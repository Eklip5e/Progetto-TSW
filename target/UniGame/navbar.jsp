<%@ page import="com.unigame.model.Utente" %>

<head>
    <link rel="stylesheet" href="css/navbar.css">
</head>

<div class="navbar">
        <a href="home.jsp" class="logo">
            <img src="img/logo.png" alt="logo">
            <h1>Unigame</h1>
        </a>
    <ul class="nav_links">
        <li><a href="CategoriaServlet?categoria=pc"><i class="fa-solid fa-laptop"></i>PC</a></li>
        <li><a href="CategoriaServlet?categoria=playstation"><i class="fa-brands fa-playstation"></i>PlayStation</a></li>
        <li><a href="CategoriaServlet?categoria=xbox"><i class="fa-brands fa-xbox"></i>Xbox</a></li>
        <li><a href="CategoriaServlet?categoria=nintendo"><i class="fa-solid fa-star"></i>Nintendo</a></li>
        <li><a href="#"><i class="fa-solid fa-magnifying-glass"></i></a></li>
    </ul>
    <div class="header-icons">
        <a href="MostraCarrello"><i class="fa-solid fa-cart-shopping"></i></a>
        <%
            Utente utenteLoggato = (Utente) session.getAttribute("utente");
            if (utenteLoggato != null) {
        %>
        <a href="profilo"><i class="fa-solid fa-user"></i></a>
        <% } else { %>
        <a href="register.jsp"><i class="fa-solid fa-user"></i></a>
        <% } %>
    </div>
</div>
</header>