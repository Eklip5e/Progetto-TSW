package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO.FatturazioneDAO;
import model.DAO.RigaCarrelloDAO;
import model.Fatturazione;
import model.RigaCarrello;
import model.Utente;
import model.acquisto.DAO.OrdineDAO;
import model.acquisto.DAO.RigaOrdineDAO;
import model.acquisto.Ordine;
import model.acquisto.RigaOrdine;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/confermaAcquisto")
public class ConfermaAcquistoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente userSession = (Utente) request.getSession().getAttribute("utente");

        int idUtente = userSession.getIdUtente();

        RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();
        List<RigaCarrello> giochiAcquistati = rigaCarrelloDAO.doRetrieveByUtenteId(idUtente);

        String numeroCarta =  request.getParameter("numeroCarta");
        String titolare =  request.getParameter("titolare");
        String scadenzaStr = request.getParameter("scadenza");
        String cvc =  request.getParameter("cvc");

        if (!isValid(numeroCarta, titolare, scadenzaStr, cvc)) {
            request.setAttribute("error", "Tutti i campi sono obbligatori");
            request.getRequestDispatcher("pagamento.jsp").forward(request, response);
            return;
        }

        java.util.Date scadenza;
        try {
            scadenza = new java.text.SimpleDateFormat("MM/yy").parse(scadenzaStr);
        } catch (java.text.ParseException e) {
            request.setAttribute("error", "Formato data non valido.");
            request.getRequestDispatcher("pagamento.jsp").forward(request, response);
            return;
        }

        Fatturazione fatturazione = new Fatturazione();
        fatturazione.setNumero(numeroCarta);
        fatturazione.setTitolare(titolare);
        fatturazione.setScadenza(scadenza);
        fatturazione.setCvc(cvc);
        fatturazione.setIdUtente(idUtente);

        FatturazioneDAO fatturazioneDAO = new FatturazioneDAO();
        fatturazioneDAO.doSave(fatturazione);

        double totale = 0;
        for(RigaCarrello riga : giochiAcquistati){
            totale += riga.getPrezzoUnitario() * riga.getQuantità();
        }

        Ordine ordine = new Ordine();
        ordine.setDataOrdine(new Date());
        ordine.setStato("In elaborazione");
        ordine.setTotale(totale);
        ordine.setIdUtente(idUtente);
        ordine.setIdFatturazione(fatturazione.getIdFatturazione());

        OrdineDAO ordineDAO = new OrdineDAO();
        ordineDAO.doSave(ordine);

        RigaOrdineDAO rigaOrdineDAO = new RigaOrdineDAO();

        for (RigaCarrello rigaCarrello : giochiAcquistati) {
            int quantita = rigaCarrello.getQuantità();
            for (int i = 0; i < quantita; i++) {
                RigaOrdine rigaOrdine = new RigaOrdine();
                rigaOrdine.setIdOrdine(ordine.getIdOrdine());
                rigaOrdine.setIdVideogioco(rigaCarrello.getIdVideogioco());
                rigaOrdine.setChiave(generaChiaveAcquisto());
                rigaOrdine.setPrezzoUnitario(rigaCarrello.getPrezzoUnitario());

                rigaOrdineDAO.doSave(rigaOrdine);
            }
        }

        rigaCarrelloDAO.doDeleteById(idUtente);

        List<RigaOrdine> righeOrdine = rigaOrdineDAO.doRetrieveByOrdineId(ordine.getIdOrdine());
        request.setAttribute("righeOrdine", righeOrdine);
        request.getRequestDispatcher("attivazione.jsp").forward(request, response);
    }

    private String generaChiaveAcquisto() {
        return java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 32);
    }

    public boolean isValid(String numeroCarta, String titolare, String scadenza, String cvc) {
        return numeroCarta != null && !numeroCarta.isEmpty()
                && titolare != null && !titolare.isEmpty()
                && scadenza != null && !scadenza.isEmpty()
                && cvc != null && !cvc.isEmpty();
    }
}