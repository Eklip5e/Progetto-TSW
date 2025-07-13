<%@ page import="model.acquisto.RigaOrdine" %>
<%@ page import="java.util.List" %>
<%@ page import="model.acquisto.Ordine" %>
<%@ page import="model.Videogioco" %>
<%@ page import="model.DAO.VideogiocoDAO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  request.setAttribute("paginaCorrente", "ordini.jsp");

  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

  List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
%>

<html>
<head>
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="css/ordini.css">
</head>
<body>
<main>
  <%@ include file="navbar.jsp" %>

  <div class="ordini">
    <a id="back" href="profilo.jsp">Torna indietro</a>

    <div class="ordini-container">
      <%
        if (ordini != null) {
          for (Ordine ordine : ordini) {
      %>
      <a id="details" href="mostraOrdine?idOrdine=<%= ordine.getIdOrdine() %>" class="ordine">
        <h3>Ordine #<%= ordine.getIdOrdine() %></h3>
        <p>Data: <%= sdf.format(ordine.getDataOrdine()) %></p>
        <p>Stato: <%= ordine.getStato() %></p>
      </a>
      <%
          }
        }
      %>
    </div>
  </div>
</main>
</body>
</html>
