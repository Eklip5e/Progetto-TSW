package com.unigame.controller;

import com.unigame.model.old.User;
import com.unigame.model.old.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class RegisterServletbkp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));


        UserDAO userDAO = new UserDAO();
        try {
            if (userDAO.insert(user)) {
                response.sendRedirect("login.jsp");
            } else {
                request.setAttribute("error", "Errore durante la registrazione!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
