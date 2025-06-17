<%@ page import="com.unigame.model.DAO.VideogiocoDAO" %>
<%@ page import="com.unigame.model.Videogioco" %>
<%@ page import="java.util.List" %>

<html>
    <head>
        <link rel="stylesheet" href="css/game-grid.css">
    </head>
    <body>
        <div class="game-grid">
            <div class="game-card card-add-game" onclick="apriModale()">
                <span class="plus">+</span>
            </div>

            <%
                for (Videogioco videogioco : videogiochi) {
            %>
                    <div class="game-card">
                        <a href="game-page.jsp?idVideogioco=<%=videogioco.getIdVideogioco()%>">
                            <img src="https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/<%= videogioco.getAppIdSteam() %>/header.jpg" alt="<%= videogioco.getTitolo() %>">
                            <span class="discount-tag">-<%= videogioco.getSconto() %>%</span>
                        </a>
                        <div class="game-card-content">
                            <h2><%= videogioco.getTitolo() %></h2>
                            <span class="price"><%=videogioco.getPrezzo()%> €</span>
                        </div>
                    </div>
            <%
                }
            %>
        </div>
    </body>
</html>
