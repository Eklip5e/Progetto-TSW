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
        // Recupero la sessione dell'utente
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();
        VideogiocoDAO videogiocoDAO = new VideogiocoDAO();

        List<RigaCarrello> carrello;
        List<Videogioco> videogiochiCarrello = new ArrayList<>();

        if (utente != null) { // Recupero carrello utente registrato
            int idUtente = utente.getIdUtente();
            carrello = rigaCarrelloDAO.doRetrieveByUtenteId(idUtente);

        } else { // Recupero carrello utente non registrato
            carrello = (List<RigaCarrello>) session.getAttribute("carrello");

            if (carrello == null) { // Se non è presente nessun carrello attivo
                carrello = new ArrayList<>();
            }
        }

        // Map carrello, per recuperare nella jsp sia le informazioni di rigaCarrello e sia quelle del videogioco nel for
        Map<Integer, RigaCarrello> carrelloMap = new HashMap<>();
        for (RigaCarrello riga : carrello) {
            carrelloMap.put(riga.getIdVideogioco(), riga);

            Videogioco videogioco = videogiocoDAO.doRetrieveById(riga.getIdVideogioco());
            if (videogioco != null) {
                videogiochiCarrello.add(videogioco);
            }
        }

        // Codice per calcolare tutti i relativi prezzi nel carrello
        double prezzoUfficiale = 0;
        double scontoTotale = 0;
        double prezzoTotale = 0;
        double tuttoTotale = 0;
        int numeroarticoli = 0;

        for (RigaCarrello riga : carrello) {
            Videogioco videogioco =  videogiocoDAO.doRetrieveById(riga.getIdVideogioco());

            prezzoUfficiale += videogioco.getPrezzo() * riga.getQuantità();
            scontoTotale += (((double) videogioco.getSconto() / 100) * videogioco.getPrezzo()) * riga.getQuantità();
            if (riga.getQuantità() > 1)
            {
                numeroarticoli= numeroarticoli+ riga.getQuantità();
            }
            else
                numeroarticoli++;

        }
        prezzoTotale = prezzoUfficiale - scontoTotale;
        if (numeroarticoli >= 5) {
            double applicazionesconto = (prezzoTotale * 10)/100;
            tuttoTotale = prezzoTotale - applicazionesconto;
        }

        request.setAttribute("numeroarticoli", numeroarticoli);
        request.setAttribute("prezzoUfficiale", prezzoUfficiale);
        request.setAttribute("scontoTotale", scontoTotale);
        request.setAttribute("prezzoTotale", prezzoTotale);
        request.setAttribute("tuttoTotale", tuttoTotale);

        request.setAttribute("carrello", carrello);
        request.setAttribute("videogiochi", videogiochiCarrello);
        request.setAttribute("carrelloMap", carrelloMap);

        request.getRequestDispatcher("WEB-INF/carrello.jsp").forward(request, response);
    }
}