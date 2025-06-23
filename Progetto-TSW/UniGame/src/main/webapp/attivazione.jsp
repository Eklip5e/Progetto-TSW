<%@ page import="model.Videogioco" %>
<%@ page import="model.RigaCarrello" %>
<%@ page import="model.DAO.RigaCarrelloDAO" %>
<%@ page import="model.DAO.VideogiocoDAO" %>
<%@ page import="model.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  request.setAttribute("paginaCorrente", "pagamento.jsp");

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
  </head>
  <body>
    <main>
      <%
        if (!carrello.isEmpty()) {
          for (Videogioco videogioco : carrello) {
      %>
            <div class="purchased-games">
              <h2><%= videogioco.getTitolo() %></h2>
            </div>
      <%
          }
        }
      %>
    </main>
  </body>
</html>
