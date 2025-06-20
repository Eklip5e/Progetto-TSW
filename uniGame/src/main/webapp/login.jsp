<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    request.setAttribute("paginaCorrente", "login.jsp");
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UniGame - Login</title>

    <!-- CSS -->
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/login.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>

    <%@ include file="navbar.jsp" %>

    <section class="login">
        <h1>Accedi</h1>
        <form action="login" method="post">

            <!-- Email -->
            <input placeholder="La tua email:" type="text" name="email" id="email" required>

            <!-- Password -->
            <input placeholder="La tua password:" type="password" name="password" id="password" required>

            <% if (request.getAttribute("error") != null) { %>
                <p style="color:red;"><%= request.getAttribute("error") %></p>
            <% } %>

            <input type="submit" id="login-button" value="Login">
        </form>

        <a href="register.jsp" class="signup-redirect">Non hai un account?</a>
    </section>

    <%@ include file="footer.jsp" %>
</body>
</html>