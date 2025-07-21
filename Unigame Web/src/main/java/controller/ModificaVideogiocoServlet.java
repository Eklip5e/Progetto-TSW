package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO.VideogiocoDAO;
import model.Videogioco;

import java.io.IOException;

@WebServlet("/modificaVideogioco")
public class ModificaVideogiocoServlet extends HttpServlet {

    private static final String ATTR_ERROR = "error";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idVideogioco = Integer.parseInt(req.getParameter("idVideogioco"));
        String titolo = req.getParameter("titolo");
        String piattaforma = req.getParameter("piattaforma");
        String dataRilascioStr =  req.getParameter("dataRilascio");
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

            req.getRequestDispatcher("gamePage").forward(req, resp);
            return;
        }

        java.util.Date dataRilascio;
        try {
            dataRilascio = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(dataRilascioStr);
        } catch (java.text.ParseException e) {
            req.setAttribute("error", "Formato data non valido.");
            req.getRequestDispatcher("gamePage").forward(req, resp);
            return;
        }

        int appIdSteam = Integer.parseInt(appIdSteamStr);
        double prezzo = Double.parseDouble(prezzoStr);
        int sconto = Integer.parseInt(scontoStr);

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
        videogiocoDAO.doUpdate(videogioco, idVideogioco);

        req.getRequestDispatcher("gamePage").forward(req, resp);
    }

    private String validaCampi(String titolo, String piattaforma, String dataRilascio, String descrizione, String produttore, String genere, String appIdSteam, String prezzo, String sconto) {
        if (isNullOrEmpty(titolo) || isNullOrEmpty(piattaforma) || isNullOrEmpty(dataRilascio)
                || isNullOrEmpty(descrizione) || isNullOrEmpty(produttore) || isNullOrEmpty(genere) || isNullOrEmpty(appIdSteam)
                || isNullOrEmpty(prezzo) || isNullOrEmpty(sconto)) {
            return "Tutti i campi sono obbligatori.";
        }

        if (!titolo.matches("^.{3,}$")) {
            return "Il titolo deve contenere almeno 3 caratteri.";
        }

        if (!descrizione.matches("^.{3,}$")) {
            return "La descrizione deve contenere almeno 3 caratteri.";
        }

        if (!produttore.matches("^.{3,}$")) {
            return "Il produttore deve contenere almeno 3 caratteri.";
        }

        if (!appIdSteam.matches("^\\d+$")) {
            return "ID Steam non valido. Usa solo numeri.";
        }

        if (!prezzo.matches("^\\d+(\\.\\d{1,2})?$")) {
            return "Prezzo non valido. Usa solo numeri, eventualmente con 1 o 2 decimali separati da punto.";
        }

        if (!sconto.matches("^\\d{1,3}$")) {
            return "Sconto non valido. Inserisci un numero intero da 0 a 999.";
        }

        return null;
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}
