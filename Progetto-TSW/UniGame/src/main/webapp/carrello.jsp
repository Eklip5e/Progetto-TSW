<%@ page import="model.RigaCarrello" %>
<%@ page import="model.DAO.VideogiocoDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Videogioco" %>

<%
    request.setAttribute("paginaCorrente", "carrello.jsp");

    VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
    List<RigaCarrello> righeCarrello = (List<RigaCarrello>) request.getAttribute("righeCarrello");
    double prezzoTotale = 0;
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
                            if (righeCarrello != null) {
                                for (RigaCarrello riga : righeCarrello) {
                                    Videogioco videogioco = videogiocoDAO.doRetrieveById(riga.getIdVideogioco());
                                    if (videogioco != null) {
                                        prezzoTotale += videogioco.getPrezzo();
                        %>
                        <div class="product-line">
                            <div class="game-info">com
                                <img id="cover" src="https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/<%= videogioco.getAppIdSteam() %>/header.jpg" alt="<%= videogioco.getTitolo() %>" width="200">
                                <h3><%= videogioco.getTitolo() %></h3>
                                <div class="actions">
                                    <a href="RimuoviDalCarrello?idVideogioco=<%= videogioco.getIdVideogioco() %>" method="post"><i class="fa-solid fa-trash"></i></a>
                                    <a href="#">Lista dei desideri</a>
                                </div>
                                <span id="price"><%= videogioco.getPrezzo() %> €</span>
                            </div>
                        </div>
                        <%
                                    }
                                }
                            } else {
                        %>
                                <div class="empty-cart">
                                    <i class="fa-solid fa-cart-shopping"></i>
                                    <span>Il tuo carrello è vuoto</span>
                                    <p>Non hai ancora aggiunto nessun articolo nel carrello. Sfoglia il sito per trovare offerte fantastiche! <p>
                                    <button>Scopri i giochi</button>
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
                        <button id="button">Vai al pagamento</button>
                        <span id="choice">o</span>
                        <a id="back">Continua lo shopping</a>
                    </div>
                </div>
            </div>
        </div>
    </main>

<%@ include file="footer.jsp" %>

</body>
</html>