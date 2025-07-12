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

@WebServlet("/modificaPassword")
public class ModificaPassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utente utente = (Utente)session.getAttribute("utente");

        String password =  req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        String errore = isValid(password, confirmPassword);
        if (errore != null) {
            req.setAttribute("error", errore);
            req.setAttribute("modalUpdatePassword-open", true);

            req.getRequestDispatcher("WEB-INF/profilo.jsp").forward(req, resp);
            return;
        }

        utente.setPassword(password);

        UtenteDAO utenteDAO = new UtenteDAO();
        utenteDAO.updatePassword(utente.getIdUtente(), utente.getPassword());

        req.getRequestDispatcher("WEB-INF/profilo.jsp").forward(req, resp);
    }

    public String isValid (String password,  String confirmPassword) {
        if (isNullOrEmpty(password) || isNullOrEmpty(confirmPassword))
            return "Tutti i campi sono obbligatori.";

        if (!password.equals(confirmPassword))
            return "Le password non corrispondono!";

        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
            return "La password deve contenere almeno 8 caratteri, una maiuscola, una minuscola e un numero.";
        }

        return null;
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}
