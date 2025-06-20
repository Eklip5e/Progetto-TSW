<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.unigame.model.Utente" %>
<%@ page import="com.unigame.model.DAO.VideogiocoDAO" %>

<%@ include file="/WEB-INF/init.jsp" %>

<%
  request.setAttribute("paginaCorrente", "search-page.jsp");
%>

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
    <%-- nav-bar --%>
    <%@ include file="navbar.jsp" %>

      <div id="videogiochi" class="videogiochi">
        <%@ include file="game-grid.jsp" %>
      </div>
  </main>

  <%@ include file="footer.jsp" %>

  <script>
    const form = document.querySelector(".search-form");

    // Funzione per fare la ricerca fetch e aggiornare i giochi
    function cercaVideogiochi() {
      const formData = new FormData(form);
      const params = new URLSearchParams(formData);

      fetch("cercaVideogiochi?" + params.toString(), {
        headers: { "X-Requested-With": "XMLHttpRequest" }
      })
              .then(response => response.text())
              .then(html => {
                document.getElementById("videogiochi").innerHTML = html;
              })
              .catch(err => console.error("Errore AJAX:", err));
    }

    // Aggiungo listener a input e select per triggerare la ricerca live
    form.querySelectorAll("input, select").forEach(element => {
      element.addEventListener("input", cercaVideogiochi);
      element.addEventListener("change", cercaVideogiochi);
    });

    // Eventuale inizializzazione con risultati iniziali
    cercaVideogiochi();
  </script>
  </body>