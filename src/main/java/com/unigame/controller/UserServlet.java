package com.unigame.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/utente-control")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recupera la sessione esistente (non crea una nuova sessione se non esiste)
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("utente") != null) {
            // Utente loggato: reindirizza alla pagina del profilo
            response.sendRedirect("profilo.jsp");
        } else {
            // Utente non loggato: reindirizza alla pagina di login
            response.sendRedirect("login.jsp");
        }
    }
}
