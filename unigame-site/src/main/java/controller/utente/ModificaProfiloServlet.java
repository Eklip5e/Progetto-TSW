package controller.utente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO.UtenteDAO;
import model.DAO.VideogiocoDAO;
import model.Utente;

import java.io.IOException;

@WebServlet("/modificaProfilo")
public class ModificaProfiloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String dataDiNascitaStr = request.getParameter("dataDiNascita");

        java.util.Date dataDiNascita;
        try {
            dataDiNascita = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(dataDiNascitaStr);
        } catch (java.text.ParseException e) {
            request.setAttribute("error", "Formato data non valido.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        utente.setUsername(username);
        utente.setEmail(email);
        utente.setPassword(password);
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setDataDiNascita(dataDiNascita);

        try {
            UtenteDAO utenteDAO = new UtenteDAO();
            utenteDAO.doUpdate(utente, utente.getIdUtente());

            response.sendRedirect("profilo.jsp");
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Formato data non valido.");
            request.getRequestDispatcher("profilo.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Errore interno: " + e.getMessage());
            request.getRequestDispatcher("profilo.jsp").forward(request, response);
        }
    }

        protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            super.doPost(req, resp);
        }
}
