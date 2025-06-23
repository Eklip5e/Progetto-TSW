package controller;

import model.DAO.RigaCarrelloDAO;
import model.RigaCarrello;
import model.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AggiungiAlCarrello")
public class AggiungiAlCarrelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idVideogiocoStr = request.getParameter("idVideogioco");
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (idVideogiocoStr == null || idVideogiocoStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id videogioco mancante");
            return;
        }

        int idVideogioco = Integer.parseInt(idVideogiocoStr);

        if (utente != null) {
            // Utente loggato -> salva su DB
            RigaCarrello riga = new RigaCarrello();
            riga.setIdUtente(utente.getIdUtente());
            riga.setIdVideogioco(idVideogioco);

            RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();
            rigaCarrelloDAO.doSave(riga);
        } else {
            // Utente ospite -> salva in sessione
            List<Integer> carrelloGuest = (ArrayList<Integer>) session.getAttribute("carrelloGuest");
            if (carrelloGuest == null) {
                carrelloGuest = new ArrayList<>();
            }
            carrelloGuest.add(idVideogioco);
            session.setAttribute("carrelloGuest", carrelloGuest);
        }

        response.sendRedirect("game-page.jsp?idVideogioco=" + idVideogioco + "&aggiunto=true");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
