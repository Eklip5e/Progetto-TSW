package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO.VideogiocoDAO;
import model.Videogioco;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/gamesDisabled")
public class GamesDisabled extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        VideogiocoDAO videogiocoDAO = new VideogiocoDAO();

        List<Videogioco> videogiochi = videogiocoDAO.doRetrieveDisabled();

        req.setAttribute("videogiochi", videogiochi);
        req.getRequestDispatcher("disabled.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
