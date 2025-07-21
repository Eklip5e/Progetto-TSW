<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    request.setAttribute("paginaCorrente", "game-page.jsp");
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>UniGame - ${videogioco.titolo}</title>

    <link rel="stylesheet" href="css/game-page.css">
</head>
<body>
    <main>

        <%@ include file="navbar.jsp" %>

        <!-- Game Banner Section -->
        <div class="game-banner" style="background: url('http://cdn.cloudflare.steamstatic.com/steam/apps/${videogioco.appIdSteam}/library_hero.jpg') center center / cover no-repeat;"></div>

        <div class="game-page-content">
            <div id="game-cover" style="position: relative;">
                <img src="https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/${videogioco.appIdSteam}/header.jpg" alt="${videogioco.titolo}">

                <c:if test="${sessionScope.utente != null  and sessionScope.utente.admin}">
                    <div class="admin-actions">
                        <button class="update-game" onclick="apriModaleUpdateGame()">
                            <i class="fa-solid fa-pen-nib"></i>
                        </button>
                        <a class="delete-game" href="disattivaVideogioco?idVideogioco=${videogioco.idVideogioco}">
                            <i class="fa-solid fa-xmark"></i>
                        </a>
                    </div>
                </c:if>
            </div>
            <div class="game-info">
                <h2>(${videogioco.piattaforma}) - ${videogioco.titolo}</h2>
                    <div class="amount">
                        <div class="discount">
                            <span>-${videogioco.sconto}%</span>
                        </div>
                        <div class="total">
                            <span><fmt:formatNumber value="${videogioco.prezzoScontato}" type="number" minFractionDigits="2" maxFractionDigits="2"/> €</span>
                        </div>
                    </div>
                <div class="actions">
                    <c:choose>
                        <c:when test="${sessionScope.utente != null}">
                            <c:choose>
                                <c:when test="${preferito}">
                                    <a id="add-to-favourites" href="rimuoviPreferiti?paginaCorrente=gamePage&idVideogioco=${videogioco.idVideogioco}" class="heart-icon">
                                        <i class="fa-solid fa-heart"></i>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a id="remove-from-favorites" href="aggiungiPreferiti?paginaCorrente=gamePage&idVideogioco=${videogioco.idVideogioco}" class="heart-icon">
                                        <i class="fa-regular fa-heart"></i>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <a id="add-to-favourites" title="Accedi per aggiungere ai preferiti">
                                <i class="fa-regular fa-heart"></i>
                            </a>
                        </c:otherwise>
                    </c:choose>
                    <a href="aggiungiCarrello?idVideogioco=${videogioco.idVideogioco}" class="add-to-cart">Aggiungi al carrello</a>
                </div>
                </div>
            </div>

        <div class="details">
            <div class="product-text">
                <h2>Riguardo al prodotto</h2>
                <p>${videogioco.descrizione}</p>
            </div>
            <div class="specifics">
                <h2>Specifiche</h2>
                <div class="list">
                    <div class="list-item">
                        <span id="label">Installazione:</span>
                        <a id="value">Come attivare il prodotto</a>
                    </div>
                    <div class="list-item">
                        <span id="label">Piattaforma:</span>
                        <span id="value">${videogioco.piattaforma}</span>
                    </div>
                    <div class="list-item">
                        <span id="label">Data di rilascio:</span>
                        <span id="value"><fmt:formatDate value="${videogioco.dataRilascio}" pattern="dd/MM/yyyy"/></span>
                    </div>
                    <div class="list-item">
                        <span id="label">Produttore:</span>
                        <span id="value">${videogioco.produttore}</span>
                    </div>
                    <div class="list-item">
                        <span id="label">Genere:</span>
                        <span id="value">${videogioco.genere}</span>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="modal-update-game.jsp" %>
    </main>

        <%@ include file="footer.jsp" %>
    </body>
</html>