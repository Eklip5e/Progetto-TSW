<%@ page import="com.unigame.model.Videogioco" %>
<%@ page import="com.unigame.model.DAO.VideogiocoDAO" %>

<%
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

<%
    String aggiunto = request.getParameter("aggiunto");
    if ("true".equals(aggiunto)) {
%>
<script>
    alert("Il gioco è stato aggiunto al carrello!");
</script>
<%
    }
%>

<%@ include file="navbar.jsp" %>

<!-- Game Banner Section -->
<section class="game-banner" style="background: linear-gradient(to right, rgba(0, 0, 0, 0.9), rgba(0, 0, 0, 0.7) 40%, transparent 90%), url('http://cdn.cloudflare.steamstatic.com/steam/apps/<%= videogioco.getAppIdSteam() %>/library_hero.jpg') no-repeat center center / cover;"></section>

<div class="content">
    <div class="panel-container">
        <div class="left-column">
            <div class="game-cover">
                <a href="game-page.jsp?idVideogioco=<%=videogioco.getIdVideogioco()%>">
                    <img src="https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/<%= videogioco.getAppIdSteam() %>/header.jpg" alt="<%= videogioco.getTitolo() %>">
                </a>
            </div>

            <div class="details">
                <div class="product-text">
                    <h2>Riguardo al prodotto</h2>
                    <p><%= videogioco.getDescrizione() %></p>
                </div>
            </div>
        </div>
        <div class="right-column">
        <div class="game-info">
            <h2><%= videogioco.getTitolo() %></h2>
            <div class="stock">
                <i class="fa-solid fa-check"></i>
                <span>Disponibile</span>
            </div>
            <select>
                <option value="pc">PC</option>
                <option value="playstation">PlayStation</option>
                <option value="xbox">Xbox</option>
                <option value="nintendo">Nintendo</option>
            </select>
            <div class="amount">
                <div class="discount">
                    <span>-<%= videogioco.getSconto() %>%</span>
                </div>
                <div class="total">
                    <span><%= videogioco.getPrezzo() %> €</span>
                </div>
            </div>
            <div class="actions">
                <a href="AggiungiAlCarrello?idVideogioco=<%=videogioco.getIdVideogioco()%>" class="add-to-cart">
                    <i class="fa-solid fa-cart-shopping"></i>
                    Aggiungi al carrello
                </a>
            </div>
        </div>
        </div>
    </div>
</div>
</main>

<%@ include file="footer.jsp" %>

</body>
</html>