package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO.VideogiocoDAO;
import model.Videogioco;

import java.io.IOException;

@WebServlet("/modificaVideogioco")
public class UpdateVideogame extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idVideogioco = Integer.parseInt(req.getParameter("idVideogioco"));
        String titolo = req.getParameter("titolo");
        String piattaforma = req.getParameter("piattaforma");
        String dataRilascioStr =  req.getParameter("dataRilascio");
        String descrizione = req.getParameter("descrizione");
        String produttore = req.getParameter("produttore");
        int appIdSteam = Integer.parseInt(req.getParameter("appIdSteam"));
        double prezzo = Double.parseDouble(req.getParameter("prezzo"));
        int sconto = Integer.parseInt(req.getParameter("sconto"));

        java.util.Date dataRilascio;
        try {
            dataRilascio = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(dataRilascioStr);
        } catch (java.text.ParseException e) {
            req.setAttribute("error", "Formato data non valido.");
            req.getRequestDispatcher("game-page.jsp").forward(req, resp);
            return;
        }

        Videogioco videogioco = new Videogioco();
        videogioco.setTitolo(titolo);
        videogioco.setPiattaforma(piattaforma);
        videogioco.setDataRilascio(dataRilascio);
        videogioco.setDescrizione(descrizione);
        videogioco.setProduttore(produttore);
        videogioco.setAppIdSteam(appIdSteam);
        videogioco.setPrezzo(prezzo);
        videogioco.setSconto(sconto);

        VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        videogiocoDAO.doUpdate(videogioco, idVideogioco);

        req.getRequestDispatcher("game-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
