package com.unigame.controller;

import com.unigame.model.DAO.VideogiocoDAO;
import com.unigame.model.Videogioco;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.sql.Date;

@WebServlet("/AggiungiVideogiocoServlet")
public class AggiungiVideogiocoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Prendi i parametri dal form
        String titolo = request.getParameter("titolo");
        String piattaforma = request.getParameter("piattaforma");
        String dataRilascioStr = request.getParameter("rilascio");
        String descrizione = request.getParameter("descrizione");
        String produttore = request.getParameter("produttore");
        String appIdSteamStr = request.getParameter("appIdSteam");
        String prezzoStr = request.getParameter("prezzo");
        String scontoStr = request.getParameter("sconto");

        // Validazione semplice (puoi ampliare)
        if (titolo == null || titolo.isEmpty()
                || dataRilascioStr == null || dataRilascioStr.isEmpty()
                || descrizione == null || descrizione.isEmpty()
                || produttore == null || produttore.isEmpty()
                || appIdSteamStr == null || appIdSteamStr.isEmpty()
                || prezzoStr == null || prezzoStr.isEmpty()
                || scontoStr == null || scontoStr.isEmpty()) {

                request.setAttribute("error", "Tutti i campi obbligatori devono essere compilati.");
                request.getRequestDispatcher("home.jsp").forward(request, response);
                return;
        }

        try {
            Date dataRilascio = Date.valueOf(dataRilascioStr);
            int appIdSteam = Integer.parseInt(appIdSteamStr);
            double prezzo = Double.parseDouble(prezzoStr);
            int sconto = Integer.parseInt(scontoStr);

            Videogioco videogioco = new Videogioco();
            videogioco.setTitolo(titolo);
            videogioco.setPiattaforma(piattaforma);
            videogioco.setDataRilascio(dataRilascio);
            videogioco.setDescrizione(descrizione);
            videogioco.setProduttore(produttore);
            videogioco.setAppIdSteam(appIdSteam);
            videogioco.setPrezzo(prezzo);
            videogioco.setSconto(sconto);

            VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
            videogiocoDAO.doSave(videogioco);

            // Redirect alla home o pagina di conferma
            response.sendRedirect("home.jsp");

        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Formato data o numeri non validi.");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Errore interno: " + e.getMessage());
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }
}