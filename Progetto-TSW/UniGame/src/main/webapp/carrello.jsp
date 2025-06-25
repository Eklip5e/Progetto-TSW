<%@ page import="model.RigaCarrello" %>
<%@ page import="model.DAO.VideogiocoDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Videogioco" %>
<%@ page import="java.util.ArrayList" %>

<%
    request.setAttribute("paginaCorrente", "carrello.jsp");

    VideogiocoDAO videogiocoDAO = new VideogiocoDAO();

    Utente utente = (Utente) session.getAttribute("utente");

    List<RigaCarrello> righeCarrello;
    righeCarrello = (List<RigaCarrello>) session.getAttribute("righeCarrello");

    if (righeCarrello == null)
        righeCarrello = new ArrayList<>();

    boolean statoCarrello;
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
            <div class="cart-page-content">
            <div class="cart">
                <h2>Carrello</h2>
                <div class="cart-content">
                    <div class="products">
                        <%
                            if (!righeCarrello.isEmpty()) {
                                statoCarrello = true;
                                for (RigaCarrello riga : righeCarrello) {
                                    Videogioco videogioco = videogiocoDAO.doRetrieveById(riga.getIdVideogioco());
                        %>
                                    <div class="cart-item">
                                        <img id="cover" src="https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/<%= videogioco.getAppIdSteam() %>/header.jpg" alt="<%= videogioco.getTitolo() %>" width="200">
                                        <div class="item-container">
                                            <h3><%= videogioco.getTitolo() %></h3>
                                            <div class="price-container">
                                                <div class="numbers">
                                                    <span id="price"><%= videogioco.getPrezzo() %> €</span>
                                                    <select class="quantity-select" data-id="<%= videogioco.getIdVideogioco() %>">
                                                        <%
                                                            for (int i = 1; i <= 10; i++) {
                                                                String selected = (i == riga.getQuantità()) ? "selected" : "";
                                                        %>
                                                                <option value="<%= i %>" <%= selected %>><%= i %></option>
                                                        <%
                                                            }
                                                        %>
                                                    </select>
                                                </div>
                                                <hr>
                                                <div class="actions">
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
                                statoCarrello = false;
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

            <div class="cart-summary">
                <h2>Riepilogo</h2>
                <div class="summary-content">
                    <div class="price-row">
                        <div class="summary-row">
                            <span>Prezzo ufficiale</span>
                            <span><%= String.format("%.2f", (Double) request.getAttribute("prezzoUfficiale")) %> €</span>
                        </div>
                        <div class="summary-row">
                            <span>Sconto</span>
                            <span><%= String.format("%.2f", (Double) request.getAttribute("scontoTotale")) %> €</span>
                        </div>
                        <div class="summary-row total">
                            <span>Somma parziale</span>
                            <span><%= String.format("%.2f", (Double) request.getAttribute("prezzoTotale")) %> €</span>
                        </div>
                    </div>

                    <div class="actions">
                        <form action="pagamento" method="post">
                            <%
                                String statoBottone = "";
                                if (!statoCarrello) {
                                    statoBottone = "disabled";
                                }
                            %>
                            <button id="pagamento-button" <%= statoBottone %>>Vai al pagamento</button>
                        </form>
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
        document.querySelectorAll('.quantity-select').forEach(select => {
            select.addEventListener('change', function () {
                const idVideogioco = this.getAttribute('data-id');
                const quantita = this.value;

                const xhr = new XMLHttpRequest();
                xhr.open("POST", "AggiornaQuantitaCarrello", true);
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4) {
                        if (xhr.status === 200) {
                            location.reload(); // ricarica la pagina se tutto ok
                        } else {
                            alert("Errore durante l'aggiornamento della quantità.");
                            console.error("Errore AJAX:", xhr.responseText);
                        }
                    }
                };

                xhr.send("idVideogioco=" + encodeURIComponent(idVideogioco) + "&quantita=" + encodeURIComponent(quantita));
            });
        });
    </script>

</body>
</html>