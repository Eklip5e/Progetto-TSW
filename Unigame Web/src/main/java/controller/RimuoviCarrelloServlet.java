package controller;

import model.DAO.RigaCarrelloDAO;
import model.RigaCarrello;
import model.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/rimuoviCarrello")
public class RimuoviCarrelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        int idVideogioco = Integer.parseInt(request.getParameter("idVideogioco"));


        if (utente != null) {
            RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();
            rigaCarrelloDAO.doDeleteByIdVideogioco(idVideogioco);
        } else {
            List<RigaCarrello> carrello = (List<RigaCarrello>) session.getAttribute("carrello");

            RigaCarrello rigaCarrello = new RigaCarrello();
            for (RigaCarrello riga : carrello) {
                if (riga.getIdVideogioco() == idVideogioco) {
                    rigaCarrello = riga;
                    break;
                }
            }

            carrello.remove(rigaCarrello);
        }

        response.sendRedirect("carrello");
    }
}
