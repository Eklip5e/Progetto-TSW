package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO.FatturazioneDAO;
import model.DAO.RigaCarrelloDAO;
import model.Fatturazione;
import model.RigaCarrello;
import model.Utente;
import model.acquisto.DAO.OrdineDAO;
import model.acquisto.DAO.RigaOrdineDAO;
import model.acquisto.Ordine;
import model.acquisto.RigaOrdine;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/confermaAcquisto")
public class ConfermaAcquistoServlet extends HttpServlet {
    private static final String ATTR_ERROR = "error";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente userSession = (Utente) request.getSession().getAttribute("utente");
        int idUtente = userSession.getIdUtente();

        RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();
        List<RigaCarrello> giochiAcquistati = rigaCarrelloDAO.doRetrieveByUtenteId(idUtente);

        String numeroCarta =  request.getParameter("numeroCarta");
        String titolare =  request.getParameter("titolare");
        String scadenzaStr = request.getParameter("scadenza");
        String cvc =  request.getParameter("cvc");

        String errore = validaDatiCarta(numeroCarta, titolare, scadenzaStr, cvc);
        if (errore != null) {
            request.setAttribute(ATTR_ERROR, errore);

            request.setAttribute("numeroCarta", numeroCarta);
            request.setAttribute("titolare", titolare);
            request.setAttribute("scadenza", scadenzaStr);
            request.setAttribute("cvc", cvc);

            request.getRequestDispatcher("pagamento.jsp").forward(request, response);
            return;
        }

        Date scadenza;
        try {
            scadenza = new SimpleDateFormat("MM/yy").parse(scadenzaStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Fatturazione fatturazione = new Fatturazione();
        fatturazione.setNumero(numeroCarta);
        fatturazione.setTitolare(titolare);
        fatturazione.setScadenza(scadenza);
        fatturazione.setCvc(cvc);
        fatturazione.setIdUtente(idUtente);

        FatturazioneDAO fatturazioneDAO = new FatturazioneDAO();
        fatturazioneDAO.doSave(fatturazione);

        double totale = 0;
        for(RigaCarrello riga : giochiAcquistati){
            totale += riga.getPrezzoUnitario() * riga.getQuantità();
        }

        Ordine ordine = new Ordine();
        ordine.setDataOrdine(new Date());
        ordine.setStato("In elaborazione");
        ordine.setTotale(totale);
        ordine.setIdUtente(idUtente);
        ordine.setIdFatturazione(fatturazione.getIdFatturazione());

        OrdineDAO ordineDAO = new OrdineDAO();
        ordineDAO.doSave(ordine);

        RigaOrdineDAO rigaOrdineDAO = new RigaOrdineDAO();

        for (RigaCarrello rigaCarrello : giochiAcquistati) {
            int quantita = rigaCarrello.getQuantità();
            for (int i = 0; i < quantita; i++) {
                RigaOrdine rigaOrdine = new RigaOrdine();
                rigaOrdine.setIdOrdine(ordine.getIdOrdine());
                rigaOrdine.setIdVideogioco(rigaCarrello.getIdVideogioco());
                rigaOrdine.setChiave(generaChiaveAcquisto());
                rigaOrdine.setPrezzoUnitario(rigaCarrello.getPrezzoUnitario());

                rigaOrdineDAO.doSave(rigaOrdine);
            }
        }

        rigaCarrelloDAO.doDeleteById(idUtente);

        List<RigaOrdine> righeOrdine = rigaOrdineDAO.doRetrieveByOrdineId(ordine.getIdOrdine());
        request.setAttribute("righeOrdine", righeOrdine);
        request.getRequestDispatcher("attivazione.jsp").forward(request, response);
    }

    private String generaChiaveAcquisto() {
        String raw = java.util.UUID.randomUUID().toString().replace("-", "").toUpperCase();
        // Prendi solo i primi 15 caratteri e formatta in blocchi da 5
        return raw.substring(0, 5) + "-" + raw.substring(5, 10) + "-" + raw.substring(10, 15);
    }

    private String validaDatiCarta(String numeroCarta, String titolare, String scadenza, String cvc) {
        if (isNullOrEmpty(numeroCarta) || isNullOrEmpty(titolare) || isNullOrEmpty(scadenza) || isNullOrEmpty(cvc)) {
            return "Tutti i campi della carta sono obbligatori.";
        }

        if (!numeroCarta.matches("^\\d{13,19}$")) {
            // Controlla che la carta abbia solo cifre e lunghezza tipica (13-19 cifre)
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
}