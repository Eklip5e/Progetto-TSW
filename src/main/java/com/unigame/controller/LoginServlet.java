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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recupera i parametri della richiesta
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validazione dei parametri
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Username e password sono obbligatori.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Crea un'istanza di UtenteDAO
        UtenteDAO utenteDAO = new UtenteDAO();

        try {
            // Recupera l'utente dal database tramite username
            Utente utente = utenteDAO.doRetrieveByUsername(username);

            if (utente != null && utente.checkPassword(password)) {
                // Credenziali valide: crea una sessione
                HttpSession session = request.getSession();
                session.setAttribute("username", utente.getUsername());
                session.setAttribute("idUtente", utente.getIdUtente()); // Salva anche l'ID dell'utente

                // Reindirizza alla pagina del profilo
                response.sendRedirect("profilo.jsp");
            } else {
                // Credenziali non valide: restituisci un messaggio di errore
                request.setAttribute("error", "Credenziali errate!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (RuntimeException e) {
            // Gestisci eventuali errori durante la verifica delle credenziali
            request.setAttribute("error", "Errore durante il login: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}