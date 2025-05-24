<head>
    <link rel="stylesheet" href="css/modal.css">
</head>

<!-- Modale -->
<div id="addModal" class="modal" style="display: none">
    <div class="modal-content">
        <h2>Nuovo Videogioco</h2>
        <form action="AggiungiVideogiocoServlet" method="post">
            <input type="text" name="titolo" placeholder="Titolo" required><br>
            <label for="piattaforma">Piattaforma</label>
            <select name="piattaforma" id="piattaforma">
                <%
                    if("pc".equals(paginaAttuale)) {
                %>
                        <option value="pc">PC</option>
                <%
                    } else if("playstation".equals(paginaAttuale)) {
                %>
                        <option value="playstation">PlayStation</option>
                <%
                    } else if("xbox".equals(paginaAttuale)) {
                %>
                        <option value="xbox">Xbox</option>
                <%
                    } else if("nintendo".equals(paginaAttuale)) {
                %>
                        <option value="nintendo">Nintendo</option>
                <%
                    } else {
                %>
                        <option value="pc">PC</option>
                        <option value="playstation">PlayStation</option>
                        <option value="xbox">Xbox</option>
                        <option value="nintendo">Nintendo</option>
                <%
                    }
                %>
            </select>
            <input type="date" name="rilascio" required><br>
            <textarea name="descrizione" placeholder="Descrizione" required></textarea><br>
            <input type="text" name="produttore" placeholder="Produttore" required><br>
            <input type="text" name="appIdSteam" placeholder="Id SteamDB" required><br>
            <input type="text" step="0.01" name="prezzo" placeholder="Prezzo (€)" required><br>
            <input type="text" name="sconto" placeholder="Sconto (%)" value="0"><br>
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
    document.addEventListener("DOMContentLoaded", function () {
        const modal = document.getElementById('addModal');

        // Chiudi cliccando fuori dal modale
        window.onclick = function (event) {
            if (event.target === modal) {
                modal.style.display = 'none';
            }
        };

        <% if (request.getAttribute("error") != null) { %>
        // Mostra il modale se c'è un errore
        modal.style.display = 'flex';
        <% } %>
    });
</script>