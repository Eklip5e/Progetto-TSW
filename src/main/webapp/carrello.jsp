<%@ page import="com.unigame.model.Videogioco" %>
<%@ page import="com.unigame.model.DAO.VideogiocoDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.unigame.model.RigaCarrello" %>
<%
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

<div class="contenitore-carrello">
    <div class="sezione-prodotti">
        <h2>Carrello</h2>
        <div class="carrello">
            <%
                if (righeCarrello != null) {
                    for (RigaCarrello riga : righeCarrello) {
                        Videogioco videogioco = videogiocoDAO.doRetrieveById(riga.getIdVideogioco());
                        if (videogioco != null) {
                            prezzoTotale += videogioco.getPrezzo();
            %>
            <div class="riga-carrello">
                <div class="info-gioco">
                    <div class="copertina">
                        <img src="https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/<%= videogioco.getAppIdSteam() %>/header.jpg" alt="<%= videogioco.getTitolo() %>" width="200">
                    </div>
                    <div class="azioni">
                        <h3><%= videogioco.getTitolo() %></h3>
                        <div class="pulsanti">
                            <a href="RimuoviDalCarrello?idVideogioco=<%= videogioco.getIdGame() %>" method="post"><i class="fa-solid fa-trash"></i></a>
                            <a href="#">Lista dei desideri</a>
                        </div>
                    </div>
                </div>
                <div class="prezzo-gioco">
                    <p><%= videogioco.getPrezzo() %> €</p>
                </div>
            </div>
            <%
                        }
                    }
                } else {
            %>
            <h3>Il carrello è vuoto</h3>
            <%
                }
            %>
            <hr>
        </div>
    </div>

    <%
        double scontoTotale = 0;  // qui puoi calcolare sconti se hai logiche
        double totaleFinale = prezzoTotale - scontoTotale;
    %>

    <div class="sezione-riepilogo">
        <h2>Riepilogo</h2>
        <div class="info-riepilogo">
            <ul class="riepilogo-lista">
                <li class="riepilogo-riga">
                    <span>Prezzo ufficiale</span>
                    <span><%= String.format("%.2f", prezzoTotale) %> €</span>
                </li>
                <li class="riepilogo-riga">
                    <span>Sconto</span>
                    <span>-<%= String.format("%.2f", scontoTotale) %> €</span>
                </li>
                <li class="riepilogo-riga">
                    <span>Totale</span>
                    <span><%= String.format("%.2f", totaleFinale) %> €</span>
                </li>
            </ul>

            <div class="pulsanti">
                <button>Vai al pagamento</button>
                <a href="home.jsp">Continua lo shopping</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>