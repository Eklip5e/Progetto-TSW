<%@ page import="model.DAO.VideogiocoDAO" %>
<%@ page import="model.Videogioco" %>
<%@ page import="model.DAO.BannerDAO" %>
<%@ page import="model.Banner" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    request.setAttribute("paginaCorrente", "home.jsp");

    VideogiocoDAO videogiocoDAO = new VideogiocoDAO();

    BannerDAO bannerDAO = new BannerDAO();
    Banner banner = bannerDAO.doRetrieveById(1);

    Videogioco videogiocoBanner = null;
    if (banner != null) {
        videogiocoBanner = videogiocoDAO.doRetrieveById(banner.getIdVideogioco());
    }

    request.setAttribute("videogiochi", videogiocoDAO.doRetrieveAll());
%>

<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>UniGame</title>

        <%-- css --%>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/home.css">

        <%-- font-awesome --%>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

        <%-- google-font --%>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&family=Roboto&display=swap" rel="stylesheet">
    </head>
    <body>
    <%@ include file="navbar.jsp" %>
        <main>
            <%-- nav-bar --%>

            <%
                if (videogiocoBanner != null) {
            %>
                    <div class="banner" style="background: url('http://cdn.cloudflare.steamstatic.com/steam/apps/<%= videogiocoBanner.getAppIdSteam() %>/library_hero.jpg')">

                        <%
                            if (userSession != null && userSession.isAdmin()) {
                        %>
                                <div class="action">
                                    <i class="fa-solid fa-pen-nib" onclick="apriModaleUpdateBanner()"></i>
                                </div>
                        <%
                            }
                        %>
                        <div class="banner-content">
                            <h1><%= videogiocoBanner.getTitolo() %></h1>

                            <div class="price-row">
                                <span class="discount-tag">-<%= videogiocoBanner.getSconto() %>%</span>
                                <p class="price"><%= videogiocoBanner.getPrezzo() %> €</p>
                            </div>
                        </div>
                    </div>
            <%
                } else {
            %>
                    <div class="banner-not-found">
                        <h1>Banner non disponibile</h1>

                        <%
                            if (userSession != null && userSession.isAdmin()) {
                        %>
                                <div class="action">
                                    <i class="fa-solid fa-plus" onclick="apriModaleAddBanner()"></i>
                                </div>
                        <%
                            }
                        %>
                    </div>
            <%
                }
            %>

            <%@ include file="game-grid.jsp" %>

            <%-- modali --%>
            <%@ include file="modal-add-banner.jsp" %>

            <%@ include file="modal-update-banner.jsp" %>

            <%@ include file="modal-add-game.jsp" %>
        </main>

        <%@ include file="footer.jsp" %>

    </body>
</html>