<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String paginaCorrente = (String) request.getAttribute("paginaCorrente");

    if (paginaCorrente == null)
        paginaCorrente = "";
%>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/navbar.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&family=Roboto&display=swap" rel="stylesheet">

<div class="${paginaCorrente eq 'home.jsp' or paginaCorrente eq 'game-page.jsp' ? 'navbar' : 'navbar-static'}">
    <div class="navbar-container">
        <a href="home.jsp" class="logo">
            <img src="img/logo.png" alt="logo">
        </a>
        <c:if test="${paginaCorrente ne 'register.jsp' and paginaCorrente ne 'login.jsp' and paginaCorrente ne 'carrello.jsp' and paginaCorrente ne 'pagamento.jsp' and paginaCorrente ne 'attivazione.jsp'}">
            <div class="nav_links">
                <ul>
                    <li id="platform"><a href="categoria?piattaforma=pc"><i class="fa-solid fa-laptop"></i>PC</a></li>
                    <li id="platform"><a href="categoria?piattaforma=playstation"><i class="fa-brands fa-playstation"></i>PlayStation</a></li>
                    <li id="platform"><a href="categoria?piattaforma=xbox"><i class="fa-brands fa-xbox"></i>Xbox</a></li>
                    <li id="platform"><a href="categoria?piattaforma=nintendo"><i class="fa-solid fa-star"></i>Nintendo</a></li>
                </ul>
            </div>
        </c:if>
        <c:if test="${paginaCorrente eq 'carrello.jsp'}">
                <div class="purchase-progress">
                    <span class="step active">
                        <span id="number">1</span>
                        <span id="text">Carrello</span>
                        <span id="spacer"></span>
                    </span>
                    <span class="step">
                        <span id="number">2</span>
                        <span id="text">Pagamento</span>
                        <span id="spacer"></span>
                    </span>
                    <span class="step">
                        <span id="number">3</span>
                        <span id="text">Attivazione</span>
                    </span>
                </div>
        </c:if>
        <c:if test="${paginaCorrente eq 'pagamento.jsp'}">
                <div class="purchase-progress">
                    <span class="step">
                        <span id="number">1</span>
                        <span id="text">Carrello</span>
                        <span id="spacer"></span>
                    </span>
                    <span class="step active">
                        <span id="number">2</span>
                        <span id="text">Pagamento</span>
                        <span id="spacer"></span>
                    </span>
                    <span class="step">
                        <span id="number">3</span>
                        <span id="text">Attivazione</span>
                    </span>
                </div>
        </c:if>
        <c:if test="${paginaCorrente eq 'attivazione.jsp'}">
                <div class="purchase-progress">
                    <span class="step">
                        <span id="number">1</span>
                        <span id="text">Carrello</span>
                        <span id="spacer"></span>
                    </span>
                    <span class="step">
                        <span id="number">2</span>
                        <span id="text">Pagamento</span>
                        <span id="spacer"></span>
                    </span>
                    <span class="step active">
                        <span id="number">3</span>
                        <span id="text">Attivazione</span>
                    </span>
                </div>
        </c:if>

        <div class="header-icons">
            <a href="carrello"><i class="fa-solid fa-cart-shopping"></i></a>
            <c:choose>
                <c:when test="${not empty sessionScope.utente}">
                    <a href="profilo"><i class="fa-solid fa-user"></i></a>
                </c:when>
                <c:otherwise>
                    <a href="register.jsp"><i class="fa-solid fa-user"></i></a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <c:if test="${paginaCorrente ne 'register.jsp' and paginaCorrente ne 'login.jsp' and paginaCorrente ne 'carrello.jsp' and paginaCorrente ne 'pagamento.jsp' and paginaCorrente ne 'attivazione.jsp'}">
        <div class="nav_links_mobile">
            <ul>
                <li id="platform"><a href="categoria?piattaforma=pc"><i class="fa-solid fa-laptop"></i>PC</a></li>
                <li id="platform"><a href="categoria?piattaforma=playstation"><i class="fa-brands fa-playstation"></i>PlayStation</a></li>
                <li id="platform"><a href="categoria?piattaforma=xbox"><i class="fa-brands fa-xbox"></i>Xbox</a></li>
                <li id="platform"><a href="categoria?piattaforma=nintendo"><i class="fa-solid fa-star"></i>Nintendo</a></li>
            </ul>
        </div>
    </c:if>
    <c:if test="${paginaCorrente eq 'carrello.jsp'}">
        <div class="purchase-progress-mobile">
                    <span class="step active">
                        <span id="number">1</span>
                        <span id="text">Carrello</span>
                        <span id="spacer"></span>
                    </span>
            <span class="step">
                        <span id="number">2</span>
                        <span id="text">Pagamento</span>
                        <span id="spacer"></span>
                    </span>
            <span class="step">
                        <span id="number">3</span>
                        <span id="text">Attivazione</span>
                    </span>
        </div>
    </c:if>
    <c:if test="${paginaCorrente eq 'pagamento.jsp'}">
        <div class="purchase-progress-mobile">
                    <span class="step active">
                        <span id="number">1</span>
                        <span id="text">Carrello</span>
                        <span id="spacer"></span>
                    </span>
            <span class="step active">
                        <span id="number">2</span>
                        <span id="text">Pagamento</span>
                        <span id="spacer"></span>
                    </span>
            <span class="step">
                        <span id="number">3</span>
                        <span id="text">Attivazione</span>
                    </span>
        </div>
    </c:if>
    <c:if test="${paginaCorrente eq 'attivazione.jsp'}">
        <div class="purchase-progress-mobile">
                    <span class="step active">
                        <span id="number">1</span>
                        <span id="text">Carrello</span>
                        <span id="spacer"></span>
                    </span>
            <span class="step active">
                        <span id="number">2</span>
                        <span id="text">Pagamento</span>
                        <span id="spacer"></span>
                    </span>
            <span class="step active">
                        <span id="number">3</span>
                        <span id="text">Attivazione</span>
                    </span>
        </div>
    </c:if>
</div>