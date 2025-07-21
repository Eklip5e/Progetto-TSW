<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    request.setAttribute("paginaCorrente", "carrello.jsp");
%>

<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>UniGame - Carrello</title>

        <link rel="stylesheet" href="css/carrello.css">
    </head>
    <body>
        <main>
            <%@ include file="navbar.jsp" %>

            <div class="cart-page">
                <div class="cart-page-content">
                    <div class="cart">
                        <h2>Carrello</h2>
                        <div class="cart-content">
                            <c:choose>
                                <c:when test="${not empty carrello}">
                                    <c:forEach var="videogioco" items="${videogiochi}">
                                        <div class="cart-item">
                                            <img id="cover" src="https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/${videogioco.appIdSteam}/header.jpg" alt="${videogioco.titolo}" width="200">
                                            <div class="item-container">
                                                <div class="info-container">
                                                    <h3>${videogioco.titolo}</h3>
                                                    <div class="actions">
                                                        <a href="rimuoviCarrello?idVideogioco=${videogioco.idVideogioco}" method="post">
                                                            <i class="fa-solid fa-trash"></i>
                                                        </a>
                                                        <a href="aggiungiPreferiti?paginaCorrente=carrello&idVideogioco=${videogioco.idVideogioco}"><i class="fa-solid fa-star"></i></a>
                                                    </div>
                                                </div>
                                                <div class="price-container">
                                                    <div class="price">
                                                        <span id="price"><fmt:formatNumber value="${videogioco.prezzoScontato}" type="number" minFractionDigits="2" maxFractionDigits="2"/> €</span>
                                                    </div>
                                                    <select class="quantity-select" data-id="${videogioco.idVideogioco}">

                                                        <c:set var="currentRiga" value="${carrelloMap[videogioco.idVideogioco]}"/>
                                                        <c:set var="currentQuantity" value="${currentRiga.quantità}"/>
                                                        <c:forEach var="i" begin="1" end="10">
                                                            <option value="${i}" ${i == currentQuantity ? 'selected' : ''}>${i}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div class="empty-cart">
                                        <i class="fa-solid fa-cart-shopping"></i>
                                        <span>Il tuo carrello è vuoto</span>
                                        <p>Non hai ancora aggiunto nessun articolo nel carrello. Sfoglia il sito per trovare offerte fantastiche! <p>
                                        <button onclick="window.location.href='home.jsp'">Scopri i giochi</button>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="cart-summary">
                        <h2>Riepilogo</h2>
                        <div class="summary-content">
                            <div class="price-rows">
                                <div class="price-row">
                                    <span><strong>Prezzo ufficiale</strong></span>
                                    <span><fmt:formatNumber value="${prezzoUfficiale}" type="number" minFractionDigits="2" maxFractionDigits="2"/> €</span>
                                </div>
                                <div class="price-row">
                                    <span><strong>Sconto</strong></span>
                                    <span><fmt:formatNumber value="${scontoTotale}" type="number" minFractionDigits="2" maxFractionDigits="2"/> €</span>
                                </div>
                                <div class="price-row total">
                                    <span><strong>Totale</strong></span>
                                    <span><fmt:formatNumber value="${prezzoTotale}" type="number" minFractionDigits="2" maxFractionDigits="2"/> €</span>
                                </div>
                            </div>

                            <div class="actions">
                                <c:set var="statoBottone" value="disabled"/>
                                <c:if test="${not empty carrello}">
                                    <c:set var="statoBottone" value=""/>
                                </c:if>

                                <c:set var="redirect" value="pagamento"/>
                                <c:if test="${utente == null}">
                                    <c:set var="redirect" value="login.jsp"/>
                                </c:if>
                                <button onclick="window.location.href='${redirect}'" id="pagamento-button" ${statoBottone}>Vai al pagamento</button>
                                <span id="choice">o</span>
                                <a id="back" href="home.jsp">Continua lo shopping</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <%@ include file="footer.jsp" %>

        <script>
            const selects = document.getElementsByClassName("quantity-select");

            for (let i = 0; i < selects.length; i++) {
                selects[i].addEventListener('change', function () {
                    const idVideogioco = this.getAttribute('data-id');
                    const quantita = this.value;

                    const xhttp = new XMLHttpRequest();

                    xhttp.onreadystatechange = function () {
                        if (this.readyState === 4) {
                            if (this.status === 200) {

                                const response = JSON.parse(this.responseText);
                                if (response) {
                                    window.location.reload();
                                } else {
                                    alert("Errore nell'aggiornamento della quantità");
                                }
                            } else {
                                alert("Errore nella richiesta AJAX");
                            }
                        }
                    };

                    xhttp.open("POST", "modificaQuantita", true);
                    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xhttp.send("idVideogioco=" + idVideogioco + "&quantita=" + quantita);
                });
            }
        </script>
</body>
</html>