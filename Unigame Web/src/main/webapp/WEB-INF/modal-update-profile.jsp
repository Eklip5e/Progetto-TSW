<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="css/modal.css">

<div class="modal-update-profile">
    <div class="modal-container">
        <a id="close-modal" onclick="chiudiModaleUpdateProfile()"><i class="fa-solid fa-xmark"></i></a>

        <h2>Modifica profilo</h2>
        <form action="modificaProfilo" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required value="${utente.username}" onblur="verificaUsername()">
                <p id="usernameMsg"></p>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required value="${utente.email}" onblur="verificaEmail()">
                <p id="emailMsg"></p>
            </div>

            <div class="form-group">
                <label for="nome">Nome</label>
                <input id="nome" type="text" name="nome" value="${utente.nome}" required>
            </div>

            <div class="form-group">
                <label for="cognome">Cognome</label>
                <input id="cognome" type="text" name="cognome" value="${utente.cognome}" required>
            </div>

            <div class="form-group">
                <label for="dataNascita">Data di nascita</label>
                <input id="dataNascita" type="text" name="dataDiNascita" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" placeholder="DD/MM/YYYY" value="<fmt:formatDate value="${utente.dataDiNascita}" pattern="dd/MM/yyyy"/>" required>
            </div>

            <p style="color: var(--color-error)">${error}</p>

            <input id="button" type="submit" value="Modifica profilo">
        </form>
    </div>
</div>

<script src="js/inputDateBirth.js"></script>

<script>
    function apriModaleUpdateProfile() {
        document.getElementsByClassName("modal-update-profile")[0].style.display = "flex";
    }

    <%
        if (request.getAttribute("modalOpen") != null && (Boolean) request.getAttribute("modalOpen")) {
    %>
            apriModaleUpdateProfile();
    <%
        }
    %>

    function chiudiModaleUpdateProfile() {
        document.getElementsByClassName("modal-update-profile")[0].style.display = "none";
    }

    const currentUsername = "${utente.username}";
    const currentEmail = "${utente.email}";

    function verificaUsername() {
        const username = document.getElementById("username").value;
        if (!username) return;

        if (username === currentUsername) {
            const msg = document.getElementById("usernameMsg");
            msg.style.display = "none"; // nascondi messaggio
            return;
        }

        const xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                const response = JSON.parse(this.responseText);
                const msg = document.getElementById("usernameMsg");
                if (response.exist) {
                    msg.textContent = "Username già esistente";
                    msg.style.display = "flex";
                    msg.style.color = "#e05c6d";
                } else {
                    msg.textContent = "Username disponibile";
                    msg.style.display = "flex";
                    msg.style.color = "#4CAF50";
                }
            }
        };
        xhttp.open("POST", "verificaUsernameEmail", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("username=" + encodeURIComponent(username));
    }

    function verificaEmail() {
        const email = document.getElementById("email").value;
        if (!email) return;

        if (email === currentEmail) {
            const msg = document.getElementById("emailMsg");
            msg.style.display = "none";
            return;
        }

        const xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                const response = JSON.parse(this.responseText);
                const msg = document.getElementById("emailMsg");
                if (response.exist) {
                    msg.textContent = "Email già esistente";
                    msg.style.display = "flex";
                    msg.style.color = "#e05c6d";
                } else {
                    msg.textContent = "Email disponibile";
                    msg.style.display = "flex";
                    msg.style.color = "#4CAF50";
                }
            }
        };
        xhttp.open("POST", "verificaUsernameEmail", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("email=" + encodeURIComponent(email));
    }
</script>