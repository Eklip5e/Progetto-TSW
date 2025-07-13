package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAO.WishListDAO;
import model.Utente;

import java.io.IOException;

@WebServlet("/removeFavorites")
public class RemoveFavorites extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utente userSession = (Utente)session.getAttribute("utente");
        int idUtente = userSession.getIdUtente();

        String idVideogiocoStr =  req.getParameter("idVideogioco");
        int idVideogioco = Integer.parseInt(idVideogiocoStr);

        WishListDAO wishListDAO = new WishListDAO();
        wishListDAO.doDeleteByGame(idUtente, idVideogioco);

        req.getRequestDispatcher("favorites").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
