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

@WebServlet("/modificaProfilo")
public class ModificaProfiloServlet extends HttpServlet {

    private static final String ATTR_ERROR = "error";
    private static final String PAGE_PROFILO = "WEB-INF/profilo.jsp";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Utente userSession = (Utente) session.getAttribute("utente");

        UtenteDAO utenteDAO = new UtenteDAO();

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String nome = req.getParameter("nome");
        String cognome = req.getParameter("cognome");
        String dataDiNascitaStr =  req.getParameter("dataDiNascita");

        if (utenteDAO.usernameExistsForOtherUser(username, userSession.getIdUtente())) {
            req.setAttribute(ATTR_ERROR, "Username già esistente");
            req.setAttribute("modalOpen", true);

            req.getRequestDispatcher(PAGE_PROFILO).forward(req, resp);
            return;
        }

        if (utenteDAO.emailExistsForOtherUser(email, userSession.getIdUtente())) {
            req.setAttribute(ATTR_ERROR, "Email già esistente");
            req.setAttribute("modalOpen", true);

            req.getRequestDispatcher(PAGE_PROFILO).forward(req, resp);
            return;
        }

        String errore = validaCampi(username, email, nome, cognome, dataDiNascitaStr);
        if (errore != null) {
            req.setAttribute(ATTR_ERROR, errore);
            req.setAttribute("modalOpen", true);

            req.getRequestDispatcher(PAGE_PROFILO).forward(req, resp);
            return;
        }

        Date dataDiNascita;
        try {
            dataDiNascita = DATE_FORMAT.parse(dataDiNascitaStr);
        } catch (java.text.ParseException e) {
            req.setAttribute("error", "Formato data non valido. Usa dd/mm/yyyy.");
            req.getRequestDispatcher(PAGE_PROFILO).forward(req, resp);
            return;
        }

        Utente utente = new Utente();
        utente.setIdUtente(userSession.getIdUtente());
        utente.setAdmin(userSession.isAdmin());
        utente.setUsername(username);
        utente.setEmail(email);
        utente.setPassword(userSession.getPassword());
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setDataDiNascita(dataDiNascita);

        utenteDAO.doUpdate(utente, userSession.getIdUtente());

        session.setAttribute("utente", utente);

        req.getRequestDispatcher(PAGE_PROFILO).forward(req, resp);
    }

    private String validaCampi(String username, String email, String nome, String cognome, String dataDiNascita) {
        if (isNullOrEmpty(username) || isNullOrEmpty(email) || isNullOrEmpty(nome)
                || isNullOrEmpty(cognome) || isNullOrEmpty(dataDiNascita)) {
            return "Tutti i campi sono obbligatori.";
        }

        if (!username.matches("^[a-zA-Z0-9._]{4,20}$")) {
            return "Username deve essere lungo tra 4 e 20 caratteri e contenere solo lettere, numeri o underscore.";
        }

        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return "Email non valida.";
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

