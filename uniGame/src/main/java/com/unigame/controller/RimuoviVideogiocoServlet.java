package com.unigame.controller;

import com.unigame.model.DAO.VideogiocoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/RimuoviVideogiocoServlet")
public class RimuoviVideogiocoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idVideogioco = request.getParameter("id");

        if (idVideogioco == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID mancante");
            return;
        }

        try {
            int id = Integer.parseInt(idVideogioco);
            VideogiocoDAO dao = new VideogiocoDAO();
            dao.doDelete(id);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID non valido");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore interno");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
