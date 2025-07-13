<%@ page import="model.acquisto.RigaOrdine" %>
<%@ page import="java.util.List" %>
<%@ page import="model.acquisto.Ordine" %>
<%@ page import="model.Videogioco" %>
<%@ page import="model.DAO.VideogiocoDAO" %>
<%@ page import="model.acquisto.DAO.OrdineDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  request.setAttribute("paginaCorrente", "ordini.jsp");

  VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
  OrdineDAO ordineDAO = new OrdineDAO();

  int idOrdine = (int) request.getAttribute("idOrdine");
  List<RigaOrdine> righeOrdine = (List<RigaOrdine>) request.getAttribute("righeOrdine");
%>

<html>
<head>
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="css/ordine.css">
</head>
<body>
<main>
  <%@ include file="navbar.jsp" %>

  <div class="ordine">
    <a id="back" href="mostraOrdini">Torna indietro</a>
    <h2>Ordine <%= idOrdine %></h2>
    <div class="ordine-container">
    <%
      if (righeOrdine != null) {
        for (RigaOrdine riga : righeOrdine) {
          Videogioco videogioco = videogiocoDAO.doRetrieveById(riga.getIdVideogioco());
    %>
          <div class="game">
            <div class="game-container">
              <img src="https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/<%= videogioco.getAppIdSteam() %>/header.jpg">
              <div class="info">
                <span><%= videogioco.getTitolo() %></span>
                <span id="price"><%= videogioco.getPrezzo() %> €</span>
              </div>
                <span><%= riga.getChiave() %></span>
            </div>
          </div>
    <%
        }
      }
    %>
    </div>
  </div>
</main>
</body>
</html>
