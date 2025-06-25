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
        Utente userSession = (Utente) session.getAttribute("utente");

        if (userSession != null) {
            int idUtente = userSession.getIdUtente();
            RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();
            List<RigaCarrello> userCart = rigaCarrelloDAO.doRetrieveByUtenteId(idUtente);

            session.setAttribute("userCart", userCart);
            request.setAttribute("userCart", userCart);
        } else {
            List<RigaCarrello> guestCart = (ArrayList<RigaCarrello>) session.getAttribute("guestCart");

            session.setAttribute("guestCart", guestCart);
            request.setAttribute("guestCart", guestCart);
        }

        request.getRequestDispatcher("carrello.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}