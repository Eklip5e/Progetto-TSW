<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="css/modal.css">

<div class="modal-add-banner">
  <div class="modal-container">
    <span id="close-modal" onclick="chiudiModaleAddBanner()"><i class="fa-solid fa-xmark"></i></span>
    <h2>Aggiungi banner</h2>

    <form action="aggiungiBanner" method="post">
      <div class="form-group">
        <label for="videogiocoId">Videogioco</label>
        <select id="videogiocoId" name="videogiocoId">
          <c:forEach var="videogioco" items="${videogiochi}">
            <option value="${videogioco.idVideogioco}">${videogioco.titolo}</option>
          </c:forEach>
        </select>
      </div>
      <input id="button" type="submit" value="Aggiungi">
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
