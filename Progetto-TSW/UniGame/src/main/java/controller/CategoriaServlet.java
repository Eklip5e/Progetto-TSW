package controller;

import com.mysql.cj.Session;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CategoriaServlet")
public class CategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoria = request.getParameter("categoria");

        String paginaDestinazione;

        switch (categoria) {
            case "pc":
                paginaDestinazione = "categorie/pc.jsp";
                request.setAttribute("paginaAttuale", "pc");
                break;
            case "playstation":
                paginaDestinazione = "categorie/playstation.jsp";
                request.setAttribute("paginaAttuale", "playstation");
                break;
            case "xbox":
                paginaDestinazione = "categorie/xbox.jsp";
                request.setAttribute("paginaAttuale", "xbox");
                break;
            case "nintendo":
                paginaDestinazione = "categorie/nintendo.jsp";
                request.setAttribute("paginaAttuale", "nintendo");
                break;
            default:
                paginaDestinazione = "home.jsp";
                request.setAttribute("paginaAttuale", "home");
                break;
        }

        // Puoi anche passare attributi alla JSP se ti servono
        request.getRequestDispatcher(paginaDestinazione).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
