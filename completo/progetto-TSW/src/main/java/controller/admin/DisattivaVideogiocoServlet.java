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

@WebServlet("/disattivaVideogioco")
public class DisattivaVideogiocoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idVideogiocoStr = req.getParameter("idVideogioco");
        if (idVideogiocoStr == null || idVideogiocoStr.isEmpty()) {
            resp.sendRedirect("error.jsp");
            return;
        }

        int  idVideogioco = Integer.parseInt(idVideogiocoStr);

        BannerDAO bannerDAO = new BannerDAO();
        Banner banner = bannerDAO.doRetrieveById(1);
        if (banner != null)
            if (banner.getIdVideogioco() == idVideogioco)
                bannerDAO.doDelete(1);

        VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        videogiocoDAO.doDelete(idVideogioco);

        resp.sendRedirect("home.jsp");
    }
}
