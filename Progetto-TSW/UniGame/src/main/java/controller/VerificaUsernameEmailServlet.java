package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO.UtenteDAO;

import java.io.IOException;

@WebServlet("/verificaUsernameEmail")
public class VerificaUsernameEmailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        UtenteDAO utenteDAO = new UtenteDAO();

        boolean exists = false;
        if (username != null) {
            exists = utenteDAO.usernameExists(username);
        } else if (email != null) {
            exists = utenteDAO.emailExists(email);
        }

        String json = "{" +
                "\"exists\":" + exists + "}";

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
