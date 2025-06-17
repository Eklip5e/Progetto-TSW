package com.unigame.controller;

import com.unigame.model.DAO.RigaCarrelloDAO;
import com.unigame.model.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/RimuoviDalCarrello")
public class RimuoviVideogiocoDalCarrelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idVideogiocoStr = request.getParameter("idVideogioco");
        if (idVideogiocoStr == null || idVideogiocoStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id videogioco mancante");
            return;
        }

        int idVideogioco = Integer.parseInt(idVideogiocoStr);
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente != null) {
            // Utente loggato → elimina dal DB
            RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();
            rigaCarrelloDAO.doDeleteById(idVideogioco);
        } else {
            // Utente ospite → elimina dalla sessione
            List<Integer> carrelloGuest = (List<Integer>) session.getAttribute("carrelloGuest");
            if (carrelloGuest != null) {
                carrelloGuest.removeIf(id -> id == idVideogioco);
                session.setAttribute("carrelloGuest", carrelloGuest);
            }
        }

        // Redirect alla pagina del carrello aggiornata
        response.sendRedirect("MostraCarrello");
    }
}
