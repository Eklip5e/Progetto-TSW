package com.unigame.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.unigame.model.DAO.UtenteDAO;
import com.unigame.model.Utente;

import java.io.IOException;

@WebServlet("/register") // Aggiorna l'URL del servlet se necessario
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // Recupera i parametri della richiesta
        String email = request.getParameter("e-mail");
        String username = request.getParameter("username");
        String nome = request.getParameter("name");
        String cognome = request.getParameter("surname");
        String password = request.getParameter("password");

        // Validazione semplice (puoi aggiungere controlli più rigorosi)
        if (username == null || nome == null || cognome == null || email == null || password == null
                || username.isEmpty() || nome.isEmpty() || cognome.isEmpty() || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Tutti i campi sono obbligatori.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Crea un nuovo utente
        Utente utente = new Utente();
        utente.setEmail(email);
        utente.setUsername(username);
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setPassword(password);

        try {
            // Salva l'utente nel database
            UtenteDAO utenteDAO = new UtenteDAO();
            utenteDAO.doSave(utente);

            // Reindirizza alla pagina di login dopo la registrazione
            response.sendRedirect("home.jsp");
        } catch (RuntimeException e) {
            // Gestisci eventuali errori durante la registrazione
            request.setAttribute("error", "Errore durante la registrazione: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

