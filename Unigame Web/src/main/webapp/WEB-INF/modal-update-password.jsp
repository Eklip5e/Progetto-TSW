<head>
  <link rel="stylesheet" href="css/modal.css">
</head>

<div class="modal-update-password">
  <div class="modal-container">
    <a id="close-modal" onclick="chiudiModaleUpdatePassword()"><i class="fa-solid fa-xmark"></i></a>

    <h2>Modifica password</h2>
    <form action="modificaPassword" method="post">

      <div class="form-group">
        <label for="password">Password</label>
        <input id="password" type="password" name="password" required>
      </div>

      <div class="form-group">
        <label for="confirmPassword">Conferma password</label>
        <input id="confirmPassword" type="password" name="confirmPassword" required>
      </div>

      <p style="color: var(--color-error)">${error}</p>

      <input id="button" type="submit" value="Modifica password">
    </form>
  </div>
</div>

<script>
  function apriModaleUpdatePassword() {
    document.getElementsByClassName("modal-update-password")[0].style.display = "flex";
  }

  <%
    if (request.getAttribute("modalUpdatePassword-open") != null && (Boolean) request.getAttribute("modalUpdatePassword-open")) {
  %>
      apriModaleUpdatePassword();
  <%
    }
  %>

  function chiudiModaleUpdatePassword() {
    document.getElementsByClassName("modal-update-password")[0].style.display = "none";
  }
</script>