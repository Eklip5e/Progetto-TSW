<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="navbar.jsp"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navbar</title>
    <link rel="stylesheet" href="css/styles.css">
    <script src="js/scripts.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>

<section class="register">
    <form action="register" method="post">

    <div class="form-group">
            <label for="e-mail">E-mail</label>
            <input type="email" id="e-mail" name="e-mail">
        </div>

        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" name="username">
        </div>

        <div class="form-group">
            <label for="name">Nome</label>
            <input type="text" id="name" name="name">
        </div>

        <div class="form-group">
            <label for="surname">Cognome</label>
            <input type="text" id="surname" name="surname">
        </div>

        <!--
        <div class="form-group">
            <label for="birthday">Data di Nascita</label>
            <input type="text" id="birthday" name="birthday" placeholder="gg/mm/aaaa">
        </div>
        -->
        <script>
            flatpickr("#birthday", {
                dateFormat: "d/m/Y"
            });
        </script>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password">
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
