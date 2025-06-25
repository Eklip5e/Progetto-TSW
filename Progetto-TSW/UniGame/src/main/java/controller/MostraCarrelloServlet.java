package controller;

import model.DAO.RigaCarrelloDAO;
import model.DAO.VideogiocoDAO;
import model.RigaCarrello;
import model.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Videogioco;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/MostraCarrello")
public class MostraCarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utente userSession = (Utente) session.getAttribute("utente");

        List<RigaCarrello> righeCarrello = new ArrayList<>();

        if (userSession != null) {
            int idUtente = userSession.getIdUtente();
            RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();
            righeCarrello = rigaCarrelloDAO.doRetrieveByUtenteId(idUtente);

        } else {
            righeCarrello = (ArrayList<RigaCarrello>) session.getAttribute("guestCart");

            if (righeCarrello == null) {
                righeCarrello = new ArrayList<>();
            }
        }

        double prezzoUfficiale = 0;
        double scontoTotale = 0;
        double prezzoTotale = 0;
        VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        for (RigaCarrello riga : righeCarrello) {
            Videogioco videogioco = videogiocoDAO.doRetrieveById(riga.getIdVideogioco());
            double sconto = ((double) videogioco.getSconto() / 100) * videogioco.getPrezzo();
            prezzoUfficiale += videogioco.getPrezzo() * riga.getQuantità();
            scontoTotale += sconto * riga.getQuantità();
            prezzoTotale += (videogioco.getPrezzo() - sconto) * riga.getQuantità();
        }

        request.setAttribute("prezzoUfficiale", prezzoUfficiale);
        request.setAttribute("scontoTotale", scontoTotale);
        request.setAttribute("prezzoTotale", prezzoTotale);

        session.setAttribute("righeCarrello", righeCarrello);

        request.getRequestDispatcher("carrello.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}