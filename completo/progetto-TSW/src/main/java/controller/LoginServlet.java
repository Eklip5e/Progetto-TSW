package controller;

import model.DAO.UtenteDAO;
import model.Utente;
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
    private static final String PAGE_PROFILO = "WEB-INF/profilo.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (isNullOrEmpty(username, password)) {
            request.setAttribute(ATTR_ERROR, "Username e password sono obbligatori.");

            request.getRequestDispatcher(PAGE_LOGIN).forward(request, response);
            return;
        }

        try {
            UtenteDAO utenteDAO = new UtenteDAO();
            Utente utente = utenteDAO.doRetrieveByUsername(username);

            if (utente != null && utente.checkPassword(password)) {
                session.setAttribute(ATTR_UTENTE, utente);

                request.getRequestDispatcher(PAGE_PROFILO).forward(request, response);
            } else {
                request.setAttribute(ATTR_ERROR, "Credenziali errate!");

                request.getRequestDispatcher(PAGE_LOGIN).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isNullOrEmpty(String username, String password) {
        return username == null && username.isEmpty()
                && password == null && password.isEmpty();
    }
}