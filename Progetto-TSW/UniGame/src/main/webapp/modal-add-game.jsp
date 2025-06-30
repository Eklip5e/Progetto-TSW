<%
    String titoloVal = (String) request.getAttribute("titolo");
    String piattaformaVal = (String) request.getAttribute("piattaforma");
    String dataRilascioVal = (String) request.getAttribute("dataRilascio");
    String descrizioneVal = (String) request.getAttribute("descrizione");
    String produttoreVal = (String) request.getAttribute("produttore");
    String appIdSteamVal = (String) request.getAttribute("appIdSteam");
    String prezzoVal = (String) request.getAttribute("prezzo");
    String scontoVal = (String) request.getAttribute("sconto");

    // Evita null mostrando stringa vuota
    if (titoloVal == null) titoloVal = "";
    if (piattaformaVal == null) piattaformaVal = "";
    if (dataRilascioVal == null) dataRilascioVal = "";
    if (descrizioneVal == null) descrizioneVal = "";
    if (produttoreVal == null) produttoreVal = "";
    if (appIdSteamVal == null) appIdSteamVal = "";
    if (prezzoVal == null) prezzoVal = "";
    if (scontoVal == null) scontoVal = "";
%>

<head>
    <link rel="stylesheet" href="css/modal.css">
</head>

<div class="modal-add-game">
    <div class="modal-content">
        <span id="close-modal" onclick="chiudiModaleAddGame()">x</span>

        <h2>Nuovo Videogioco</h2>
        <form action="AggiungiVideogiocoServlet">

            <input name="titolo" type="text" placeholder="Titolo" required value="<%= titoloVal %>">

            <select name="piattaforma" required>
                <option value="" <%= "".equals(piattaformaVal) ? "selected" : "" %>>-- Seleziona una piattaforma --</option>
                <option value="pc" <%= "pc".equals(piattaformaVal) ? "selected" : "" %>>PC</option>
                <option value="playstation" <%= "playstation".equals(piattaformaVal) ? "selected" : "" %>>PlayStation</option>
                <option value="xbox" <%= "xbox".equals(piattaformaVal) ? "selected" : "" %>>Xbox</option>
                <option value="nintendo" <%= "nintendo".equals(piattaformaVal) ? "selected" : "" %>>Nintendo</option>
            </select>

            <input name="dataRilascio" type="text" placeholder="Data rilascio (DD/MM/AAAA)" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" required value="<%= dataRilascioVal %>">

            <textarea name="descrizione" placeholder="Descrizione" required><%= descrizioneVal %></textarea>

            <input name="produttore" type="text" placeholder="Produttore" required value="<%= produttoreVal %>">

            <input name="appIdSteam" type="text" placeholder="App ID Steam" required value="<%= appIdSteamVal %>">

            <input name="prezzo" type="text" placeholder="Prezzo (€)" required value="<%= prezzoVal %>">

            <input name="sconto" type="text" placeholder="Sconto (%)" required value="<%= scontoVal %>">

            <%
                if (request.getAttribute("error") != null) {
            %>
                    <p style="color: var(--color-error)"><%= request.getAttribute("error") %></p>
            <%
                }
            %>

            <input id="add-button" type="submit" value="Aggiungi Videogioco">
        </form>
        </form>
    </div>
</div>

<script>
    function apriModaleAddGame() {
        document.getElementsByClassName("modal-add-game")[0].style.display = "flex";
    }

    <%
        if (request.getAttribute("modalOpen") != null && (Boolean) request.getAttribute("modalOpen")) {
    %>
            apriModaleAddGame();
    <%
        }
    %>

    function chiudiModaleAddGame() {
        document.getElementsByClassName("modal-add-game")[0].style.display = "none";
    }

    document.addEventListener('DOMContentLoaded', function () {
        const dataInput = document.querySelector('input[name="dataRilascio"]');

        dataInput.addEventListener('input', function () {
            let value = dataInput.value.replace(/\D/g, '');

            if (value.length > 4) {
                value = value.slice(0, 2) + '/' + value.slice(2, 4) + '/' + value.slice(4, 8);
            } else if (value.length > 2) {
                value = value.slice(0, 2) + '/' + value.slice(2);
            }

            dataInput.value = value;
        });
    });
</script>

<script src="js/formatDateInput.js"></script>