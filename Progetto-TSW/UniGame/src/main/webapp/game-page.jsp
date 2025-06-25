<%@ page import="model.Videogioco" %>
<%@ page import="model.DAO.VideogiocoDAO" %>

<%
    request.setAttribute("paginaCorrente", "game-page.jsp");

    String id = request.getParameter("idVideogioco");
    Videogioco videogioco = new VideogiocoDAO().doRetrieveById(Integer.parseInt(id));
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
            <a id="game-cover" href="game-page.jsp?idVideogioco=<%=videogioco.getIdVideogioco()%>"><img src="https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/<%= videogioco.getAppIdSteam() %>/header.jpg" alt="<%= videogioco.getTitolo() %>"></a>
            <div class="game-info">
                <h2><%= videogioco.getTitolo() %></h2>
                <div class="platform">
                    <select>
                        <option value="pc">PC</option>
                        <option value="playstation">PlayStation</option>
                        <option value="xbox">Xbox</option>
                        <option value="nintendo">Nintendo</option>
                    </select>
                    <div class="stock">
                        <i class="fa-solid fa-check"></i>
                        <span>Disponibile</span>
                    </div>
                </div>
                    <div class="amount">
                        <div class="discount">
                            <span>-<%= videogioco.getSconto() %>%</span>
                        </div>
                        <div class="total">
                            <span><%= videogioco.getPrezzo() %> €</span>
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
                        <span id="label">Produttore:</span>
                        <span id="value"><%= videogioco.getProduttore() %></span>
                    </div>
                    <div class="list-item">
                        <span id="label">Data di rilascio:</span>
                        <span id="value"><%= videogioco.getDataRilascio() %></span>
                    </div>
                    <div class="list-item">
                        <span id="label">Genere:</span>
                        <span id="value"></span>
                    </div>
                </div>
            </div>
        </div>
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