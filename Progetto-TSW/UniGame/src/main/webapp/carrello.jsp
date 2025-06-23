<%@ page import="model.RigaCarrello" %>
<%@ page import="model.DAO.VideogiocoDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Videogioco" %>
<%@ page import="java.util.ArrayList" %>

<%
    request.setAttribute("paginaCorrente", "carrello.jsp");

    VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
    List<Videogioco> giochiCarrello = new ArrayList<>();
    double prezzoTotale = 0;

    Utente utente = (Utente) session.getAttribute("utente");

    if(utente != null) {
        List<RigaCarrello> carrello = (List<RigaCarrello>) request.getAttribute("righeCarrello");
        if (carrello != null) {
            for (RigaCarrello riga : carrello) {
                Videogioco videogioco = videogiocoDAO.doRetrieveById(riga.getIdVideogioco());
                if (videogioco != null) {
                    for (int i = 0; i < riga.getQuantità(); i++) {
                        giochiCarrello.add(videogioco);
                        prezzoTotale += videogioco.getPrezzo();
                    }
                }
            }
        }
    } else {
        List<Integer> carrelloGuest = (List<Integer>) session.getAttribute("carrelloGuest");
        if (carrelloGuest != null) {
            for (Integer idVideogioco : carrelloGuest) {
                Videogioco videogioco = videogiocoDAO.doRetrieveById(idVideogioco);
                if (videogioco != null) {
                    giochiCarrello.add(videogioco);
                    prezzoTotale += videogioco.getPrezzo();
                }
            }
        }
    }
%>

<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>UniGame</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/carrello.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&family=Roboto&display=swap" rel="stylesheet">
    </head>
    <body>
    <main>
        <%@ include file="navbar.jsp" %>

        <div class="cart-page">
            <div class="cart">
                <h2>Carrello</h2>
                <div class="cart-content">
                    <div class="products">
                        <%
                            if (!giochiCarrello.isEmpty()) {
                                for (Videogioco videogioco : giochiCarrello) {
                        %>
                                    <div class="product-line">
                                        <img id="cover" src="https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/<%= videogioco.getAppIdSteam() %>/header.jpg" alt="<%= videogioco.getTitolo() %>" width="200">
                                        <div class="game-info">
                                            <h3><%= videogioco.getTitolo() %></h3>
                                            <div class="purchase-options">
                                                <div class="price-quantity">
                                                    <span class="price"><%= videogioco.getPrezzo() %> €</span>
                                                    <select class="quantity-select">
                                                        <%
                                                            for (int i = 1; i <= 10; i++) {
                                                        %>
                                                        <option><%= i %></option>
                                                        <%
                                                            }
                                                        %>
                                                    </select>
                                                </div>

                                                <div class="cart-actions">
                                                    <a href="RimuoviDalCarrello?idVideogioco=<%= videogioco.getIdVideogioco() %>" method="post">
                                                        <i class="fa-solid fa-trash"></i>
                                                    </a>
                                                    <a href="#">Lista dei desideri</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                        <%
                                }
                            } else {
                        %>
                                <div class="empty-cart">
                                    <i class="fa-solid fa-cart-shopping"></i>
                                    <span>Il tuo carrello è vuoto</span>
                                    <p>Non hai ancora aggiunto nessun articolo nel carrello. Sfoglia il sito per trovare offerte fantastiche! <p>
                                    <button onclick="window.location.href='home.jsp'">Scopri i giochi</button>
                                </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>

            <%
                double scontoTotale = 0;  // qui puoi calcolare sconti se hai logiche
                double totaleFinale = prezzoTotale - scontoTotale;
            %>

            <div class="cart-summary">
                <h2>Riepilogo</h2>
                <div class="summary-content">
                    <div class="price-row">
                        <div class="summary-row">
                            <span>Prezzo ufficiale</span>
                            <span>0 €</span>
                        </div>
                        <div class="summary-row">
                            <span>Sconto</span>
                            <span>0 €</span>
                        </div>
                        <div class="summary-row total">
                            <span>Somma parziale</span>
                            <span>0 €</span>
                        </div>
                    </div>

                    <div class="actions">
                        <button id="button" onclick="window.location.href='pagamento.jsp'">Vai al pagamento</button>
                        <span id="choice">o</span>
                        <a id="back" href="home.jsp">Continua lo shopping</a>
                    </div>
                </div>
            </div>
        </div>
    </main>

<%@ include file="footer.jsp" %>

</body>
</html>