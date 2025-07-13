<%
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
    String dataFormattata = sdf.format(videogioco.getDataRilascio());
%>

<head>
  <link rel="stylesheet" href="css/modal.css">
</head>

<div class="modal-add-game">
  <div class="modal-content">
    <span id="close-modal" onclick="chiudiModaleUpdateGame()">x</span>

    <h2>Modifica Videogioco</h2>
    <form action="modificaVideogioco">

      <input type="hidden" name="idVideogioco" value="<%= videogioco.getIdVideogioco() %>">

      <input name="titolo" type="text" placeholder="Titolo" required value="<%= videogioco.getTitolo() %>">

      <select name="piattaforma" required>
        <option value="">-- Seleziona una piattaforma --</option>

        <option value="pc" <%= "pc".equalsIgnoreCase(videogioco.getPiattaforma()) ? "selected" : "" %>>PC</option>
        <option value="playstation" <%= "playstation".equalsIgnoreCase(videogioco.getPiattaforma()) ? "selected" : "" %>>PlayStation</option>
        <option value="xbox" <%= "xbox".equalsIgnoreCase(videogioco.getPiattaforma()) ? "selected" : "" %>>Xbox</option>
        <option value="nintendo" <%= "nintendo".equalsIgnoreCase(videogioco.getPiattaforma()) ? "selected" : "" %>>Nintendo</option>
      </select>

      <input name="dataRilascio" type="text" placeholder="Data rilascio (dd/mm/yyyy)" pattern="\d\d\/\d\d\/\d\d\d\d" required value="<%= dataFormattata %>">

      <textarea name="descrizione" placeholder="Descrizione" required><%= videogioco.getDescrizione() %></textarea>

      <input name="produttore" type="text" placeholder="Produttore" required value="<%= videogioco.getProduttore() %>">

      <input name="appIdSteam" type="text" placeholder="App ID Steam" required value="<%= videogioco.getAppIdSteam() %>">

      <input name="prezzo" type="text" placeholder="Prezzo (€)" required value="<%= videogioco.getPrezzo() %>">

      <input name="sconto" type="text" placeholder="Sconto (%)" value="<%= videogioco.getSconto() %>">

      <input id="add-button" type="submit" value="Modifica Videogioco">
    </form>
  </div>
</div>

<script>
  function apriModaleUpdateGame() {
    document.getElementsByClassName("modal-add-game")[0].style.display = "flex";
  }

  function chiudiModaleUpdateGame() {
    document.getElementsByClassName("modal-add-game")[0].style.display = "none";
  }
</script>

<script src="js/formatDateInput.js"></script>