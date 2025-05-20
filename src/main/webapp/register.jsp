<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="navbar.jsp"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrazione</title>
    <link rel="stylesheet" href="css/register.css">
    <link rel="stylesheet" href="css/styles.css">
    <script src="js/scripts.js"></script>
    <script src="js/CheckPassword.js"></script> <!-- Includi il file JavaScript esterno -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css ">
    <link rel="preconnect" href="https://fonts.googleapis.com ">
    <link rel="preconnect" href="https://fonts.gstatic.com " crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat :ital,wght@0,100..900;1,100..900&family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>

<section class="register">
    <form action="register" method="post" onsubmit="return validatePassword()">

        <div class="form-group">
            <label for="e-mail">E-mail</label>
            <input type="email" id="e-mail" name="e-mail" required>
        </div>

        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required>
        </div>

        <div class="form-group">
            <label for="name">Nome</label>
            <input type="text" id="name" name="name" required>
        </div>

        <div class="form-group">
            <label for="surname">Cognome</label>
            <input type="text" id="surname" name="surname" required>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <div class="password-container">
                <!-- Campo Password -->
                <input type="password" id="password" name="password" required oninput="checkPasswordStrength()">

        <div class="form-group">
                <!-- Blocco dei Requisiti - Posizionato a fianco -->
                <div class="password-hint right-aligned">
                    <strong>Requisiti Password:</strong>
                    <ul id="password-requirements">
                        <li id="length-requirement">❌ Almeno 8 caratteri, massimo 25.</li>
                        <li id="uppercase-requirement">❌ Almeno una lettera maiuscola.</li>
                        <li id="symbol-requirement">❌ Almeno un simbolo (: @ ? ! + = - _).</li>
                    </ul>
                </div>
            </div>
        </div>

        <input type="submit" id="register" value="Register">

        <div class="form-group" id="login-redirect">
            <a href="login.jsp" class="login-redirect">Hai già un account?</a>
        </div>

        <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
        <% } %>

    </form>
</section>
</body>
</html>