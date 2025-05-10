package com.unigame.controller;

import com.unigame.model.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        try {
            if (userDAO.validate(username, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("jsp/welcome.jsp");
            } else {
                request.setAttribute("error", "Credenziali errate!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
