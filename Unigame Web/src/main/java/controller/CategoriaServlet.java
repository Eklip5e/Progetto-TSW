package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO.VideogiocoDAO;
import model.Videogioco;

import java.io.IOException;
import java.util.List;

@WebServlet("/categoria")
public class CategoriaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // In base alla categoria selezionata mostrerà giochi solo di una specifica piattaforma

        String piattaforma =  request.getParameter("piattaforma");

        VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        List<Videogioco> videogiochi = videogiocoDAO.doRetrieveByPiattaformaAttivi(piattaforma); // Recupera tutti i videogiochi della piattaforma selezionata

        request.setAttribute("videogiochi", videogiochi);
        request.setAttribute("piattaforma", piattaforma);

        request.getRequestDispatcher("WEB-INF/categorie.jsp").forward(request, response);
    }
}
