<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="css/game-grid.css">

<div class="game-grid">
    <c:if test="${sessionScope.utente != null  and sessionScope.utente.admin and paginaCorrente eq 'home.jsp'}">
        <div class="game-card card-add-game">
            <button onclick="apriModaleAddGame()">
                <i id="plus" class="fa-solid fa-plus"></i>
            </button>
        </div>
    </c:if>

    <c:if test="${paginaCorrente eq 'ordini.jsp' or paginaCorrente eq 'ordine.jsp' or paginaCorrente eq 'preferiti.jsp' or paginaCorrente eq 'disabled.jsp'}">
        <c:set var="href" value="profilo"/>
        <c:if test="${paginaCorrente eq 'ordine.jsp'}">
            <c:set var="href" value="ordini"/>
        </c:if>
            <div class="game-card back">
                <a id="back-container" class="game-card" href="${href}">
                    <i class="fa-solid fa-arrow-left"></i>
                    <span>Torna indietro</span>
                </a>
            </div>
    </c:if>
    <c:forEach var="videogioco" items="${videogiochi}" varStatus="status">

        <div class="game-card">
            <a href="gamePage?idVideogioco=${videogioco.idVideogioco}">
                <img src="https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/${videogioco.appIdSteam}/header.jpg" alt="${videogioco.titolo}">
            </a>

            <c:if test="${paginaCorrente eq 'preferiti.jsp'}">
                <a href="rimuoviPreferiti?paginaCorrente=preferiti&idVideogioco=${videogioco.idVideogioco}">
                    <i id="rimuovi" class="fa-solid fa-xmark"></i>
                </a>
            </c:if>

            <c:if test="${paginaCorrente eq 'disabled.jsp'}">
                <a href="attivaVideogioco?idVideogioco=${videogioco.idVideogioco}">
                    <i id="attiva" class="fa-solid fa-check"></i>
                </a>
            </c:if>

            <div class="game-card-content">
                <div class="game-info">
                    <h2>${videogioco.titolo} (${videogioco.piattaforma})</h2>

                    <div class="price-row">
                        <span class="discount-tag">-${videogioco.sconto}%</span>
                        <span class="price"><fmt:formatNumber value="${videogioco.prezzoScontato}" type="number" minFractionDigits="2" maxFractionDigits="2"/> €</span>
                    </div>
                </div>

                <c:if test="${paginaCorrente eq 'ordine.jsp' or paginaCorrente eq 'attivazione.jsp'}">
                    <c:set var="chiave" value="${chiavi[status.index]}" />
                    <span id="game-key" title="Chiave gioco">${chiave}</span>
                </c:if>
            </div>
        </div>
    </c:forEach>
</div>
