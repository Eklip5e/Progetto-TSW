package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAO.RigaCarrelloDAO;
import model.DAO.VideogiocoDAO;
import model.RigaCarrello;
import model.RigaOrdine;
import model.Utente;
import model.Videogioco;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/pagamento")
public class PagamentoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            response.sendRedirect("home.jsp");
            return;
        }

        int idUtente = utente.getIdUtente();

        List<RigaCarrello> carrello;

        RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();
        carrello = rigaCarrelloDAO.doRetrieveByUtenteId(idUtente);


        double prezzoUfficiale = 0;
        double scontoTotale = 0;
        double prezzoTotale = 0;
        VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        for (RigaCarrello riga : carrello) {
            Videogioco videogioco = videogiocoDAO.doRetrieveById(riga.getIdVideogioco());
            double prezzoSingolo = riga.getPrezzoUnitario();

            prezzoUfficiale += videogioco.getPrezzo() * riga.getQuantità();
            scontoTotale += (videogioco.getPrezzo() - prezzoSingolo) * riga.getQuantità();
            prezzoTotale += prezzoSingolo * riga.getQuantità();
        }

        request.setAttribute("prezzoUfficiale", prezzoUfficiale);
        request.setAttribute("scontoTotale", scontoTotale);
        request.setAttribute("prezzoTotale", prezzoTotale);

        Map<Integer, Videogioco> videogiochiMap = new HashMap<>();
        for (RigaCarrello riga : carrello) {
            videogiochiMap.put(riga.getIdVideogioco(), videogiocoDAO.doRetrieveById(riga.getIdVideogioco()));
        }

        request.setAttribute("carrello", carrello);
        request.setAttribute("videogiochiMap", videogiochiMap);

        request.getRequestDispatcher("WEB-INF/pagamento.jsp").forward(request, response);
    }
}
