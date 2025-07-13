<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  request.setAttribute("paginaCorrente", "wish-list.jsp");
%>

<html>
  <head>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/wish-list.css">
  </head>
  <body>
    <main>
      <%@ include file="navbar.jsp" %>

      <div class="wish-list">
        <div class="wish-list-container">
          <h2>Wishlist</h2>
          <a href="profilo.jsp">
            <i class="fa-solid fa-arrow-left"></i>
            Torna indietro
          </a>
        </div>
      </div>

        <%@ include file="game-grid.jsp" %>
    </main>

    <%@ include file="footer.jsp" %>
  </body>
</html>
