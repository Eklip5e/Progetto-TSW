package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAO.RigaCarrelloDAO;
import model.DAO.WishListDAO;
import model.RigaCarrello;
import model.Utente;
import model.WishList;

import java.io.IOException;
import java.util.List;

@WebServlet("/addFavorites")
public class AddFavorites extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente)session.getAttribute("utente");
        
        if (utente == null){
            response.sendRedirect("login.jsp");
            return;
        }

        String idVideogiocoStr = request.getParameter("idVideogioco");
        int idVideogioco = Integer.parseInt(idVideogiocoStr);

        WishListDAO wishListDAO = new WishListDAO();
        try {
            WishList wishlist = new WishList();
            wishlist.setIdUtente(utente.getIdUtente());
            wishlist.setIdVideogioco(idVideogioco);

            wishListDAO.doSave(wishlist);

            RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();
            rigaCarrelloDAO.doDeleteByIdVideogioco(idVideogioco);

            response.sendRedirect("MostraCarrello");

        } catch (RuntimeException e) {
            response.sendRedirect("carrello.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
