<%@ page import="com.unigame.model.Videogioco" %>
<%@ page import="java.util.List" %>
<%@ page import="com.unigame.model.DAO.VideogiocoDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
    List<Videogioco> videogiochi = videogiocoDAO.doRetrieveAll();
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UniGame</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<%@ include file="../navbar.jsp" %>


<!-- Game Grid Section -->
<section class="game-grid">
    <% for (Videogioco videogioco : videogiochi) {
        if (videogioco.getPiattaforma().equalsIgnoreCase("xbox")) {%>
    <div class="game-card">
        <%
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
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
    <% }
    } %>

    <!-- Card per aggiungere un nuovo gioco -->
    <%
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin != null && isAdmin) {
    %>
    <div class="game-card add-game-card" onclick="document.getElementById('addModal').style.display='flex'">
        <div class="plus-sign">+</div>
        <p>Aggiungi gioco</p>
    </div>
    <% } %>
</section>

<!-- Modale -->
<div id="addModal" class="modal" style="display: none">
    <div class="modal-content">
        <span class="close" onclick="document.getElementById('addModal').style.display='none'">&times;</span>
        <h2>Nuovo Videogioco</h2>
        <form action="AggiungiVideogiocoServlet" method="post">
            <input type="text" name="titolo" placeholder="Titolo" required><br>
            <input type="text" name="piattaforma" value="xbox" disabled><br>
            <input type="date" name="rilascio" required><br>
            <textarea name="descrizione" placeholder="Descrizione" required></textarea><br>
            <input type="text" name="copertina" placeholder="copertina" required><br>
            <input type="text" step="0.01" name="prezzo" placeholder="Prezzo (€)" required><br>
            <input type="text" name="sconto" placeholder="Sconto (%)" value="0"><br>
            <input type="text" name="produttore" placeholder="Produttore" required><br>
            <button type="submit">Aggiungi</button>
        </form>

        <% if (request.getAttribute("error") != null) { %>
        <div class="error-message">
            <%= request.getAttribute("error") %>
        </div>
        <% } %>

    </div>
</div>

<script>
    function closeAddModal() {
        document.getElementById('addModal').style.display = 'none';
    }

    window.onclick = function (event) {
        let modal = document.getElementById('addModal');
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    }
</script>

<script>
    <% if (request.getAttribute("error") != null) { %>
    document.addEventListener("DOMContentLoaded", function () {
        document.getElementById('addModal').style.display = 'flex';
    });
    <% } %>
</script>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        document.querySelectorAll(".delete-button").forEach(button => {
            button.addEventListener("click", function () {
                const idGame = this.getAttribute("data-id");

                const conferma = confirm("Sei sicuro di voler eliminare questo videogioco?");
                if (!conferma) {
                    return; // Annulla l'operazione se l'utente clicca "Annulla"
                }

                fetch("RimuoviVideogiocoServlet?id=" + idGame)
                    .then(res => {
                        if (res.ok) {
                            location.reload(); // Ricarica la pagina dopo l’eliminazione
                        } else {
                            alert("Errore durante la rimozione del videogioco.");
                        }
                    });
            });
        });
    });
</script>

<%@ include file="../footer.jsp" %>

</body>
</html>