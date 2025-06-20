<%@ page import="com.unigame.model.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>UniGame</title>

    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/search-page.css">

    <%-- font-awesome --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
  </head>
  <body>
    <main>
      <div class="search-bar">
        <div class="search-bar-content">
          <a href="home.jsp" class="logo">
            <img src="img/logo.png" alt="logo">
          </a>

          <input type="text" placeholder="Cerca">

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
        </div>
      </div>

        <div class="advanced-search">
          <div class="advanced-search-content">
          <select name="piattaforma">
            <option>PC</option>
            <option>PlayStation</option>
            <option>Xbox</option>
            <option>Nintendo</option>
          </select>

          <select name="genere">
            <option>Genere</option>
          </select>

          <div class="price">
            <span>Da</span>
            <input type="text">
            <span>a</span>
            <input type="text">
            <span>€</span>
          </div>
        </div>
      </div>

    </main>

    <%@ include file="footer.jsp" %>
  </body>
</html>
