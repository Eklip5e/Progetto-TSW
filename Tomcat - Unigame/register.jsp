<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    request.setAttribute("paginaCorrente", "register.jsp");
%>

<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>UniGame - Registrazione</title>

        <link rel="stylesheet" href="css/register.css">
    </head>
    <body>
        <main>
            <%-- nav-bar --%>
            <%@ include file="WEB-INF/navbar.jsp" %>

            <div class="register">
                <div class="register-container">
                    <h1>Registrati</h1>
                    <form action="register" method="post">
                        <!-- Username -->
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" id="username" name="username" required value="${username != null ? username : ''}" onblur="verificaUsername()">
                            <p id="usernameMsg"></p>
                        </div>

                        <!-- Email -->
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" id="email" name="email" required value="${email != null ? email : ''}" onblur="verificaEmail()">
                            <p id="emailMsg"></p>
                        </div>

                        <!-- Password -->
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input id="password" name="password" type="password" required value="">
                        </div>

                        <!-- Nome -->
                        <div class="form-group">
                            <label for="nome">Nome</label>
                            <input id="nome" type="text" name="nome" required value="${nome != null ? nome : ''}">
                        </div>

                        <!-- Cognome -->
                        <div class="form-group">
                            <label for="cognome">Cognome</label>
                            <input id="cognome" type="text" name="cognome" required value="${cognome != null ? cognome : ''}">
                        </div>

                        <!-- Data di Nascita -->
                        <div class="form-group">
                            <label for="dataNascita">Data di nascita</label>
                            <input id="dataNascita" name="dataDiNascita" type="text" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" required value="${dataDiNascita != null ? dataDiNascita : ''}">
                        </div>

                        <p style="color: var(--color-error)">${error}</p>

                        <!-- Submit -->
                        <input type="submit" id="register-button" value="Registrati">
                    </form>

                    <!-- Redirect al Login -->
                    <a href="login.jsp" id="login-redirect">Hai già un account?</a>
                </div>
            </div>
        </main>

        <%@ include file="WEB-INF/footer.jsp" %>

        <script src="js/inputDateBirth.js"></script>

        <script>
            function verificaUsername() {
                const username = document.getElementById("username").value;
                if (!username) return;

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
    </body>
</html>