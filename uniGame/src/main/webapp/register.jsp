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
    <a href="home.jsp" class="logo">
        <img src="img/logo.png" alt="logo">
        <h1>Unigame</h1>
    </a>

    <section class="register">
        <h1>Registrati</h1>
        <form action="register" method="post">
            <!-- Email -->
            <input type="email" id="email" name="email" required placeholder="La tua email:">

            <!-- Password -->
            <div class="password-group">
                <input placeholder="La tua password:" id="password" name="password" type="password" required onfocus="togglePasswordHint(true)" onblur="togglePasswordHint(false)">

                <div id="passwordHint" class="password-hint">
                    <strong>Requisiti Password:</strong>
                    <ul>
                        <li id="length-requirement">❌ 8-25 caratteri</li>
                        <li id="uppercase-requirement">❌ Almeno una maiuscola</li>
                        <li id="symbol-requirement">❌ Almeno un simbolo (: @ ? ! + = - _)</li>
                    </ul>
                </div>   
            </div> 

            <!-- Nome -->
            <input type="text" id="nome" name="nome" required placeholder="Nome:">

            <!-- Cognome -->
            <input type="text" id="cognome" name="cognome" required placeholder="Cognome:">

            <!-- Data di Nascita -->
            <input placeholder="Data di nascita (dd/mm/yyyy)" id="dataDiNascita" name="dataDiNascita" type="text" pattern="\d\d\/\d\d/\d\d\d\d" required="">

            <% if (request.getAttribute("error") != null) { %>
                <p style="color:red;"><%= request.getAttribute("error") %></p>
            <% } %>

            <!-- Submit -->
            <input type="submit" id="register-button" value="Registrati">
        </form>

        <!-- Redirect al Login -->
        <a href="login.jsp" class="login-redirect">Hai già un account?</a>
    </section>

    <script src="js/register.js"></script>

    <%@ include file="footer.jsp" %>
</body>
</html>