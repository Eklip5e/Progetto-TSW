<link rel="stylesheet" href="css/modal.css">

<div class="modal-add-game">
  <div class="modal-container">
    <button id="close-modal" onclick="chiudiModaleUpdateGame()"><i class="fa-solid fa-xmark"></i></button>

    <h2>Modifica Videogioco</h2>
    <form action="modificaVideogioco">

      <input type="hidden" name="idVideogioco" value="${videogioco.idVideogioco}">

      <div class="form-group">
        <label for="titolo">Titolo</label>
        <input id="titolo" name="titolo" type="text" required value="${videogioco.titolo}">
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
        <input id="dataRilascio" name="dataRilascio" type="text" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" required placeholder="DD/MM/YYYY" value="<fmt:formatDate value="${videogioco.dataRilascio}" pattern="dd/MM/yyyy"/>">
      </div>

      <div class="form-group">
        <label for="descrizione">Descrizione</label>
        <textarea id="descrizione" name="descrizione" required>${videogioco.descrizione}</textarea>
      </div>

      <div class="form-group">
        <label for="produttore">Produttore</label>
        <input id="produttore" name="produttore" type="text" required value="${videogioco.produttore}">
      </div>

      <div class="form-group">
        <label for="genere">Genere</label>
        <input id="genere" name="genere" type="text" required value="${videogioco.genere}">
      </div>

      <div class="form-group">
        <label for="appIdSteam">AppIdSteam</label>
        <input id="appIdSteam" name="appIdSteam" type="text" required value="${videogioco.appIdSteam}">
      </div>

      <div class="form-group">
        <label for="prezzo">Prezzo</label>
        <input id="prezzo" name="prezzo" type="text" required value="<fmt:formatNumber value="${videogioco.prezzo}" type="number" minFractionDigits="2" maxFractionDigits="2"/>">
      </div>

      <div class="form-group">
        <label for="sconto">Sconto</label>
        <input id="sconto" name="sconto" type="text" value="${videogioco.sconto}">
      </div>

      <p style="color: var(--color-error)">${error}</p>

      <input id="button" type="submit" value="Modifica Videogioco">
    </form>
  </div>
</div>

<script src="js/releaseDateInput.js"></script>

<script>
  function apriModaleUpdateGame() {
    document.getElementsByClassName("modal-add-game")[0].style.display = "flex";
  }

  <%
      if (request.getAttribute("modalOpen") != null && (Boolean) request.getAttribute("modalOpen")) {
  %>
        apriModaleUpdateGame();
  <%
      }
  %>

  function chiudiModaleUpdateGame() {
    document.getElementsByClassName("modal-add-game")[0].style.display = "none";
  }
</script>