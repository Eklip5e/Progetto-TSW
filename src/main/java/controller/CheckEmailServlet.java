package controller;

import model.DAO.UtenteDAO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CheckEmailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        UtenteDAO utenteDAO = new UtenteDAO();
        boolean exists = false;
        if (email != null && !email.isEmpty()) {
        }
    }
}
