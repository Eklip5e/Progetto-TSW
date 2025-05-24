<%@ page import="com.unigame.model.DAO.VideogiocoDAO" %>
<%@ page import="com.unigame.model.Videogioco" %>
<%@ page import="java.util.List" %>

<%
    VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
    List<Videogioco> videogiochi = videogiocoDAO.doRetrieveAll();
    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    String paginaAttuale = (String) request.getAttribute("paginaAttuale");
%>

<section class="game-grid">
    <%
        for (Videogioco videogioco : videogiochi) {
            if(videogioco.getPiattaforma().equalsIgnoreCase("home")) {
                if (videogioco.getPiattaforma().equalsIgnoreCase(paginaAttuale)) {
    %>
    <div class="game-card">
        <%
            if (isAdmin != null && isAdmin) {
        %>
        <button class="delete-button" data-id="<%=videogioco.getIDGame()%>">−</button> <!-- Bottone di rimozione -->
        <%
            }
        %>
        <a href="game-page.jsp"><img src="img/<%=videogioco.getCopertina()%>.png" alt="<%= videogioco.getTitolo() %>"></a>
        <h2><%= videogioco.getTitolo() %>
        </h2>
        <div class="card-price-row">
            <span class="discount-tag">-<%= videogioco.getSconto() %>%</span>
            <p class="price"><%=videogioco.getPrezzo()%> €</p>
        </div>
    </div>
    <%
                }
            } else {
    %>
    <div class="game-card">
        <%
            if (isAdmin != null && isAdmin) {
        %>
        <button class="delete-button" data-id="<%=videogioco.getIDGame()%>">−</button> <!-- Bottone di rimozione -->
        <%
            }
        %>
        <a href="game-page.jsp"><img src="img/<%=videogioco.getCopertina()%>.png" alt="<%= videogioco.getTitolo() %>"></a>
        <h2><%= videogioco.getTitolo() %>
        </h2>
        <div class="card-price-row">
            <span class="discount-tag">-<%= videogioco.getSconto() %>%</span>
            <p class="price"><%=videogioco.getPrezzo()%> €</p>
        </div>
    </div>
    <%
            }
            }
    %>

    <!-- Card per aggiungere un nuovo gioco -->
    <%
        if (isAdmin != null && isAdmin) {
    %>
    <div class="game-card add-game-card" onclick="document.getElementById('addModal').style.display='flex'">
        <div class="plus-sign">+</div>
        <p>Aggiungi gioco</p>
    </div>
    <% } %>
</section>
