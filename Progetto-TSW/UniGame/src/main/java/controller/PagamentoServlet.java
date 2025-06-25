package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAO.VideogiocoDAO;
import model.RigaCarrello;
import model.Utente;
import model.Videogioco;

import java.io.IOException;
import java.util.List;

@WebServlet("/pagamento")
public class PagamentoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<RigaCarrello> righeCarrello = (List<RigaCarrello>) session.getAttribute("righeCarrello");

        if (righeCarrello == null || righeCarrello.isEmpty()) {
            response.sendRedirect("carrello.jsp?empty=true");
            return;
        }

        double prezzoUfficiale = 0;
        double scontoTotale = 0;
        double prezzoTotale = 0;
        VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        for (RigaCarrello riga : righeCarrello) {
            Videogioco videogioco = videogiocoDAO.doRetrieveById(riga.getIdVideogioco());
            double prezzoSingolo = riga.getPrezzoUnitario();

            prezzoUfficiale += videogioco.getPrezzo() * riga.getQuantità();
            scontoTotale += (videogioco.getPrezzo() - prezzoSingolo) * riga.getQuantità();
            prezzoTotale += prezzoSingolo * riga.getQuantità();
        }

        request.setAttribute("prezzoUfficiale", prezzoUfficiale);
        request.setAttribute("scontoTotale", scontoTotale);
        request.setAttribute("prezzoTotale", prezzoTotale);

        session.setAttribute("righeCarrello", righeCarrello);
        request.setAttribute("righeCarrello", righeCarrello);

        request.getRequestDispatcher("pagamento.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
