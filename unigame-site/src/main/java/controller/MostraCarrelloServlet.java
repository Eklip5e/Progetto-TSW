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

@WebServlet("/MostraCarrello")
public class MostraCarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente != null) {
            // Utente loggato → prendi dal DB
            int idUtente = utente.getIdUtente();
            RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();
            List<RigaCarrello> righeCarrello = rigaCarrelloDAO.doRetrieveByUtenteId(idUtente);
            request.setAttribute("righeCarrello", righeCarrello);
        } else {
            // Utente ospite → prendi dalla sessione
            List<Integer> carrelloGuest = (ArrayList<Integer>) session.getAttribute("carrelloGuest");
            if (carrelloGuest == null) {
                carrelloGuest = new ArrayList<>();
            }
            request.setAttribute("carrelloGuest", carrelloGuest);
        }

        request.getRequestDispatcher("/carrello.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}