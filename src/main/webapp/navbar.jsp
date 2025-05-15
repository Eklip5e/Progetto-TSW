<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nav bar</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
    <header class="navbar">
        <a href="#" class="logo"><i class="fa-solid fa-gamepad"></i></a>
        <ul class="nav_links">
            <li><a href="#"><i class="fa-solid fa-laptop"></i>PC</a></li>
            <li><a href="#"><i class="fa-brands fa-playstation"></i>PlayStation</a></li>
            <li><a href="#"><i class="fa-brands fa-xbox"></i>Xbox</a></li>
            <li><a href="#"><i class="fa-solid fa-star"></i>Nintendo Switch</a></li>
            <li><a href="#"><i class="fa-solid fa-magnifying-glass"></i></a></li>
        </ul>

        <div>
            <a href="#"><i class="fa-solid fa-cart-shopping"></i></a>

            <%-- Controllo utente loggato --%>
            <%
                Object utente = session.getAttribute("utenteLoggato");
                if (utente != null) {
            %>
            <a href="profilo.jsp"><i class="fa-solid fa-user"></i></a>
            <% } else { %>
            <a href="register.jsp"><i class="fa-solid fa-user"></i></a>
            <% } %>
        </div>
    </header>
</body>
</html>