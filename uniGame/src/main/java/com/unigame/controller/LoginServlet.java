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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String ATTR_ERROR = "error";
    private static final String ATTR_UTENTE = "utente";
    private static final String PAGE_LOGIN = "login.jsp";
    private static final String PAGE_PROFILO = "profilo.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (!isValidLogin(username, password)) {
            request.setAttribute(ATTR_ERROR, "Username e password sono obbligatori.");
            request.getRequestDispatcher(PAGE_LOGIN).forward(request, response);
            return;
        }

        UtenteDAO utenteDAO = new UtenteDAO();
        try {
            Utente utente = utenteDAO.doRetrieveByUsername(username);

            if (utente != null && utente.checkPassword(password)) {
                HttpSession session = request.getSession();
                session.setAttribute(ATTR_UTENTE, utente);

                response.sendRedirect(PAGE_PROFILO);
            } else {
                request.setAttribute(ATTR_ERROR, "Credenziali errate!");
                request.getRequestDispatcher(PAGE_LOGIN).forward(request, response);
            }
        } catch (RuntimeException e) {
            request.setAttribute(ATTR_ERROR, "Errore durante il login: " + e.getMessage());
            request.getRequestDispatcher(PAGE_LOGIN).forward(request, response);
        }
    }

    private boolean isValidLogin(String username, String password) {
        return username != null && !username.isEmpty()
                && password != null && !password.isEmpty();
    }
}