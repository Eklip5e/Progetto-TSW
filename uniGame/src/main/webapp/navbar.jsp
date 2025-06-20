<%@ page import="com.unigame.model.Utente" %>

<%
    Utente userSession = (Utente) session.getAttribute("utente");

    String paginaCorrente = (String) request.getAttribute("paginaCorrente");

    if (paginaCorrente == null)
        paginaCorrente = "";
%>

<head>
    <link rel="stylesheet" href="css/navbar.css">
</head>

<div class="navbar">
    <div class="navbar-content">
        <a href="home.jsp" class="logo">
            <img src="img/logo.png" alt="logo">
        </a>
        <%
            if (!paginaCorrente.equals("register.jsp") && !paginaCorrente.equals("login.jsp")) {
                if(!paginaCorrente.equals("search-page.jsp")) {
        %>
                    <ul class="nav_links">
                        <li id="platform"><a href="CategoriaServlet?categoria=pc"><i class="fa-solid fa-laptop"></i>PC</a></li>
                        <li id="platform"><a href="CategoriaServlet?categoria=playstation"><i class="fa-brands fa-playstation"></i>PlayStation</a></li>
                        <li id="platform"><a href="CategoriaServlet?categoria=xbox"><i class="fa-brands fa-xbox"></i>Xbox</a></li>
                        <li id="platform"><a href="CategoriaServlet?categoria=nintendo"><i class="fa-solid fa-star"></i>Nintendo</a></li>

                        <li><a href="search-page.jsp"><i class="fa-solid fa-magnifying-glass"></i></a></li>
                    </ul>
        <%
                } else {
        %>
                    <div class="search-bar">
                        <form action="cercaVideogiochi" class="search-form">

                            <input id="search-input" type="text" name="titolo" placeholder="Cerca">

                            <div class="advanced-search">
                                <div class="advanced-search-content">
                                    <select name="piattaforma">
                                        <option>PC</option>
                                        <option>PlayStation</option>
                                        <option>Xbox</option>
                                        <option>Nintendo</option>
                                    </select>

                                    <select name="genere">
                                        <option value="">Genere</option>
                                        <option value="azione">Azione</option>
                                        <option value="avventura">Avventura</option>
                                    </select>

                                    <div class="price">
                                        <span>Da</span>
                                        <input type="text" name="prezzoMin">
                                        <span>a</span>
                                        <input type="text" name="prezzoMax">
                                        <span>€</span>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
        <%
                }
        %>

        <div class="header-icons">
            <a href="MostraCarrello"><i class="fa-solid fa-cart-shopping"></i></a>
            <%
                Utente utenteLoggato = (Utente) session.getAttribute("utente");
                if (utenteLoggato != null) {
            %>
            <a href="profilo.jsp"><i class="fa-solid fa-user"></i></a>
            <% } else { %>
            <a href="register.jsp"><i class="fa-solid fa-user"></i></a>
            <% } %>
        </div>
        <%
            }
        %>
    </div>
</div>