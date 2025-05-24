<header class="navbar">
    <a href="home.jsp" class="logo">
        <svg class="logo-svg" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100">
            <circle cx="50" cy="50" r="40" fill="currentColor" />
        </svg>
    <ul class="nav_links">
        <li><a href="CategoriaServlet?categoria=pc"><i class="fa-solid fa-laptop"></i>PC</a></li>
        <li><a href="CategoriaServlet?categoria=playstation"><i class="fa-brands fa-playstation"></i>PlayStation</a></li>
        <li><a href="CategoriaServlet?categoria=xbox"><i class="fa-brands fa-xbox"></i>Xbox</a></li>
        <li><a href="CategoriaServlet?categoria=nintendo"><i class="fa-solid fa-star"></i>Nintendo Switch</a></li>
        <li><a href="CategoriaServlet"><i class="fa-solid fa-magnifying-glass"></i></a></li>
    </ul>
    <div class="header-icons">
        <a href="#"><i class="fa-solid fa-cart-shopping"></i></a>
        <%
            String utenteLoggato = (String) session.getAttribute("username");
            if (utenteLoggato != null) {
        %>
        <a href="profilo"><i class="fa-solid fa-user"></i></a>
        <% } else { %>
        <a href="register.jsp"><i class="fa-solid fa-user"></i></a>
        <% } %>
    </div>
</header>