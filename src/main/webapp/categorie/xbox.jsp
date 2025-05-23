<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 23/05/2025
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- Game Grid Section -->
<section class="game-grid">
    <% for (Videogioco videogioco : videogiochi) { %>
    <div class="game-card">
        <%
            isAdmin = (Boolean) session.getAttribute("isAdmin");
            if (isAdmin != null && isAdmin) {
        %>
        <button class="delete-button" data-id="<%=videogioco.getIDGame()%>">−</button> <!-- Bottone di rimozione -->
        <% } %>
        <img src="img/<%=videogioco.getCopertina()%>.png" alt="<%= videogioco.getTitolo() %>">
        <h2><%= videogioco.getTitolo() %>
        </h2>
        <div class="card-price-row">
            <span class="discount-tag">-<%= videogioco.getSconto() %>%</span>
            <p class="price"><%=videogioco.getPrezzo()%> €</p>
        </div>
    </div>
    <% } %>

    <!-- Card per aggiungere un nuovo gioco -->
    <%
        isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin != null && isAdmin) {
    %>
    <div class="game-card add-game-card" onclick="document.getElementById('addModal').style.display='flex'">
        <div class="plus-sign">+</div>
        <p>Aggiungi gioco</p>
    </div>
    <% } %>
</section>
</body>
</html>
