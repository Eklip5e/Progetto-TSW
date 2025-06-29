<%@ page import="model.Videogioco" %>
<%@ page import="model.DAO.VideogiocoDAO" %>
<%@ page import="model.RigaCarrello" %>

<%
    request.setAttribute("paginaCorrente", "game-page.jsp");

    String id = request.getParameter("idVideogioco");
    Videogioco videogioco = new VideogiocoDAO().doRetrieveById(Integer.parseInt(id));

    double prezzoUfficiale = 0;
    double scontoTotale = 0;
    double prezzoTotale = 0;

    double sconto = ((double) videogioco.getSconto() / 100) * videogioco.getPrezzo();
    prezzoUfficiale += videogioco.getPrezzo();
    scontoTotale += sconto;
    prezzoTotale += (videogioco.getPrezzo() - sconto);
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UniGame</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/game-page.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&family=Roboto&display=swap" rel="stylesheet">
</head>
<body>
    <main>

        <%@ include file="navbar.jsp" %>

        <!-- Game Banner Section -->
        <div class="game-banner" style="background-image: url('http://cdn.cloudflare.steamstatic.com/steam/apps/<%= videogioco.getAppIdSteam() %>/library_hero.jpg')"></div>

        <div class="game-page-content">
            <div id="game-cover" style="position: relative;">
                <img src="https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/<%= videogioco.getAppIdSteam() %>/header.jpg" alt="<%= videogioco.getTitolo() %>">
                <div class="admin-actions">
                    <a class="update-game" onclick="apriModaleUpdateGame()">
                        <i class="fa-solid fa-pen-nib"></i>
                    </a>
                    <a class="delete-game" href="deleteVideogame?idVideogame=<%= videogioco.getIdVideogioco() %>">
                        <i class="fa-solid fa-xmark"></i>
                    </a>
                </div>
            </div>
            <div class="game-info">
                <h2><%= videogioco.getTitolo() %></h2>
                    <div class="amount">
                        <div class="discount">
                            <span>-<%= videogioco.getSconto() %>%</span>
                        </div>
                        <div class="total">
                            <span><%= String.format("%.2f", prezzoTotale) %> €</span>
                        </div>
                    </div>
                    <div class="actions">
                        <a href="AggiungiAlCarrello?idVideogioco=<%=videogioco.getIdVideogioco()%>" class="add-to-cart">Aggiungi al carrello</a>
                    </div>
                </div>
            </div>

        <div class="details">
            <div class="product-text">
                <h2>Riguardo al prodotto</h2>
                <p><%= videogioco.getDescrizione() %></p>
            </div>
            <div class="specifics">
                <h2>Specifiche</h2>
                <div class="list">
                    <div class="list-item">
                        <span id="label">Installazione:</span>
                        <a id="value">Come attivare il prodotto</a>
                    </div>
                    <div class="list-item">
                        <span id="label">Piattaforma:</span>
                        <span id="value"><%= videogioco.getPiattaforma() %></span>
                    </div>
                    <div class="list-item">
                        <span id="label">Data di rilascio:</span>
                        <span id="value"><%= videogioco.getDataRilascio() %></span>
                    </div>
                    <div class="list-item">
                        <span id="label">Produttore:</span>
                        <span id="value"><%= videogioco.getProduttore() %></span>
                    </div>
                    <div class="list-item">
                        <span id="label">Genere:</span>
                        <span id="value"></span>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="modal-update-game.jsp" %>
    </main>

        <%@ include file="footer.jsp" %>

        <% String aggiunto = request.getParameter("aggiunto"); %>
        <%
            if ("true".equals(aggiunto)) {
        %>
                <div class="notifica success">
                    Prodotto aggiunto al carrello!
                </div>
        <%
            }
        %>
    </body>
</html>