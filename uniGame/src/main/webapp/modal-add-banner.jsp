<%@ page import="com.unigame.model.Videogioco" %>

<head>
  <link rel="stylesheet" href="css/modal.css">
</head>

<div class="modal-add-banner">
  <div class="modal-content">
    <span id="close-modal" onclick="chiudiModaleAddBanner()">x</span>
    <h2>Aggiungi banner</h2>
    <form action="aggiungiBanner">
      <select name="videogioco">
        <option value="">-- Seleziona un videogioco --</option>
        <%
          for (Videogioco videogioco : videogiochi) {
        %>
            <option value="<%= videogioco.getIdVideogioco() %>"><%= videogioco.getTitolo() %></option>
        <%
          }
        %>
      </select>
      <input type="submit" value="Aggiungi">
    </form>
  </div>
</div>

<script>
  function apriModaleAddBanner() {
    document.getElementsByClassName("modal-add-banner")[0].style.display = "flex";
  }

  function chiudiModaleAddBanner() {
    document.getElementsByClassName("modal-add-banner")[0].style.display = "none";
  }
</script>
