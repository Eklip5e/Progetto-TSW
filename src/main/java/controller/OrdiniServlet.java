package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO.MetodoPagamentoDAO;
import model.Fatturazione;
import model.Utente;
import model.DAO.OrdineDAO;
import model.DAO.RigaOrdineDAO;
import model.Ordine;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/ordini")
public class OrdiniServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente userSession = (Utente) request.getSession().getAttribute("utente");

        if (userSession == null) {
            response.sendRedirect("home.jsp");
            return;
        }

        int idUtente = userSession.getIdUtente();

        OrdineDAO ordineDAO = new OrdineDAO();
        RigaOrdineDAO rigaOrdineDAO = new RigaOrdineDAO();
        MetodoPagamentoDAO metodoPagamentoDAO = new MetodoPagamentoDAO();

        List<Ordine> ordini = ordineDAO.doRetrieveByIdUtente(idUtente);

        Map<Integer, Fatturazione> fatturazioni = new HashMap<>();
        for (Ordine o : ordini) {
            Fatturazione fatturazione = metodoPagamentoDAO.doRetrieveById(o.getIdFatturazione());
            fatturazioni.put(o.getIdOrdine(), fatturazione);
        }

        request.setAttribute("ordini", ordini);
        request.setAttribute("fatturazioni", fatturazioni);

        request.getRequestDispatcher("WEB-INF/ordini.jsp").forward(request, response);
    }
}
