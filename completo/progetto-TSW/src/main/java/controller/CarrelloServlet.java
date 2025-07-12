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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/carrello")
public class CarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();
        VideogiocoDAO videogiocoDAO = new VideogiocoDAO();

        List<RigaCarrello> carrello;
        List<Videogioco> videogiochiCarrello = new ArrayList<>();

        if (utente != null) {
            int idUtente = utente.getIdUtente();
            carrello = rigaCarrelloDAO.doRetrieveByUtenteId(idUtente);

        } else {
            carrello = (List<RigaCarrello>) session.getAttribute("carrello");

            if (carrello == null) {
                carrello = new ArrayList<>();
            }
        }

        Map<Integer, RigaCarrello> carrelloMap = new HashMap<>();
        for (RigaCarrello riga : carrello) {
            carrelloMap.put(riga.getIdVideogioco(), riga);

            Videogioco videogioco = videogiocoDAO.doRetrieveById(riga.getIdVideogioco());
            if (videogioco != null) {
                videogiochiCarrello.add(videogioco);
            }
        }

        double prezzoUfficiale = 0;
        double scontoTotale = 0;
        double prezzoTotale = 0;

        for (RigaCarrello riga : carrello) {
            Videogioco videogioco =  videogiocoDAO.doRetrieveById(riga.getIdVideogioco());

            prezzoUfficiale += videogioco.getPrezzo() * riga.getQuantità();
            scontoTotale += (((double) videogioco.getSconto() / 100) * videogioco.getPrezzo()) * riga.getQuantità();
        }
        prezzoTotale = prezzoUfficiale - scontoTotale;

        request.setAttribute("prezzoUfficiale", prezzoUfficiale);
        request.setAttribute("scontoTotale", scontoTotale);
        request.setAttribute("prezzoTotale", prezzoTotale);

        request.setAttribute("carrello", carrello);
        request.setAttribute("videogiochi", videogiochiCarrello);
        request.setAttribute("carrelloMap", carrelloMap);

        request.getRequestDispatcher("WEB-INF/carrello.jsp").forward(request, response);
    }
}