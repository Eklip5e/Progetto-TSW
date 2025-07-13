<head>
    <link rel="stylesheet" href="css/modal.css">
</head>

<div class="modal-add-game">
    <div class="modal-container">
        <button id="close-modal" onclick="chiudiModaleAddGame()"><i class="fa-solid fa-xmark"></i></button>

        <h2>Nuovo Videogioco</h2>
        <form action="aggiungiVideogioco" method="post">

            <div class="form-group">
                <label for="titolo">Titolo</label>
                <input id="titolo" name="titolo" type="text" required value="${not empty titolo ? titolo : ''}">
            </div>

            <div class="form-group">
                <label for="piattaforma">Piattaforma</label>
                <select id="piattaforma" name="piattaforma" required>
                    <option value="PC" ${piattaforma == 'PC' ? "selected" : ""}>PC</option>
                    <option value="PlayStation" ${piattaforma == 'PlayStation' ? "selected" : ""}>PlayStation</option>
                    <option value="XBOX" ${piattaforma == 'XBOX' ? "selected" : ""}>Xbox</option>
                    <option value="Nintendo" ${piattaforma == 'Nintendo' ? "selected" : ""}>Nintendo</option>
                </select>
            </div>

            <div class="form-group">
                <label for="dataRilascio">Data rilascio</label>
                <input id="dataRilascio" name="dataRilascio" type="text" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" required placeholder="DD/MM/YYYY" value="${not empty dataRilascio ? dataRilascio : ''}">
            </div>

            <div class="form-group">
                <label for="descrizione">Descrizione</label>
                <textarea id="descrizione" name="descrizione" required>${not empty descrizione ? descrizione : ''}</textarea>
            </div>

            <div class="form-group">
                <label for="produttore">Produttore</label>
                <input id="produttore" name="produttore" type="text" required value="${not empty produttore ? produttore : ''}">
            </div>

            <div class="form-group">
                <label for="genere">Genere</label>
                <input id="genere" name="genere" type="text" placeholder="Genere" required value="${not empty genere ? genere : ''}">
            </div>

            <div class="form-group">
                <label for="appIdSteam">AppIdSteam</label>
                <input id="appIdSteam" name="appIdSteam" type="text" required value="${not empty appIdSteam ? appIdSteam : ''}">
            </div>

            <div class="form-group">
                <label for="prezzo">Prezzo</label>
                <input id="prezzo" name="prezzo" type="text" pattern="\d+(\.\d{1,2})?" required value="${not empty prezzo ? prezzo : ''}">
            </div>

            <div class="form-group">
                <label for="sconto">Sconto</label>
                <input id="sconto" name="sconto" type="text" pattern="\d+" required value="${not empty sconto ? sconto : ''}">
            </div>

            <p style="color: var(--color-error)">${error}</p>

            <input id="button" type="submit" value="Aggiungi Videogioco">
        </form>
    </div>
</div>

<script src="js/releaseDateInput.js"></script>

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
</script>