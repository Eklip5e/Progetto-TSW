<%@ page import="com.unigame.model.Videogioco" %>
<%@ page import="java.util.List" %>

<%
    List<Videogioco> videogiochi = videogiocoDAO.doRetrieveAll();
%>

<html>
    <head>
        <%-- css --%>
        <link rel="stylesheet" href="css/game-grid.css">
    </head>

    <body>
        <div class="game-grid">
            <%
                if (utente != null && utente.isAdmin()) {
            %>
                    <div class="game-card card-add-game" onclick="apriModale()">
                        <span class="plus">+</span>
                    </div>
            <%
                }
            %>

            <%
                for (Videogioco videogioco : videogiochi) {
            %>
                    <div class="game-card">
                        <a href="game-page.jsp?idVideogioco=<%= videogioco.getIdVideogioco() %>">
                            <img src="https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/<%= videogioco.getAppIdSteam() %>/header.jpg" alt="<%= videogioco.getTitolo() %>">
                        </a>
                        <div class="game-card-content">
                            <h2><%= videogioco.getTitolo() %> (<%= videogioco.getPiattaforma() %>)</h2>

                            <div class="price-row">
                                <span class="discount-tag">-<%= videogioco.getSconto() %>%</span>
                                <span class="price"><%=videogioco.getPrezzo()%> €</span>
                            </div>
                        </div>
                    </div>
            <%
                }
            %>
        </div>
    </body>
</html>
