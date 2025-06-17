<head>
    <link rel="stylesheet" href="css/modal.css">
</head>

<div id="modal-add-game" class="modal-add-game">
    <div class="modal-content">
        <div class="close-modal">
            <span onclick="chiudiModale()">x</span>
        </div>
        <h2>Nuovo Videogioco</h2>
        <form action="AggiungiVideogiocoServlet">
            <input placeholder="Titolo:" name="titolo" type="text" required>

            <select name="piattaforma">
                <option value="pc">PC</option>
                <option value="playstation">PlayStation</option>
                <option value="xbox">Xbox</option>
                <option value="nintendo">Nintendo</option>
            </select>

            <input placeholder="Data rilascio (dd/mm/yyyy)" id="dataDiNascita" name="dataDiNascita" type="text" pattern="\d\d\/\d\d/\d\d\d\d" required="">
            <textarea placeholder="Descrizione:" name="descrizione" required></textarea>
            <input type="text" name="produttore" placeholder="Produttore" required>
            <input placeholder="Id DB:" name="idDB" type="text" required>
            <input placeholder="Prezzo:" name="prezzo" type="text" required>
            <input placeholder="Sconto:" name="sconto" type="text">
            <input id="add-button" type="submit" value="Aggiungi Videogioco">
        </form>
    </div>
</div>

<script>
    function apriModale() {
        document.getElementById("modal-add-game").style.display = "flex";
        document.body.style.overflow = "hidden";
    }

    function chiudiModale() {
        document.getElementById("modal-add-game").style.display = "none";
        document.body.style.overflow = "auto";
    }
</script>