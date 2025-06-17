package com.unigame.controller;

import com.unigame.model.Banner;
import com.unigame.model.DAO.BannerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateBanner")
public class UpdateBannerServlet extends HttpServlet {

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
        banner.setIdVideogioco(idVideogioco);

        int idBanner = 1;

        try {
            bannerDAO.doUpdate(banner, idBanner);
            resp.sendRedirect("home.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore aggiornamento banner");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
