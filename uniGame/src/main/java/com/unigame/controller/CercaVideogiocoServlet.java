package com.unigame.controller;

import com.unigame.model.DAO.VideogiocoDAO;
import com.unigame.model.Videogioco;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CercaVideogiocoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String titolo = request.getParameter("titolo");
        String piattaforma = request.getParameter("piattaforma");
        String genere =  request.getParameter("genere");
        String prezzoMinStr = request.getParameter("prezzoMin");
        String prezzoMaxStr = request.getParameter("prezzoMax");

        Double prezzoMin = Double.parseDouble(prezzoMinStr);
        Double prezzoMax = Double.parseDouble(prezzoMaxStr);

        VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        List<Videogioco> videogiochi = null;

        try {
            videogiochi = videogiocoDAO.doRetrieveByFiltro(titolo, piattaforma, genere, prezzoMin, prezzoMax);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("videogiochi", videogiochi);
        request.getRequestDispatcher("search-page.jsp").forward(request, response);
    }
}
