package com.unigame.controller;

import com.unigame.model.DAO.ScreenshotDAO;
import com.unigame.model.DAO.VideogiocoDAO;
import com.unigame.model.Screenshot;
import com.unigame.model.Videogioco;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import com.unigame.model.Videogioco;
import com.unigame.model.DAO.VideogiocoDAO;
import com.unigame.model.DAO.ScreenshotDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import com.unigame.model.Videogioco;
import com.unigame.model.DAO.VideogiocoDAO;
import com.unigame.model.DAO.ScreenshotDAO;

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

        request.setCharacterEncoding("UTF-8");

        // Prendi i parametri dal form
        String titolo = request.getParameter("titolo");
        String piattaforma = request.getParameter("piattaforma");
        String dataRilascioStr = request.getParameter("rilascio");
        String descrizione = request.getParameter("descrizione");
        String copertina = request.getParameter("copertina");
        String prezzoStr = request.getParameter("prezzo");
        String scontoStr = request.getParameter("sconto");
        String produttore = request.getParameter("produttore");

        // Validazione semplice (puoi ampliare)
        if (titolo == null || titolo.isEmpty() || piattaforma == null || piattaforma.isEmpty()
                || dataRilascioStr == null || dataRilascioStr.isEmpty()
                || descrizione == null || descrizione.isEmpty()
                || copertina == null || copertina.isEmpty()
                || prezzoStr == null || prezzoStr.isEmpty()
                || scontoStr == null || scontoStr.isEmpty()
                || produttore == null || produttore.isEmpty()) {

            request.setAttribute("error", "Tutti i campi obbligatori devono essere compilati.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        try {
            Date dataRilascio = Date.valueOf(dataRilascioStr);  // parsing yyyy-MM-dd
            double prezzo = Double.parseDouble(prezzoStr);
            int sconto = Integer.parseInt(scontoStr);

            Videogioco videogioco = new Videogioco();
            videogioco.setTitolo(titolo);
            videogioco.setPiattaforma(piattaforma);
            videogioco.setDataRilascio(dataRilascio);
            videogioco.setDescrizione(descrizione);
            videogioco.setCopertina(copertina);
            videogioco.setPrezzo(prezzo);
            videogioco.setSconto(sconto);
            videogioco.setProduttore(produttore);

            VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
            videogiocoDAO.doSave(videogioco);

            // Ottieni l'id generato: nel tuo DAO devi modificarlo per tornare l'id (vedi nota sotto)
            int idVideogioco = videogioco.getIDGame();

            // Redirect alla home o pagina di conferma
            response.sendRedirect("home.jsp");

        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Formato data o numeri non validi.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Errore interno: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}