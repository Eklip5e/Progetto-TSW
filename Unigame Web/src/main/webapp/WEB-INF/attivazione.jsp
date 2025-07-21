<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  request.setAttribute("paginaCorrente", "attivazione.jsp");
%>

<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>UniGame - attivazione</title>

    <link rel="stylesheet" href="css/attivazione.css">
  </head>
  <body>
    <main>
      <%@ include file="navbar.jsp" %>

      <h2 id="head">Grazie per il tuo acquisto! Ecco le tue chiavi di attivazione</h2>

      <%@ include file="game-grid.jsp" %>
    </main>

    <%@ include file="footer.jsp" %>
  </body>
</html>
