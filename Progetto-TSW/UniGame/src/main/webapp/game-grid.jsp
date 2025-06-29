<%@ page import="model.Videogioco" %>
<%@ page import="model.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
    String paginaCorrente = (String) request.getAttribute("paginaCorrente");

    List<Videogioco> videogiochi = (List<Videogioco>) request.getAttribute("videogiochi");

    if (videogiochi == null) {
        videogiochi = new ArrayList<>();
    }

    Utente utente = (Utente) session.getAttribute("utente");
%>

<head>
    <%-- css --%>
    <link rel="stylesheet" href="css/game-grid.css">
</head>
        <div class="game-grid">
            <%
                if (utente != null && utente.isAdmin()) {
                    if (paginaCorrente.equals("home.jsp")) {
            %>
                        <div class="game-card card-add-game" onclick="apriModaleAddGame()">
                            <span class="plus">+</span>
                        </div>
            <%
                    }
                }
            %>

            <%
                for (Videogioco videogioco : videogiochi) {
            %>
                    <div class="game-card">
                        <a id="img" href="game-page.jsp?idVideogioco=<%= videogioco.getIdVideogioco() %>">
                            <img src="https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/<%= videogioco.getAppIdSteam() %>/header.jpg" alt="<%= videogioco.getTitolo() %>">
                        </a>
                        <%
                            if (paginaCorrente.equals("wish-list.jsp")) {
                        %>
                            <a href="removeFavorites?idVideogioco=<%= videogioco.getIdVideogioco() %>">
                                <i class="fa-solid fa-xmark"></i>
                            </a>
                        <%
                            }
                        %>
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
