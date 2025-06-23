<%@ page import="model.Videogioco" %>
<%@ page import="model.RigaCarrello" %>
<%@ page import="model.DAO.RigaCarrelloDAO" %>
<%@ page import="model.DAO.VideogiocoDAO" %>
<%@ page import="model.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  request.setAttribute("paginaCorrente", "attivazione.jsp");

  VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
  List<Videogioco> carrello = new ArrayList<>();
  double prezzoTotale = 0;

  Utente utente = (Utente) session.getAttribute("utente");

  List<RigaCarrello> userCart = null;

  if(utente != null) {
    userCart = (List<RigaCarrello>) request.getAttribute("userCart");
    if (userCart != null) {
      for (RigaCarrello riga : userCart) {
        Videogioco videogioco = videogiocoDAO.doRetrieveById(riga.getIdVideogioco());
        if (videogioco != null) {
          carrello.add(videogioco);
          for (int i = 0; i < riga.getQuantità(); i++) {
            prezzoTotale += videogioco.getPrezzo();
          }
        }
      }
    }
  } else {
    List<Integer> guestCart = (List<Integer>) session.getAttribute("guestCart");
    if (guestCart != null) {
      for (Integer idVideogioco : guestCart) {
        Videogioco videogioco = videogiocoDAO.doRetrieveById(idVideogioco);
        if (videogioco != null) {
          carrello.add(videogioco);
          prezzoTotale += videogioco.getPrezzo();
        }
      }
    }
  }
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
      <%
        if (!carrello.isEmpty()) {
          for (Videogioco videogioco : carrello) {
      %>
        <div class="activation-content">
              <div class="purchased-games">
                <h2><%= videogioco.getTitolo() %></h2>
                <span class="activation-key"></span>
              </div>
        </div>
      <%
          }
        }
      %>
      </div>
      <script>
        const keyElements = document.querySelectorAll(".activation-key");

        keyElements.forEach(el => {
          const key = Array(4).fill(0).map(() =>
                  Math.random().toString(36).substring(2, 6).toUpperCase()
          ).join('-');

          el.innerText = key;
        });
      </script>

      <script>
        setTimeout(() => {
          document.getElementById("loader").style.display = "none"
        }, 3000);
      </script>
    </main>
  </body>
</html>
