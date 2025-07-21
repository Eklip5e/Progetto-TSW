package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAO.MetodoPagamentoDAO;
import model.DAO.RigaCarrelloDAO;
import model.DAO.VideogiocoDAO;
import model.Fatturazione;
import model.RigaCarrello;
import model.Utente;
import model.Videogioco;
import model.DAO.OrdineDAO;
import model.DAO.RigaOrdineDAO;
import model.Ordine;
import model.RigaOrdine;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/confermaAcquisto")
public class ConfermaAcquistoServlet extends HttpServlet {
    private static final String ATTR_ERROR = "error";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/yy");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recupero la sessione dell'utente
        HttpSession session = req.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            resp.sendRedirect("login.jsp");
        }

        int idUtente = utente.getIdUtente();

        RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();
        List<RigaCarrello> carrello = rigaCarrelloDAO.doRetrieveByUtenteId(idUtente);

        if (carrello == null || carrello.isEmpty()) {
            resp.sendRedirect("carrello");
            return;
        }

        // Recupero i dati della carta dell'utente
        String numeroCarta =  req.getParameter("numeroCarta");
        String titolare =  req.getParameter("titolare");
        String scadenzaStr = req.getParameter("scadenza");
        String cvc =  req.getParameter("cvc");

        // Controllo che i dati sono inseriti correttamente
        String errore = validaDatiCarta(numeroCarta, titolare, scadenzaStr, cvc);

        // Se i dati non sono corretti invio l'errore alla pagina e lo mostra
        if (errore != null) {
            req.setAttribute(ATTR_ERROR, errore);

            req.setAttribute("numeroCarta", numeroCarta);
            req.setAttribute("titolare", titolare);
            req.setAttribute("scadenza", scadenzaStr);
            req.setAttribute("cvc", cvc);

            req.getRequestDispatcher("pagamento").forward(req, resp);
            return;
        }

        // Inserisco i dati della carta e quelli dei videogiochi acquistati nell'ordine
        try {
            java.util.Date scadenza = DATE_FORMAT.parse(scadenzaStr);
            String numero = numeroCarta.replaceAll("\\s", "");

            MetodoPagamentoDAO metodoPagamentoDAO = new MetodoPagamentoDAO();
            Fatturazione fatturazione = metodoPagamentoDAO.doRetrieveByNumeroAndUtente(numero, idUtente);

            if (fatturazione == null) {
                fatturazione = new Fatturazione();
                fatturazione.setNumero(numero);
                fatturazione.setTitolare(titolare);
                fatturazione.setScadenza(scadenza);
                fatturazione.setCvc(cvc);
                fatturazione.setIdUtente(idUtente);

                metodoPagamentoDAO.doSave(fatturazione);
            }

            double totale = 0;
            for (RigaCarrello riga : carrello) {
                totale += riga.getPrezzoUnitario() * riga.getQuantità();
            }

            OrdineDAO ordineDAO = new OrdineDAO();
            Ordine ordine = new Ordine();
            ordine.setDataOrdine(new Date());
            ordine.setStato("Completato");
            ordine.setTotale(totale);
            ordine.setIdUtente(idUtente);
            ordine.setIdFatturazione(fatturazione.getIdFatturazione());

            ordineDAO.doSave(ordine);

            RigaOrdineDAO rigaOrdineDAO = new RigaOrdineDAO();
            for (RigaCarrello riga : carrello) {
                int quantita = riga.getQuantità();
                for (int i = 0; i < quantita; i++) {
                    RigaOrdine rigaOrdine = new RigaOrdine();
                    rigaOrdine.setIdOrdine(ordine.getIdOrdine());
                    rigaOrdine.setIdVideogioco(riga.getIdVideogioco());
                    rigaOrdine.setChiave(generaChiaveAcquisto());
                    rigaOrdine.setPrezzoUnitario(riga.getPrezzoUnitario());

                    rigaOrdineDAO.doSave(rigaOrdine);
                }
            }

            rigaCarrelloDAO.doDeleteById(idUtente);

            List<RigaOrdine> righeOrdine = rigaOrdineDAO.doRetrieveByOrdineId(ordine.getIdOrdine());
            List<Videogioco> videogiochi = new ArrayList<>();

            VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
            for (RigaOrdine riga : righeOrdine) {
                Videogioco videogioco = videogiocoDAO.doRetrieveById(riga.getIdVideogioco());

                if (videogioco != null) {
                    videogiochi.add(videogioco);
                }
            }

            List<String> chiavi = new ArrayList<>();
            for (RigaOrdine riga : righeOrdine) {
                chiavi.add(riga.getChiave());
            }

            req.setAttribute("idOrdine", ordine.getIdOrdine());
            req.setAttribute("videogiochi", videogiochi);
            req.setAttribute("chiavi", chiavi);

            req.getRequestDispatcher("WEB-INF/attivazione.jsp").forward(req, resp);

        } catch (Exception e) {
            resp.sendRedirect("error.jsp");
        }
    }

    private String generaChiaveAcquisto() {
        String raw = java.util.UUID.randomUUID().toString().replace("-", "").toUpperCase(); // Genera un identificatore unico e rimuove tutti i trattini
        // Prendi solo i primi 15 caratteri e formatta in blocchi da 5
        return raw.substring(0, 5) + "-" + raw.substring(5, 10) + "-" + raw.substring(10, 15);
    }

    private String validaDatiCarta(String numeroCarta, String titolare, String scadenza, String cvc) {
        if (isNullOrEmpty(numeroCarta) || isNullOrEmpty(titolare) || isNullOrEmpty(scadenza) || isNullOrEmpty(cvc)) {
            return "Tutti i campi della carta sono obbligatori.";
        }

        String numeroCartaPulito = numeroCarta.replaceAll("\\s", "");

        if (!numeroCartaPulito.matches("^\\d{16}$")) {
            return "Numero carta non valido.";
        }

        if (!titolare.matches("^[a-zA-ZÀ-ÿ\\s']{2,50}$")) {
            // Nome titolare: solo lettere, spazi e apostrofi
            return "Nome titolare non valido.";
        }

        try {
            // Scadenza nel formato MM/yy
            java.util.Date dataScadenza = new java.text.SimpleDateFormat("MM/yy").parse(scadenza);
            // La scadenza deve essere nel futuro (almeno il mese corrente)
            Date now = new Date();
            if (dataScadenza.before(now)) {
                return "La data di scadenza non può essere nel passato.";
            }
        } catch (java.text.ParseException e) {
            return "Formato scadenza non valido. Usa MM/yy.";
        }

        if (!cvc.matches("^\\d{3,4}$")) {
            // CVC: 3 o 4 cifre
            return "CVC non valido.";
        }

        return null; // tutto ok
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}