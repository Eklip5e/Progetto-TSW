package controller.admin;

import model.DAO.VideogiocoDAO;
import model.Videogioco;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/aggiungiVideogioco")
public class AggiungiVideogiocoServlet extends HttpServlet {

    private static final String ATTR_ERROR = "error";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String titolo = req.getParameter("titolo");
        String piattaforma = req.getParameter("piattaforma");
        String dataRilascioStr = req.getParameter("dataRilascio");
        String descrizione = req.getParameter("descrizione");
        String produttore = req.getParameter("produttore");
        String genere = req.getParameter("genere");
        String appIdSteamStr = req.getParameter("appIdSteam");
        String prezzoStr = req.getParameter("prezzo");
        String scontoStr = req.getParameter("sconto");

        String errore = validaCampi(titolo, piattaforma, dataRilascioStr, descrizione, produttore, genere, appIdSteamStr, prezzoStr, scontoStr);

        if (errore != null) {
            req.setAttribute(ATTR_ERROR, errore);
            req.setAttribute("modalOpen", true);

            req.setAttribute("titolo", titolo);
            req.setAttribute("piattaforma", piattaforma);
            req.setAttribute("dataRilascio", dataRilascioStr);
            req.setAttribute("descrizione", descrizione);
            req.setAttribute("produttore", produttore);
            req.setAttribute("genere", genere);
            req.setAttribute("appIdSteam", appIdSteamStr);
            req.setAttribute("prezzo", prezzoStr);
            req.setAttribute("sconto", scontoStr);

            req.getRequestDispatcher("home.jsp").forward(req, resp);
            return;
        }

        try {
            java.util.Date dataRilascio = DATE_FORMAT.parse(dataRilascioStr);
            int appIdSteam = Integer.parseInt(appIdSteamStr);
            double prezzo = Double.parseDouble(prezzoStr);
            int sconto = Integer.parseInt(scontoStr);

            if (sconto < 0 || sconto > 100) {
                errore = "Lo sconto deve essere compreso tra 0 e 100.";

                req.getRequestDispatcher("home.jsp").forward(req, resp);
                return;
            }

            Videogioco videogioco = new Videogioco();
            videogioco.setTitolo(titolo);
            videogioco.setPiattaforma(piattaforma);
            videogioco.setDataRilascio(dataRilascio);
            videogioco.setDescrizione(descrizione);
            videogioco.setProduttore(produttore);
            videogioco.setGenere(genere);
            videogioco.setAppIdSteam(appIdSteam);
            videogioco.setPrezzo(prezzo);
            videogioco.setSconto(sconto);

            VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
            videogiocoDAO.doSave(videogioco);

            resp.sendRedirect("home.jsp");

        } catch (Exception e) {
            resp.sendRedirect("home.jsp");
        }
    }

    private String validaCampi(String titolo, String piattaforma, String dataRilascio, String descrizione, String produttore, String genere, String appIdSteam, String prezzo, String sconto) {
        if (isNullOrEmpty(titolo) || isNullOrEmpty(piattaforma) || isNullOrEmpty(dataRilascio)
                || isNullOrEmpty(descrizione) || isNullOrEmpty(produttore) || isNullOrEmpty(genere)
                || isNullOrEmpty(appIdSteam) || isNullOrEmpty(prezzo) || isNullOrEmpty(sconto)) {
            return "Tutti i campi sono obbligatori.";
        }

        if (titolo.length() < 3) return "Il titolo deve contenere almeno 3 caratteri.";

        if (descrizione.length() < 3) return "La descrizione deve contenere almeno 3 caratteri.";

        if (produttore.length() < 3) return "Il produttore deve contenere almeno 3 caratteri.";

        if (!appIdSteam.matches("\\d+")) return "ID Steam non valido. Usa solo numeri.";

        if (!prezzo.matches("\\d+(\\.\\d{1,2})?")) return "Prezzo non valido. Usa solo numeri, eventualmente con 1 o 2 decimali separati da punto.";

        if (!sconto.matches("\\d+")) return "Sconto non valido. Inserisci un numero intero da 0 a 100.";

        try {
            DATE_FORMAT.parse(dataRilascio);
        } catch (Exception e) {
            return "Formato data non valido. Usa dd/MM/yyyy.";
        }

        return null;
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}