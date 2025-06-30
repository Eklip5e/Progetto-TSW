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

@WebServlet("/AggiungiVideogiocoServlet")
public class AggiungiVideogiocoServlet extends HttpServlet {

    private static final String ATTR_ERROR = "error";
    private static final String ATTR_UTENTE = "utente";
    private static final String PAGE_REGISTER = "register.jsp";
    private static final String PAGE_PROFILO = "profilo.jsp";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Prendi i parametri dal form
        String titolo = request.getParameter("titolo");
        String piattaforma = request.getParameter("piattaforma");
        String dataRilascioStr = request.getParameter("dataRilascio");
        String descrizione = request.getParameter("descrizione");
        String produttore = request.getParameter("produttore");
        String appIdSteamStr = request.getParameter("appIdSteam");
        String prezzoStr = request.getParameter("prezzo");
        String scontoStr = request.getParameter("sconto");

        String errore = validaCampi(titolo, piattaforma, dataRilascioStr, descrizione, produttore, appIdSteamStr, prezzoStr, scontoStr);
        if (errore != null) {
            request.setAttribute(ATTR_ERROR, errore);

            request.setAttribute("modalOpen", true);

            request.setAttribute("titolo", titolo);
            request.setAttribute("piattaforma", piattaforma);
            request.setAttribute("dataRilascio", dataRilascioStr);
            request.setAttribute("descrizione", descrizione);
            request.setAttribute("produttore", produttore);
            request.setAttribute("appIdSteam", appIdSteamStr);
            request.setAttribute("prezzo", prezzoStr);
            request.setAttribute("sconto", scontoStr);

            request.getRequestDispatcher("home.jsp").forward(request, response);
            return;
        }

        java.util.Date dataRilascio;
        try {
            dataRilascio = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(dataRilascioStr);
        } catch (java.text.ParseException e) {
            request.setAttribute("error", "Formato data non valido.");
            request.getRequestDispatcher("home.jsp").forward(request, response);
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
        videogioco.setAppIdSteam(appIdSteam);
        videogioco.setPrezzo(prezzo);
        videogioco.setSconto(sconto);

        try {
            VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
            videogiocoDAO.doSave(videogioco);

            response.sendRedirect("home.jsp");

        } catch (Exception e) {
            request.setAttribute("error", "Errore interno: " + e.getMessage());
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }

    private String validaCampi(String titolo, String piattaforma, String dataRilascio, String descrizione, String produttore, String appIdSteam, String prezzo, String sconto) {
        if (isNullOrEmpty(titolo) || isNullOrEmpty(piattaforma) || isNullOrEmpty(dataRilascio)
                || isNullOrEmpty(descrizione) || isNullOrEmpty(produttore) || isNullOrEmpty(appIdSteam)
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