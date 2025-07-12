package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO.VideogiocoDAO;
import model.Videogioco;

import java.io.IOException;
import java.util.List;

@WebServlet("/attivaVideogioco")
public class AttivaVideogiocoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idVideogioco = Integer.parseInt(req.getParameter("idVideogioco"));

        VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        videogiocoDAO.Attiva(idVideogioco);

        resp.sendRedirect("home.jsp");
    }
}
