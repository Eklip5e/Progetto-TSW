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
    <%-- nav-bar --%>
    <%@ include file="navbar.jsp" %>

    <section class="register">
        <div class="register-container">
        <h1>Registrati</h1>
        <form action="register" method="post">
            <!-- Username -->
            <input type="text" id="username" name="username" required placeholder="Il tuo username:">

            <!-- Email -->
            <input type="email" id="email" name="email" required placeholder="La tua email:">

            <!-- Password -->
            <input placeholder="La tua password:" name="password" type="password" required>

            <!-- Nome -->
            <input type="text" name="nome" required placeholder="Nome:">

            <!-- Cognome -->
            <input type="text" name="cognome" required placeholder="Cognome:">

            <!-- Data di Nascita -->
            <input placeholder="Data di nascita (dd/mm/yyyy)" name="dataDiNascita" type="text" pattern="\d\d\/\d\d/\d\d\d\d" required="">

            <p id="warning" style="color:red;"></p>

            <!-- Submit -->
            <input type="submit" id="register-button" value="Registrati" onclick="checkUser()">
        </form>

        <!-- Redirect al Login -->
        <a href="login.jsp" class="login-redirect">Hai già un account?</a>
        </div>
    </section>

    <script src="js/register.js"></script>

    <%@ include file="footer.jsp" %>

    <script src="js/formatDateInput.js"></script>

    <script>
        function checkUser() {
            const username = document.getElementById("username").value;
            const email = document.getElementById("email").value;

            const xhttp = new XMLHttpRequest();
            xhttp.onload = function() {
                const res = JSON.parse(this.responseText);

                document.getElementById("warning").innerHTML = this.responseText;
            }

            xhttp.open("POST", "checkUser", true);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")

            const params = "username=" + encodeURIComponent(username) +
                           "&email=" + encodeURIComponent(email);

            xhttp.send(params);
        }
    </script>
</body>
</html>