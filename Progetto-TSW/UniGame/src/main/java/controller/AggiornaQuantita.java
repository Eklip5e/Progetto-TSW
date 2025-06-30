package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.RigaCarrello;
import org.json.simple.JSONObject;
import java.io.IOException;
import java.util.List;

@WebServlet("/aggiornaQuantita")
public class AggiornaQuantita extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("idVideogioco");
        String quantitaParam = request.getParameter("quantita");

        boolean success = false;

        if (idParam != null && quantitaParam != null) {
            try {
                int idVideogioco = Integer.parseInt(idParam);
                int quantita = Integer.parseInt(quantitaParam);

                HttpSession session = request.getSession();
                List<RigaCarrello> righeCarrello = (List<RigaCarrello>) session.getAttribute("righeCarrello");

                if (righeCarrello != null) {
                    for (RigaCarrello riga : righeCarrello) {
                        if (riga.getIdVideogioco() == idVideogioco) {
                            riga.setQuantità(quantita);
                            success = true;
                            break;
                        }
                    }
                }
            } catch (NumberFormatException e) {
                success = false;
            }
        }

        // Creazione della risposta JSON usando JSON.simple
        JSONObject json = new JSONObject();
        json.put("success", success);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toJSONString());
    }
}