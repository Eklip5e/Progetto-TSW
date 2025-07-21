package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utente;
import model.Videogioco;
import model.DAO.OrdineDAO;
import model.DAO.RigaOrdineDAO;
import model.RigaOrdine;
import model.DAO.VideogiocoDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/ordine")
public class OrdineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente userSession = (Utente) request.getSession().getAttribute("utente");

        if (userSession == null) {
            response.sendRedirect("home.jsp");
            return;
        }

        int idUtente = userSession.getIdUtente();
        int idOrdine = Integer.parseInt(request.getParameter("idOrdine"));

        OrdineDAO ordineDAO = new OrdineDAO();
        RigaOrdineDAO rigaOrdineDAO = new RigaOrdineDAO();
        VideogiocoDAO videogiocoDAO = new VideogiocoDAO();

        List<RigaOrdine> righeOrdine = rigaOrdineDAO.doRetrieveByOrdineId(idOrdine);
        List<Videogioco> videogiochi = new ArrayList<>();

        for (RigaOrdine riga : righeOrdine) {
            Videogioco videogioco = videogiocoDAO.doRetrieveById(riga.getIdVideogioco());

            if (videogioco != null) {
                videogiochi.add(videogioco);
            }
        }

        List<String> chiavi = new ArrayList<>();
        for (RigaOrdine riga : righeOrdine) {
            chiavi.add(riga.getChiave());
        }

        request.setAttribute("idOrdine", idOrdine);
        request.setAttribute("videogiochi", videogiochi);
        request.setAttribute("chiavi", chiavi);

        request.getRequestDispatcher("WEB-INF/ordine.jsp").forward(request, response);
    }
}
