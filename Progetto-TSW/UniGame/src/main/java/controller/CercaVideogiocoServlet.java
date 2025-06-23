package controller;

import model.DAO.VideogiocoDAO;
import model.Videogioco;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/cercaVideogiochi")
public class CercaVideogiocoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String titolo = request.getParameter("titolo");
        String piattaforma = request.getParameter("piattaforma");
        String genere = request.getParameter("genere");

        String prezzoMinStr = request.getParameter("prezzoMin");
        String prezzoMaxStr = request.getParameter("prezzoMax");

        Double prezzoMin = null;
        Double prezzoMax = null;

        try {
            if (prezzoMinStr != null && !prezzoMinStr.isEmpty()) {
                prezzoMin = Double.parseDouble(prezzoMinStr);
            }
            if (prezzoMaxStr != null && !prezzoMaxStr.isEmpty()) {
                prezzoMax = Double.parseDouble(prezzoMaxStr);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errore", "Inserisci solo numeri nei campi prezzo.");
            request.getRequestDispatcher("search-page.jsp").forward(request, response);
            return;
        }

        VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        List<Videogioco> videogiochi = null;
        try {
            videogiochi = videogiocoDAO.doRetrieveByFiltro(
                    titolo, piattaforma, genere, prezzoMin, prezzoMax
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("videogiochi", videogiochi);

        request.getRequestDispatcher("game-grid.jsp").forward(request, response);
    }
}
