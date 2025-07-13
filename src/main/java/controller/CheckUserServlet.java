package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO.UtenteDAO;

import java.io.IOException;

@WebServlet("/checkUser")
public class CheckUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        boolean usernameExists = false;
        boolean emailExists = false;

        String usernameMessage = "";
        String emailMessage = "";

        if (username != null && UtenteDAO.usernameExists(username)) {
            usernameMessage = "Username già in uso!";
        } else if (email != null && UtenteDAO.emailExists(email)) {
            emailMessage = "Email già registrata!";
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = String.format(
                "{\"usernameMessage\": \"%s\", \"emailMessage\": \"%s\"}",
                usernameMessage.replace("\"", "\\\""),
                emailMessage.replace("\"", "\\\"")
        );

        response.getWriter().write(json);
    }
}
