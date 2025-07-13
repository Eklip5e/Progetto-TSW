<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  request.setAttribute("paginaCorrente", "ordine.jsp");
%>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>UniGame - Ordine #${ordine.idOrdine}</title>

  <link rel="stylesheet" href="css/ordine.css" />
</head>
<body>
<main>
  <%@ include file="navbar.jsp" %>

  <%@ include file="game-grid.jsp" %>
</main>

<%@ include file="footer.jsp" %>
</body>
</html>