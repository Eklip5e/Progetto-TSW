package com.unigame.controller;

import com.unigame.model.User;
import com.unigame.model.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setIndirizzo(request.getParameter("indirizzo"));
        user.setCap(request.getParameter("cap"));
        user.setCitta(request.getParameter("citta"));

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
