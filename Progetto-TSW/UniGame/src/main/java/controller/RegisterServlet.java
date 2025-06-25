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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final String ATTR_ERROR = "error";
    private static final String ATTR_UTENTE = "utente";
    private static final String PAGE_REGISTER = "register.jsp";
    private static final String PAGE_PROFILO = "profilo.jsp";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

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

        String errore = validaCampi(username, email, password, nome, cognome, dataDiNascitaStr);
        if (errore != null) {
            request.setAttribute(ATTR_ERROR, errore);

            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("nome", nome);
            request.setAttribute("cognome", cognome);
            request.setAttribute("dataDiNascita", dataDiNascitaStr);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        Date dataDiNascita;
        try {
            dataDiNascita = DATE_FORMAT.parse(dataDiNascitaStr);
        } catch (java.text.ParseException e) {
            request.setAttribute("error", "Formato data non valido. Usa dd/mm/yyyy.");
            request.getRequestDispatcher(PAGE_REGISTER).forward(request, response);
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
            e.printStackTrace();
            request.setAttribute(ATTR_ERROR, "Errore durante la registrazione.");
            request.getRequestDispatcher(PAGE_REGISTER).forward(request, response);
        }
    }

    private String validaCampi(String username, String email, String password, String nome, String cognome, String dataDiNascita) {
        if (isNullOrEmpty(username) || isNullOrEmpty(email) || isNullOrEmpty(password)
                || isNullOrEmpty(nome) || isNullOrEmpty(cognome) || isNullOrEmpty(dataDiNascita)) {
            return "Tutti i campi sono obbligatori.";
        }

        if (!username.matches("^[a-zA-Z0-9._]{4,20}$")) {
            return "Username deve essere lungo tra 4 e 20 caratteri e contenere solo lettere, numeri o underscore.";
        }

        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return "Email non valida.";
        }

        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
            return "La password deve contenere almeno 8 caratteri, una maiuscola, una minuscola e un numero.";
        }

        if (!nome.matches("^[a-zA-ZàèéìòùÀÈÉÌÒÙ'\\s]{2,30}$")) {
            return "Nome non valido. Usa solo lettere.";
        }

        if (!cognome.matches("^[a-zA-ZàèéìòùÀÈÉÌÒÙ'\\s]{2,30}$")) {
            return "Cognome non valido. Usa solo lettere.";
        }

        try {
            java.util.Date data = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(dataDiNascita);
            if (data.after(new java.util.Date())) {
                return "La data di nascita non può essere nel futuro.";
            }
        } catch (java.text.ParseException e) {
            return "Formato data di nascita non valido. Usa dd/mm/yyyy.";
        }

        return null;
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}