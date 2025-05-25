package com.unigame.controller;

import com.unigame.model.DAO.UtenteDAO;
import com.unigame.model.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/profilo")
public class ProfiloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false); // false = non crearne una nuova

        if (session != null && session.getAttribute("utente") != null) {
            // Utente loggato → vai al profilo
            request.getRequestDispatcher("/profilo.jsp").forward(request, response);
        } else {
            // Utente non loggato → reindirizza al login
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
