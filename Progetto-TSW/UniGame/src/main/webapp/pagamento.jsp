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
        <title>UniGame - Pagamento</title>

        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/pagamento.css">

    </head>
    <body>
        <main>
            <%@ include file="navbar.jsp" %>

            <div class="payment">
                <div class="payment-content">
                    <div class="billing-address">
                        <h2>Indirizzo di fatturazione</h2>
                        <form>
                            <input type="text" placeholder="Nome completo">
                            <input type="text" placeholder="Indirizzo">
                        </form>
                    </div>
                    <div class="payment-method">
                        <h2>Metodi di pagamento</h2>
                        <div class="credit-card-controls">
                            <div class="form-group">
                                <label for="card-number">Numero della carta</label>
                                <input type="text" id="card-number" placeholder="•••• •••• •••• ••••">
                            </div>
                            <div class="form-group">
                                <label for="card-holder">Titolare della carta</label>
                                <input type="text" id="card-holder" placeholder="J. Smith">
                            </div>
                            <div class="form-row">
                                <div class="form-group">
                                    <label for="expiry-date">Data di scadenza</label>
                                    <input type="text" id="expiry-date" placeholder="MM/YY">
                                </div>
                                <div class="form-group">
                                    <label for="cvc">Codice di sicurezza</label>
                                    <input type="text" id="cvc" placeholder="CVC">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="summary">
                    <h2>Riepilogo</h2>
                        <%
                            RigaCarrelloDAO rigaCarrelloDAO = new RigaCarrelloDAO();
                            RigaCarrello rigaCarrello;
                            if (!carrello.isEmpty()) {
                                for (Videogioco videogioco : carrello) {
                                    rigaCarrello = rigaCarrelloDAO.doRetrieveByIdVideogioco(videogioco.getIdVideogioco());
                        %>
                                    <div class="game-container">
                                        <span id="title"><%= videogioco.getTitolo() %></span>
                                        <div class="numbers">
                                            <span><%= rigaCarrello.getQuantità() %>x</span>
                                            <span id="price"><%= videogioco.getPrezzo() %> €</span>
                                        </div>
                                    </div>
                        <%
                                }
                            }
                        %>
                    <div class="pay-button">
                        <div class="price-row">
                            <span id="summary-total">Totale</span>
                            <span><%= String.format("%.2f", prezzoTotale) %> €</span>
                        </div>
                        <button onclick="window.location.href='MostraCarrello?paginaCorrente=attivazione.jsp'">Acquista</button>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
