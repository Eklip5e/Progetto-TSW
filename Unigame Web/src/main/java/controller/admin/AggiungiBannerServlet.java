package controller.admin;

import model.Banner;
import model.DAO.BannerDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/aggiungiBanner")
public class AggiungiBannerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idVideogioco = Integer.parseInt(req.getParameter("videogiocoId"));

        try {
            Banner banner = new Banner();
            banner.setIdVideogioco(idVideogioco);

            BannerDAO bannerDAO = new BannerDAO();
            bannerDAO.doSave(banner);

            resp.sendRedirect("home.jsp");

        } catch (Exception e) {
            resp.sendRedirect("error.jsp");
        }
    }
}

