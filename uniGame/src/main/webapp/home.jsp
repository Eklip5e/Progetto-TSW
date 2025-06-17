<%@ page import="com.unigame.model.DAO.UtenteDAO" %>
<%@ page import="com.unigame.model.DAO.BannerDAO" %>
<%@ page import="com.unigame.model.Banner" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    VideogiocoDAO videogiocoDAO = new VideogiocoDAO();

    BannerDAO bannerDAO = new BannerDAO();
    Banner banner = bannerDAO.doRetrieveById(1);

    Videogioco videogiocoBanner = null;
    if (banner != null) {
        videogiocoBanner = videogiocoDAO.doRetrieveById(banner.getIdVideogioco());
    }

    List<Videogioco> videogiochi = videogiocoDAO.doRetrieveAll();

    Utente utente = (Utente) session.getAttribute("utente");
%>

<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>UniGame</title>

        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/home.css">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&family=Roboto&display=swap" rel="stylesheet">
    </head>
    <body>
        <%@ include file="navbar.jsp" %>

        <%
            if (videogiocoBanner != null) {
        %>
                <div class="banner" style="background: linear-gradient(to right, rgba(0, 0, 0, 0.9), rgba(0, 0, 0, 0.7) 40%, transparent 90%), url('http://cdn.cloudflare.steamstatic.com/steam/apps/<%= videogiocoBanner.getAppIdSteam() %>/library_hero.jpg') no-repeat center center / cover;">
                    <div class="banner-content">
                        <h1><%= videogiocoBanner.getTitolo() %></h1>
                        <div class="price-row">
                            <span class="discount-tag">-<%= videogiocoBanner.getSconto() %>%</span>
                            <p class="price"><%= videogiocoBanner.getPrezzo() %> €</p>
                        </div>
                    </div>

                    <div class="actions">
                        <button id="update-banner" onclick="apriModaleUpdate()">Modifica banner</button>
                    </div>
                </div>
        <%
            }
        %>

        <%@ include file="game-grid.jsp" %>

        <%@ include file="modal-add-game.jsp" %>

        <%@ include file="modal-update-game.jsp" %>

        <%@ include file="footer.jsp" %>
    </body>
</html>