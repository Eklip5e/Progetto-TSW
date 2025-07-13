package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAO.VideogiocoDAO;
import model.DAO.WishListDAO;
import model.Utente;
import model.Videogioco;
import model.WishList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/favorites")
public class Favorites extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utente userSession =  (Utente) session.getAttribute("utente");

        WishListDAO wishListDAO = new WishListDAO();
        List<WishList> wishList = wishListDAO.doRetrieveAllByUtente(userSession.getIdUtente());

        VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        List<Videogioco> favorites = new ArrayList<Videogioco>();
        for (WishList item : wishList) {
            Videogioco videogioco = videogiocoDAO.doRetrieveById(item.getIdVideogioco());
            favorites.add(videogioco);
        }

        req.setAttribute("videogiochi", favorites);

        req.getRequestDispatcher("wish-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
