package controller;

import model.DAO.VideogiocoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/RimuoviVideogiocoServlet")
public class RimuoviVideogiocoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idVideogiocoStr = request.getParameter("idVideogioco");

        if (idVideogiocoStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID mancante");
            return;
        }

        try {
            int idVideogioco = Integer.parseInt(idVideogiocoStr);
            VideogiocoDAO dao = new VideogiocoDAO();
            dao.doDelete(idVideogioco);

            request.getSession().setAttribute("messaggioConferma", "Videogioco eliminato correttamente!");
            response.sendRedirect("home.jsp");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID non valido");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore interno");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
