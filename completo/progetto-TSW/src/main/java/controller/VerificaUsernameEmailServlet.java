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

@WebServlet("/verificaUsernameEmail")
public class VerificaUsernameEmailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente userSession = (Utente) session.getAttribute("utente");

        String username = request.getParameter("username");
        String email = request.getParameter("email");

        UtenteDAO utenteDAO = new UtenteDAO();

        boolean exist = false;
        if (userSession != null) {
            // Caso utente loggato: escludi il suo username/email dal controllo
            if (username != null && !username.equals(userSession.getUsername())) {
                exist = utenteDAO.usernameExists(username);
            }

            if (!exist && email != null && !email.equals(userSession.getEmail())) {
                exist = utenteDAO.emailExists(email);
            }
        } else {
            // Caso utente NON loggato (registrazione): controlla semplicemente se esistono
            if (username != null) {
                exist = utenteDAO.usernameExists(username);
            } else if (email != null) {
                exist = utenteDAO.emailExists(email);
            }
        }

        org.json.simple.JSONObject json = new org.json.simple.JSONObject();
        json.put("exist", exist);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toJSONString());
    }
}
