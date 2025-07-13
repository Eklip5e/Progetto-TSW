package controller;

import model.Banner;
import model.DAO.BannerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/aggiungiBanner")
public class AggiungiBannerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idVideogiocoStr =  req.getParameter("videogioco");

        if (idVideogiocoStr == null || idVideogiocoStr.isEmpty()) {
            resp.sendRedirect("home.jsp");
            return;
        }

        int idVideogioco;
        try {
            idVideogioco = Integer.parseInt(idVideogiocoStr);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "idVideogioco non valido");
            return;
        }

        BannerDAO bannerDAO = new BannerDAO();

        Banner banner = new Banner();

        banner.setIdBanner(1);
        banner.setIdVideogioco(idVideogioco);

        try {
            bannerDAO.doSave(banner);
            resp.sendRedirect("home.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore inserimento banner");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

