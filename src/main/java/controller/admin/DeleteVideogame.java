package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Banner;
import model.DAO.BannerDAO;
import model.DAO.VideogiocoDAO;
import model.Videogioco;

import java.io.IOException;
import java.util.List;

@WebServlet("/deleteVideogame")
public class DeleteVideogame extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idVideogameStr = req.getParameter("idVideogame");

        int idVideogame;
        if (idVideogameStr != null){
            try {
                idVideogame = Integer.parseInt(idVideogameStr);
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID non valido");
                return;
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parametro mancante");
            return;
        }

        BannerDAO bannerDAO = new BannerDAO();
        Banner banner = bannerDAO.doRetrieveById(1);

        if (banner.getIdVideogioco() == idVideogame) {
            bannerDAO.doDelete(1);
        }

        VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        Videogioco videogioco = videogiocoDAO.doRetrieveById(idVideogame);

        videogiocoDAO.doDelete(idVideogame);

        List<Videogioco> videogiochi = videogiocoDAO.doRetrieveAllActive();

        req.setAttribute("videogiochi", videogiochi);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
