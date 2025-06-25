package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO.UtenteDAO;

import java.io.IOException;
import java.io.PrintWriter;

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

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{\"exists\": " + exists + "}");
        out.flush();
    }
}
