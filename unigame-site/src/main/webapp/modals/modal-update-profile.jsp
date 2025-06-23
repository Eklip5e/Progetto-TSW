<head>
    <link rel="stylesheet" href="css/modal.css">
</head>

<div class="modal-update-profile">
    <div class="modal-content">
        <span id="close-modal" onclick="chiudiModaleUpdateProfile()">x</span>

        <h2>Modifica profilo</h2>
        <form action="modificaProfilo">
            <input type="text" name="username" placeholder="Nuovo username:" value="<%= utente.getUsername() %>" required>

            <input type="text" name="email" placeholder="Nuova email:" value="<%= utente.getEmail() %>" required>

            <input type="password" name="password" placeholder="Nuova password:" value="<%= utente.getPassword() %>" required>

            <input type="text" name="nome" placeholder="Nuovo nome:" value="<%= utente.getNome() %>" required>

            <input type="text" name="cognome" placeholder="Nuovo cognome:" value="<%= utente.getCognome() %>" required>

            <input type="text" name="dataDiNascita" placeholder="Nuova data di nascita:" pattern="\d\d\/\d\d\/\d\d\d\d" value="<%= utente.getDataDiNascita() %>" required>

            <input id="update-profile-button" type="submit" value="Modifica profilo">
        </form>
    </div>
</div>

<script>
    function apriModaleUpdateProfile() {
        document.getElementsByClassName("modal-update-profile")[0].style.display = "flex";
    }

    function chiudiModaleUpdateProfile() {
        document.getElementsByClassName("modal-update-profile")[0].style.display = "none";
    }
</script>

<script src="js/formatDateInput.js"></script>
