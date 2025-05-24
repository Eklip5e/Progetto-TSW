<header class="navbar">
    <a href="${pageContext.request.contextPath}/home.jsp" class="logo">
        <img src="${pageContext.request.contextPath}/img/unigameLogo.svg" class="logo-img" alt="Logo UniGame">
        <img src="${pageContext.request.contextPath}/img/unigameLogohover.svg" class="logo-img-hover" alt="Logo UniGame Hover">
    <ul class="nav_links">
        <li><a href="${pageContext.request.contextPath}/games/pcgames.jsp"><i class="fa-solid fa-laptop"></i>PC</a></li>
        <li><a href="${pageContext.request.contextPath}/games/playstation.jsp"><i class="fa-brands fa-playstation"></i>PlayStation</a></li>
        <li><a href="${pageContext.request.contextPath}/games/xbox.jsp"><i class="fa-brands fa-xbox"></i>Xbox</a></li>
        <li><a href="${pageContext.request.contextPath}/games/nintendo.jsp"><i class="fa-solid fa-star"></i>Nintendo Switch</a></li>
        <li><a href="${pageContext.request.contextPath}/searchpage.jsp"><i class="fa-solid fa-magnifying-glass"></i></a></li>
    </ul>
    <div class="header-icons">
        <a href="${pageContext.request.contextPath}/carrello.jsp"><i class="fa-solid fa-cart-shopping"></i></a>
        <%
            String utenteLoggato = (String) session.getAttribute("username");
            if (utenteLoggato != null) {
        %>
        <a href="profilo"><i class="fa-solid fa-user"></i></a>
        <% } else { %>
        <a href="${pageContext.request.contextPath}/register.jsp"><i class="fa-solid fa-user"></i></a>
        <% } %>
    </div>
</header>