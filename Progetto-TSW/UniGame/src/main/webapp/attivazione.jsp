<%@ page import="model.Videogioco" %>
<%@ page import="model.RigaCarrello" %>
<%@ page import="model.DAO.RigaCarrelloDAO" %>
<%@ page import="model.DAO.VideogiocoDAO" %>
<%@ page import="model.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.acquisto.RigaOrdine" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  request.setAttribute("paginaCorrente", "attivazione.jsp");

  RigaOrdine rigaOrdineDAO = new RigaOrdine();
  VideogiocoDAO videogiocoDAO = new VideogiocoDAO();

  List<model.acquisto.RigaOrdine> righeOrdine = (List<model.acquisto.RigaOrdine>) request.getAttribute("righeOrdine");

%>

<html>
  <head>
    <title>Title</title>

    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/attivazione.css">
  </head>
  <body>
    <main>
      <div id="loader">
        <div class="spinner"></div>
      </div>

      <%@ include file="navbar.jsp" %>
      <div class="activation-page">
        <div class="activation-content">
          <h2>Grazie per il tuo acquisto! Ecco le tue chiavi di attivazione</h2>
          <%
            if (!righeOrdine.isEmpty()) {
              for (RigaOrdine riga : righeOrdine) {
                Videogioco videogioco = videogiocoDAO.doRetrieveById(riga.getIdVideogioco());
          %>
                <div class="purchased-games">
                  <h2><%= videogioco.getTitolo() %></h2>
                  <span class="activation-key"><%= riga.getChiave() %></span>
                </div>
        </div>
        <%
              }
            }
        %>
      </div>

      <script>
        setTimeout(() => {
          document.getElementById("loader").style.display = "none"
        }, 3000);
      </script>
    </main>
  </body>
</html>
