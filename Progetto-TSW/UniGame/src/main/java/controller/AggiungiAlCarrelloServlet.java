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

        if (idVideogiocoStr == null || idVideogiocoStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id videogioco mancante");
            return;
        }

        int idVideogioco;
        try {
            idVideogioco = Integer.parseInt(idVideogiocoStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id videogioco non valido");
            return;
        }

        HttpSession session = request.getSession();
        Utente userSession = (Utente) session.getAttribute("utente");

        RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();

        if (userSession != null) {
            int idUtente = userSession.getIdUtente();

            if(rigaCarrelloDAO.exists(idUtente, idVideogioco)) {
                rigaCarrelloDAO.incrementaQuantita(idUtente, idVideogioco);
            } else {
                RigaCarrello rigaCarrello = new RigaCarrello();
                rigaCarrello.setQuantità(1);
                rigaCarrello.setIdUtente(idUtente);
                rigaCarrello.setIdVideogioco(idVideogioco);
                rigaCarrelloDAO.doSave(rigaCarrello);
            }

        } else {
            // Utente ospite -> salva in sessione
            List<Integer> guestCart = (ArrayList<Integer>) session.getAttribute("guestCart");
            if (guestCart == null) {
                guestCart = new ArrayList<>();
            }
            if (!guestCart.contains(idVideogioco)) {
                guestCart.add(idVideogioco);
            }
            session.setAttribute("guestCart", guestCart);
        }

        response.sendRedirect("game-page.jsp?idVideogioco=" + idVideogioco + "&aggiunto=true");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
