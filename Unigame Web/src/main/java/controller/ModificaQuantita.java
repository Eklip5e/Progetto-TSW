package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAO.RigaCarrelloDAO;
import model.RigaCarrello;
import model.Utente;

import java.io.IOException;
import java.util.List;

    @WebServlet("/modificaQuantita")
    public class ModificaQuantita extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            HttpSession session = request.getSession();
            Utente utente = (Utente) session.getAttribute("utente");

            String idParam = request.getParameter("idVideogioco");
            String quantitaParam = request.getParameter("quantita");

            boolean success = false;
            if (idParam != null && quantitaParam != null) {

                int idVideogioco = Integer.parseInt(idParam);
                int quantita = Integer.parseInt(quantitaParam);

                if (utente != null) {
                    RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();
                    success = rigaCarrelloDAO.aggiornaQuantita(utente.getIdUtente(), idVideogioco, quantita);
                } else {
                    List<RigaCarrello> righeCarrello = (List<RigaCarrello>) session.getAttribute("carrello");
                    for (RigaCarrello riga : righeCarrello) {
                        if (riga.getIdVideogioco() == idVideogioco) {
                            riga.setQuantità(quantita);
                            success = true;
                            break;
                        }
                    }
                }
            }

            org.json.simple.JSONObject json = new org.json.simple.JSONObject();
            json.put("success", success);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json.toJSONString());
        }
    }