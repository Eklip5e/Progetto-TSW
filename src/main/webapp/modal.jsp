<!-- Modale -->
<div id="addModal" class="modal" style="display: none">
    <div class="modal-content">
        <span class="close" onclick="document.getElementById('addModal').style.display='none'">&times;</span>
        <h2>Nuovo Videogioco</h2>
        <form action="AggiungiVideogiocoServlet" method="post">
            <input type="text" name="titolo" placeholder="Titolo" required><br>
            <input type="text" name="piattaforma" value="<@=>" required><br>
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