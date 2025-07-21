<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="model.DAO.VideogiocoDAO" %>
<%@ page import="model.Videogioco" %>
<%@ page import="java.util.List" %>
<%@ page import="model.RigaCarrello" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    request.setAttribute("paginaCorrente", "pagamento.jsp");

    VideogiocoDAO videogiocoDAO = new VideogiocoDAO();

    List<RigaCarrello> righeCarrello = (List<RigaCarrello>) session.getAttribute("carrello");

    if (righeCarrello == null)
        righeCarrello = new ArrayList<>();

    String numeroCarta = (String) request.getAttribute("numeroCarta");
    String titolare = (String) request.getAttribute("titolare");
    String scadenza = (String) request.getAttribute("scadenza");
    String cvc = (String) request.getAttribute("cvc");

    if (numeroCarta == null) { numeroCarta = ""; }
    if (titolare == null) { titolare = ""; }
    if (scadenza == null) { scadenza = ""; }
    if (cvc == null) { cvc = ""; }
%>

<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>UniGame - Pagamento</title>

        <link rel="stylesheet" href="css/pagamento.css">

    </head>
    <body>
        <main>
            <%@ include file="navbar.jsp" %>

            <div class="payment">
                <div class="payment-container">
                    <form action="confermaAcquisto" method="post">
                        <h2>Metodo di pagamento</h2>
                        <div class="payment-method">
                            <div class="credit-card-controls">
                                <div class="form-group">
                                    <label for="card-number">Numero della carta</label>
                                    <input type="text" id="card-number" name="numeroCarta" placeholder="•••• •••• •••• ••••" value="${numeroCarta}">
                                </div>
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="dataScadenza">Data di scadenza</label>
                                        <input type="text" id="dataScadenza" name="scadenza" placeholder="MM/YY" value="${scadenza}">
                                    </div>
                                    <div class="form-group">
                                        <label for="cvc">Codice di sicurezza</label>
                                        <input type="text" id="cvc" name="cvc" placeholder="CVC" value="${cvc}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="card-holder">Titolare della carta</label>
                                    <input type="text" id="card-holder" name="titolare" placeholder="J. Smith" value="${titolare}">
                                </div>
                            </div>

                            <p style="color: var(--color-error)">${error}</p>
                        </div>

                        <div class="pay-button">
                            <div class="price-row">
                                <span id="summary-total">Totale</span>
                                <span><fmt:formatNumber value="${prezzoTotale}" type="number" minFractionDigits="2" maxFractionDigits="2"/> €</span>
                            </div>
                            <button type="submit">Acquista</button>
                        </div>
                    </form>

                    <div class="summary">
                        <h2>Riepilogo</h2>
                        <div class="summary-container">
                            <c:if test="${not empty carrello}">
                                <c:forEach var="riga" items="${carrello}">
                                    <c:set var="videogioco" value="${videogiochiMap[riga.idVideogioco]}" />
                                        <div class="row">
                                            <span>${videogioco.titolo}</span>
                                            <div class="price">
                                                <span>${riga.quantità}x</span>
                                                <span><fmt:formatNumber value="${riga.prezzoUnitario * riga.quantità}" type="number" minFractionDigits="2" maxFractionDigits="2"/> €</span>
                                            </div>
                                        </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <%@ include file="footer.jsp" %>

        <script src="js/inputExpirationDate.js"></script>
        <script>
            document.addEventListener('DOMContentLoaded', () => {

                const cardNumber = document.getElementById("card-number");

                cardNumber.addEventListener('input', () => {
                    let val = cardNumber.value.replace(/\D/g, '').slice(0, 16); // max 16 cifre

                    const parts = val.match(/^(\d{0,4})(\d{0,4})(\d{0,4})(\d{0,4})$/);

                    if (!parts) return;

                    cardNumber.value = parts[1] + (parts[2] ? ' ' + parts[2] : '') + (parts[3] ? ' ' + parts[3] : '') + (parts[4] ? ' ' + parts[4] : '');
                });
            });
        </script>
    </body>
</html>
