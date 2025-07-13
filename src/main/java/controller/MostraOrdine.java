package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utente;
import model.acquisto.DAO.OrdineDAO;
import model.acquisto.DAO.RigaOrdineDAO;
import model.acquisto.Ordine;
import model.acquisto.RigaOrdine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/mostraOrdine")
public class MostraOrdine extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente userSession = (Utente) request.getSession().getAttribute("utente");

        int idUtente = userSession.getIdUtente();
        int idOrdine = Integer.parseInt(request.getParameter("idOrdine"));

        OrdineDAO ordineDAO = new OrdineDAO();
        RigaOrdineDAO rigaOrdineDAO = new RigaOrdineDAO();

        List<RigaOrdine> righeOrdine = rigaOrdineDAO.doRetrieveByOrdineId(idOrdine);

        request.setAttribute("idOrdine", idOrdine);
        request.setAttribute("righeOrdine", righeOrdine);
        request.getRequestDispatcher("ordine.jsp").forward(request, response);
    }
}
