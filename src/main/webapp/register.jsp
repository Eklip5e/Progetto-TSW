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

        <!-- CSS -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/register.css">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

        <!-- Google Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
    </head>
    <body>
        <main>
            <%-- nav-bar --%>
            <%@ include file="navbar.jsp" %>

            <div class="register">
                <div class="register-container">
                    <h1>Registrati</h1>
                    <form action="register" method="post">
                        <!-- Username -->
                        <div class="form-group">
                            <input type="text" id="username" name="username" required placeholder="Il tuo username:"
                                value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>" onblur="verificaUsername()">
                            <p id="usernameMsg"></p>
                        </div>

                        <!-- Email -->
                        <div class="form-group">
                            <input type="email" id="email" name="email" required placeholder="La tua email:"
                                value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" onblur="verificaEmail()">
                            <p id="emailMsg"></p>
                        </div>

                        <!-- Password -->
                        <input placeholder="La tua password:" name="password" type="password" required value="">

                        <!-- Nome -->
                        <input type="text" name="nome" required placeholder="Nome:"
                            value="<%= request.getAttribute("nome") != null ? request.getAttribute("nome") : "" %>">

                        <!-- Cognome -->
                        <input type="text" name="cognome" required placeholder="Cognome:"
                            value="<%= request.getAttribute("cognome") != null ? request.getAttribute("cognome") : "" %>">

                        <!-- Data di Nascita -->
                        <input placeholder="Data di nascita (dd/mm/yyyy)" name="dataDiNascita" type="text" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" required
                            value="<%= request.getAttribute("dataDiNascita") != null ? request.getAttribute("dataDiNascita") : "" %>">

                        <%
                            if (request.getAttribute("error") != null) {
                        %>
                                <p style="color: var(--color-error)"><%= request.getAttribute("error") %></p>
                        <%
                            }
                        %>

                        <!-- Submit -->
                        <input type="submit" id="register-button" value="Registrati">
                    </form>

                    <!-- Redirect al Login -->
                    <a href="login.jsp" id="login-redirect">Hai già un account?</a>
                </div>
            </div>
        </main>

        <%@ include file="footer.jsp" %>

        <script src="js/formatDateInput.js"></script>

        <script>
            function verificaUsername() {
                const username = document.getElementById("username").value;
                if (!username) return;

                const xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                    if (this.readyState === 4 && this.status === 200) {
                        const response = JSON.parse(this.responseText);
                        const msg = document.getElementById("usernameMsg");
                        if (response.exists) {
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
                        if (response.exists) {
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