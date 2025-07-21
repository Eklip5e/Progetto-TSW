package controller;

import model.DAO.RigaCarrelloDAO;
import model.DAO.VideogiocoDAO;
import model.RigaCarrello;
import model.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Videogioco;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/aggiungiCarrello")
public class AggiungiCarrelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recupero sessione utente
        HttpSession session = req.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        int idVideogioco = Integer.parseInt(req.getParameter("idVideogioco"));
        VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        Videogioco videogioco = videogiocoDAO.doRetrieveById(idVideogioco);

        List<RigaCarrello> carrello;

        RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();

        if (utente != null) { // Utente registrato
            int idUtente = utente.getIdUtente();

            try {
                if (rigaCarrelloDAO.exists(idUtente, idVideogioco)) { // Carrello già esistente (aggiungi videogioco al carrello esistente)
                    rigaCarrelloDAO.incrementaQuantita(idUtente, idVideogioco, videogioco.getPrezzoScontato());
                } else { // Crea nuovo carrello e aggiungi il videogioco
                    RigaCarrello rigaCarrello = new RigaCarrello();
                    rigaCarrello.setPrezzoUnitario(videogioco.getPrezzoScontato());
                    rigaCarrello.setQuantità(1);
                    rigaCarrello.setIdUtente(idUtente);
                    rigaCarrello.setIdVideogioco(idVideogioco);

                    rigaCarrelloDAO.doSave(rigaCarrello);
                }
            } catch (Exception e) {
                resp.sendRedirect("error.jsp");
            }
        } else { // Utente non registrato
            carrello = (ArrayList<RigaCarrello>) session.getAttribute("carrello");
            if (carrello == null) { // Se non esiste un carrello attivo, crea un nuovo carrello
                carrello = new ArrayList<>();
            }

            boolean trovato = false;
            for (RigaCarrello riga : carrello) { // Se il videogioco è già presente nel carrello aggiorna la quantità
                if (riga.getIdVideogioco() == idVideogioco) {
                    riga.setQuantità(riga.getQuantità() + 1);
                    trovato = true;
                    break;
                }
            }

            if (!trovato) { // Se il videogioco non è presente nel carrello aggiungilo
                RigaCarrello nuovaRiga = new RigaCarrello();
                nuovaRiga.setIdVideogioco(idVideogioco);
                nuovaRiga.setQuantità(1);
                nuovaRiga.setPrezzoUnitario(videogioco.getPrezzoScontato());

                carrello.add(nuovaRiga);
            }

            // Salva il carrello in sessione
            session.setAttribute("carrello", carrello);
        }

        resp.sendRedirect("gamePage?idVideogioco=" + idVideogioco);
    }
}
