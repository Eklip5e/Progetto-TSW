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

@WebServlet("/rimuoviPreferiti")
public class RimuoviPreferitiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        int idUtente = utente.getIdUtente();

        int idVideogioco = Integer.parseInt(req.getParameter("idVideogioco"));

        String paginaCorrente = req.getParameter("paginaCorrente");

        WishListDAO wishListDAO = new WishListDAO();
        wishListDAO.doDeleteByGame(idUtente, idVideogioco);

        resp.sendRedirect(paginaCorrente + "?idVideogioco=" + idVideogioco);
    }
}
