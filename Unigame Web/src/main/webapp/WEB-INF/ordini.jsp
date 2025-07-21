<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
  request.setAttribute("paginaCorrente", "ordini.jsp");
%>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>UniGame - Ordini</title>

  <link rel="stylesheet" href="css/order-grid.css">
</head>
<body>
<main>
  <%@ include file="navbar.jsp" %>

  <div class="order-grid">
    <div class="order-card back">
      <a id="back-container" class="game-card" href="profilo">
        <i class="fa-solid fa-arrow-left"></i>
        <span>Torna indietro</span>
      </a>
    </div>

    <c:forEach var="ordine" items="${ordini}">
      <div class="order-card">
        <a class="order-card-container" href="ordine?idOrdine=${ordine.idOrdine}">
          <h2>Ordine #${ordine.idOrdine}</h2>
          <div class="order-info">
            <span><strong>Data ordine: </strong><fmt:formatDate value="${ordine.dataOrdine}" pattern="dd/MM/yyyy"/></span>
            <span><strong>Totale: </strong><fmt:formatNumber value="${ordine.totale}" type="number" minFractionDigits="2" maxFractionDigits="2"/> €</span>

            <c:set var="fatturazione" value="${fatturazioni[ordine.idOrdine]}"/>
            <c:set var="startIndex" value="${fn:length(fatturazione.numero) - 4}" />
            <c:set var="endIndex" value="${fn:length(fatturazione.numero)}" />
            <span><strong>Pagamento: </strong> **** **** **** ${fn:substring(fatturazione.numero, startIndex, endIndex)}</span>
          </div>
        </a>
      </div>
    </c:forEach>
  </div>
</main>
<%@ include file="footer.jsp" %>
</body>
</html>
