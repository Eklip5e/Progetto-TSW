package controller.admin;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAO.RigaCarrelloDAO;
import model.Utente;

import java.io.IOException;

@WebServlet("/AggiornaQuantitaCarrello")
public class AggiornaQuantitaCarrelloServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        int idUtente = utente.getIdUtente();

        try {
            int idVideogioco = Integer.parseInt(request.getParameter("idVideogioco"));
            int nuovaQuantita = Integer.parseInt(request.getParameter("quantita"));

            RigaCarrelloDAO dao = new RigaCarrelloDAO();
            dao.aggiornaQuantita(idUtente, idVideogioco, nuovaQuantita);

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
