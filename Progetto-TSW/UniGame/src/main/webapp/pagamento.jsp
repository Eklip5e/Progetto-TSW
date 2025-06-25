<%@ page import="model.DAO.VideogiocoDAO" %>
<%@ page import="model.Videogioco" %>
<%@ page import="java.util.List" %>
<%@ page import="model.RigaCarrello" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.DAO.RigaCarrelloDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    request.setAttribute("paginaCorrente", "pagamento.jsp");

    VideogiocoDAO videogiocoDAO = new VideogiocoDAO();

    List<RigaCarrello> righeCarrello = (List<RigaCarrello>) session.getAttribute("userCart");

    if (righeCarrello == null)
        righeCarrello = new ArrayList<>();

    double prezzoTotale = 0;
%>

<html>
    <head>
        <title>UniGame - Pagamento</title>

        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/pagamento.css">

    </head>
    <body>
        <main>
            <%@ include file="navbar.jsp" %>

            <div class="payment">
                <div class="payment-container">
                    <form action="confermaAcquisto" method="post">
                        <h2>Metodi di pagamento</h2>
                        <div class="payment-method">
                            <div class="credit-card-controls">
                                <div class="form-group">
                                    <label for="card-number">Numero della carta</label>
                                    <input type="text" id="card-number" name="numeroCarta" placeholder="•••• •••• •••• ••••">
                                </div>
                                <div class="form-group">
                                    <label for="card-holder">Titolare della carta</label>
                                    <input type="text" id="card-holder" name="titolare" placeholder="J. Smith">
                                </div>
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="expiry-date">Data di scadenza</label>
                                        <input type="text" id="expiry-date" name="scadenza" placeholder="MM/YY">
                                    </div>
                                    <div class="form-group">
                                        <label for="cvc">Codice di sicurezza</label>
                                        <input type="text" id="cvc" name="cvc" placeholder="CVC">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="pay-button">
                            <div class="price-row">
                                <span id="summary-total">Totale</span>
                                <span><%= String.format("%.2f", prezzoTotale) %> €</span>
                            </div>
                            <button type="submit">Acquista</button>
                        </div>
                    </form>

                    <div class="summary">
                        <h2>Riepilogo</h2>
                        <div class="summary-container">
                            <%
                                if (!righeCarrello.isEmpty()) {
                                    for (RigaCarrello riga : righeCarrello) {
                                        Videogioco videogioco = videogiocoDAO.doRetrieveById(riga.getIdVideogioco());
                            %>
                                        <div class="row">
                                            <span><%= videogioco.getTitolo() %></span>
                                            <div class="price">
                                                <span><%= riga.getQuantità() %>x</span>
                                                <span><%= videogioco.getPrezzo() %> €</span>
                                            </div>
                                        </div>
                            <%
                                    }
                                }
                            %>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
