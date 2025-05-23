package com.unigame.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CategoriaServlet")
public class CategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoria = request.getParameter("categoria");

        String paginaDestinazione;

        switch (categoria) {
            case "pc":
                paginaDestinazione = "categorie/pc.jsp";
                break;
            case "playstation":
                paginaDestinazione = "categorie/playstation.jsp";
                break;
            case "xbox":
                paginaDestinazione = "categorie/xbox.jsp";
                break;
            case "nintendo":
                paginaDestinazione = "categorie/nintendo.jsp";
                break;
            default:
                paginaDestinazione = "home.jsp"; // oppure una pagina di errore
                break;
        }

        // Puoi anche passare attributi alla JSP se ti servono
        request.getRequestDispatcher(paginaDestinazione).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
