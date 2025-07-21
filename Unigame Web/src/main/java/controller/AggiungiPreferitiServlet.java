package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAO.RigaCarrelloDAO;
import model.DAO.WishListDAO;
import model.Utente;
import model.WishList;

import java.io.IOException;

@WebServlet("/aggiungiPreferiti")
public class AggiungiPreferitiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recupero sessione utente
        HttpSession session = req.getSession();
        Utente utente = (Utente)session.getAttribute("utente");

        // Reindirizzo l'utente non registrato alla pagina di login
        if (utente == null){
            resp.sendRedirect("login.jsp");
            return;
        }

        String paginaCorrente = req.getParameter("paginaCorrente");

        String idVideogiocoStr = req.getParameter("idVideogioco");
        if (idVideogiocoStr == null || idVideogiocoStr.isBlank()) {
            resp.sendRedirect("error.jsp");
            return;
        }

        int idVideogioco;
        try {
            idVideogioco = Integer.parseInt(idVideogiocoStr);
        } catch (NumberFormatException e) {
            resp.sendRedirect("error.jsp");
            return;
        }

        try { // Aggiungo il videogioco nei preferiti
            WishListDAO wishListDAO = new WishListDAO();
            WishList wishlist = new WishList();
            wishlist.setIdUtente(utente.getIdUtente());
            wishlist.setIdVideogioco(idVideogioco);

            wishListDAO.doSave(wishlist);

            if (paginaCorrente.equals("carrello")) { // Se lo aggiungo ai preferiti dal carrello, elimino il videogioco dal carrello
                RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();
                rigaCarrelloDAO.doDeleteByIdVideogioco(idVideogioco);
            }

            resp.sendRedirect(paginaCorrente + "?idVideogioco=" + idVideogioco);

        } catch (Exception e) {
            resp.sendRedirect("error.jsp");
        }
    }
}
