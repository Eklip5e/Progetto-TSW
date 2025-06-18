<%@ page import="com.unigame.model.Videogioco" %>
<head>
  <link rel="stylesheet" href="css/modal.css">
</head>

<div id="modal-add-banner" class="modal-add-banner">
  <div class="modal-content">
    <div class="close-modal">
      <span onclick="chiudiModaleAddBanner()">x</span>
    </div>
    <h2>Nuovo Videogioco</h2>
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
      <input type="submit" value="Modifica">
    </form>
  </div>
</div>

<script>
  function apriModaleAddBanner() {
    document.getElementById("modal-add-banner").style.display = "flex";
    document.body.style.overflow = "hidden";
  }

  function chiudiModaleAddBanner() {
    document.getElementById("modal-add-banner").style.display = "none";
    document.body.style.overflow = "auto";
  }
</script>
