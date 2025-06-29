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

@WebServlet("/RimuoviDalCarrello")
public class RimuoviVideogiocoDalCarrelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String idVideogiocoStr = request.getParameter("idVideogioco");
        if (idVideogiocoStr == null || idVideogiocoStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id videogioco mancante");
            return;
        }

        int idVideogioco = Integer.parseInt(idVideogiocoStr);
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente != null) {
            // Utente loggato -> elimina dal DB
            RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();
            rigaCarrelloDAO.doDeleteByIdVideogioco(idVideogioco);
        } else {
            // Utente ospite -> elimina dalla sessione
            List<RigaCarrello> carrello = (List<RigaCarrello>) session.getAttribute("righeCarrello");

            Iterator<RigaCarrello> iterator = carrello.iterator();
            while (iterator.hasNext()) {
                RigaCarrello riga = iterator.next();
                if (riga.getIdVideogioco() == idVideogioco) {
                    iterator.remove();
                    break;
                }
            }
        }

        response.sendRedirect("MostraCarrello");
    }
}
