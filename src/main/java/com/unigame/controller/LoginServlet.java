package com.unigame.controller;

import com.unigame.model.DAO.UtenteDAO; // Assicurati che UtenteDAO sia aggiornato
import com.unigame.model.Utente; // Aggiungi questa importazione se necessario
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login") // URL del servlet
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
            // Verifica le credenziali
            Utente utente = utenteDAO.doRetrieveByUsernameAndPassword(username, password);

            if (utente != null) {
                // Credenziali valide: crea una sessione
                HttpSession session = request.getSession();
                session.setAttribute("username", utente.getUsername());
                session.setAttribute("idUtente", utente.getIdUtente()); // Salva anche l'ID dell'utente

                // Reindirizza alla pagina di benvenuto
                response.sendRedirect("home.jsp");
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