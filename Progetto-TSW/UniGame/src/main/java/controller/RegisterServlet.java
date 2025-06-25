package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAO.UtenteDAO;
import model.Utente;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final String ATTR_ERROR = "error";
    private static final String ATTR_UTENTE = "utente";
    private static final String PAGE_REGISTER = "register.jsp";
    private static final String PAGE_PROFILO = "profilo.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String dataDiNascitaStr =  request.getParameter("dataDiNascita");

        if (!isValidRegister(username, email, password, nome, cognome, dataDiNascitaStr)) {
            request.setAttribute(ATTR_ERROR, "Tutti i campi sono obbligatori");
            request.getRequestDispatcher(PAGE_REGISTER).forward(request, response);
            return;
        }

        java.util.Date dataDiNascita;
        try {
            dataDiNascita = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(dataDiNascitaStr);
        } catch (java.text.ParseException e) {
            request.setAttribute("error", "Formato data non valido.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Crea un nuovo utente
        Utente utente = new Utente();
        utente.setAdmin(false);
        utente.setUsername(username);
        utente.setEmail(email);
        utente.setPassword(password);
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setDataDiNascita(dataDiNascita);

        try {
            UtenteDAO utenteDAO = new UtenteDAO();
            utenteDAO.doSave(utente);

            HttpSession session = request.getSession();
            session.setAttribute(ATTR_UTENTE, utente);

            response.sendRedirect(PAGE_PROFILO);
        } catch (RuntimeException e) {
            request.setAttribute("error", "Errore durante la registrazione");
            request.getRequestDispatcher(PAGE_REGISTER).forward(request, response);
        }
    }

    private boolean isValidRegister(String email, String username, String password, String nome, String cognome, String dataDiNascita) {
        return email != null && !email.isEmpty()
                && username != null && !username.isEmpty()
                && password != null && !password.isEmpty()
                && nome != null && !nome.isEmpty()
                && cognome != null && !cognome.isEmpty()
                && dataDiNascita != null && !dataDiNascita.isEmpty();
    }
}