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

import java.io.IOException;

@WebServlet("/gamePage")
public class GamePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utente utente = (Utente)session.getAttribute("utente");

        int idVideogioco = Integer.parseInt(req.getParameter("idVideogioco"));

        VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        Videogioco videogioco = videogiocoDAO.doRetrieveById(idVideogioco);

        WishListDAO wishListDAO = new WishListDAO();
        boolean preferito = false;
        if (utente != null)
            preferito = wishListDAO.isPreferito(utente.getIdUtente(), idVideogioco);

        req.setAttribute("videogioco", videogioco);
        req.setAttribute("preferito", preferito);

        req.getRequestDispatcher("WEB-INF/game-page.jsp").forward(req, resp);
    }
}
