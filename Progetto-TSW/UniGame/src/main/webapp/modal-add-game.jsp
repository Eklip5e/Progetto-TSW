<head>
    <link rel="stylesheet" href="css/modal.css">
</head>

<div class="modal-add-game">
    <div class="modal-content">
        <span id="close-modal" onclick="chiudiModaleAddGame()">x</span>

        <h2>Nuovo Videogioco</h2>
        <form action="AggiungiVideogiocoServlet">
            <input name="titolo" type="text" placeholder="Titolo" required>

            <select name="piattaforma" required>
                <option value="">-- Seleziona una piattaforma --</option>

                <option value="pc">PC</option>
                <option value="playstation">PlayStation</option>
                <option value="xbox">Xbox</option>
                <option value="nintendo">Nintendo</option>
            </select>

            <input name="dataRilascio" type="text" placeholder="Data rilascio (dd/mm/yyyy)" pattern="\d\d\/\d\d\/\d\d\d\d" required>

            <textarea name="descrizione" placeholder="Descrizione" required></textarea>

            <input name="produttore" type="text" placeholder="Produttore" required>

            <input name="appIdSteam" type="text" placeholder="App ID Steam" required>

            <input name="prezzo" type="text" placeholder="Prezzo (€)" required>

            <input name="sconto" type="text" placeholder="Sconto (%)">

            <input id="add-button" type="submit" value="Aggiungi Videogioco">
        </form>
    </div>
</div>

<script>
    function apriModaleAddGame() {
        document.getElementsByClassName("modal-add-game")[0].style.display = "flex";
    }

    function chiudiModaleAddGame() {
        document.getElementsByClassName("modal-add-game")[0].style.display = "none";
    }
</script>

<script src="js/formatDateInput.js"></script>