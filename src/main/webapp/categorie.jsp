<%@ page import="model.DAO.VideogiocoDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  request.setAttribute("paginaCorrente", "categorie.jsp");

  VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
  request.setAttribute("videogiochi", videogiocoDAO.doRetrieveByPiattaformaAttivi(request.getParameter("piattaforma")));
%>

<html>
  <head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body>
    <main>
      <%@ include file="navbar.jsp" %>

      <%@ include file="game-grid.jsp" %>
    </main>

    <%@ include file="footer.jsp" %>
  </body>
</html>
