<%@ page import="model.RigaCarrello" %>
<%@ page import="model.DAO.VideogiocoDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Videogioco" %>
<%@ page import="java.util.ArrayList" %>

<%
    request.setAttribute("paginaCorrente", "carrello.jsp");

    Utente utente = (Utente) session.getAttribute("utente");

    VideogiocoDAO videogiocoDAO = new VideogiocoDAO();

    List<RigaCarrello> righeCarrello = (List<RigaCarrello>) session.getAttribute("righeCarrello");

    if (righeCarrello == null)
        righeCarrello = new ArrayList<>();

    double prezzoUfficiale = 0;
    double scontoTotale = 0;
    double prezzoTotale = 0;
    for (RigaCarrello riga : righeCarrello) {
        Videogioco videogioco = videogiocoDAO.doRetrieveById(riga.getIdVideogioco());
        double sconto = ((double) videogioco.getSconto() / 100) * videogioco.getPrezzo();
        prezzoUfficiale += videogioco.getPrezzo() * riga.getQuantità();
        scontoTotale += sconto * riga.getQuantità();
        prezzoTotale += (videogioco.getPrezzo() - sconto) * riga.getQuantità();
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
                <div class="cart-page-content">
                    <div class="cart">
                        <h2>Carrello</h2>
                        <div class="cart-content">
                            <%
                                if (!righeCarrello.isEmpty()) {
                                    for (RigaCarrello riga : righeCarrello) {
                                        Videogioco videogioco = videogiocoDAO.doRetrieveById(riga.getIdVideogioco());
                            %>
                                        <div class="cart-item">
                                            <img id="cover" src="https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/<%= videogioco.getAppIdSteam() %>/header.jpg" alt="<%= videogioco.getTitolo() %>" width="200">
                                            <div class="item-container">
                                                <div class="info-container">
                                                    <h3><%= videogioco.getTitolo() %></h3>
                                                    <div class="actions">
                                                        <a href="RimuoviDalCarrello?idVideogioco=<%= videogioco.getIdVideogioco() %>" method="post">
                                                            <i class="fa-solid fa-trash"></i>
                                                        </a>
                                                        <a href="addFavorites?idVideogioco=<%= videogioco.getIdVideogioco() %>">Lista dei desideri</a>
                                                    </div>
                                                </div>
                                                <div class="price-container">
                                                    <div class="price">
                                                        <span id="discount-tag">-<%= videogioco.getSconto() %>%</span>
                                                        <span id="price"><%= String.format("%.2f", riga.getPrezzoUnitario()) %> €</span>
                                                    </div>
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

                    <div class="cart-summary">
                        <h2>Riepilogo</h2>
                        <div class="summary-content">
                            <div class="price-rows">
                                <div class="price-row">
                                    <span>Prezzo ufficiale</span>
                                    <span><%= String.format("%.2f", prezzoUfficiale) %> €</span>
                                </div>
                                <div class="price-row">
                                    <span>Sconto</span>
                                    <span><%= String.format("%.2f", scontoTotale) %> €</span>
                                </div>
                                <div class="price-row total">
                                    <span>Somma parziale</span>
                                    <span><%= String.format("%.2f", prezzoTotale) %> €</span>
                                </div>
                            </div>

                            <div class="actions">
                                <%
                                    String statoBottone = "";
                                    if (righeCarrello.isEmpty()) {
                                        statoBottone = "disabled";
                                    }
                                %>
                                <%
                                    String redirect = "pagamento";
                                    if (utente == null)
                                        redirect = "login.jsp";
                                %>
                                <a href="<%= redirect %>" id="pagamento-button" <%= statoBottone %>>Vai al pagamento</a>
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
            // Prendi tutte le select quantità
            const quantitySelects = document.querySelectorAll('.quantity-select');

            quantitySelects.forEach(select => {
                select.addEventListener('change', function() {
                    const idVideogioco = this.getAttribute('data-id');
                    const quantita = this.value;

                    // Crea XMLHttpRequest
                    const xhttp = new XMLHttpRequest();

                    xhttp.onreadystatechange = function() {
                        if (this.readyState === 4) {
                            if (this.status === 200) {
                                // Opzionale: gestisci risposta JSON
                                const response = JSON.parse(this.responseText);
                                if (response.success) {
                                    console.log("Quantità aggiornata con successo");
                                    window.location.reload();  // <-- ricarica pagina
                                } else {
                                    alert("Errore nell'aggiornamento della quantità");
                                }
                            } else {
                                alert("Errore nella richiesta AJAX");
                            }
                        }
                    };

                    xhttp.open("POST", "aggiornaQuantita", true);
                    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xhttp.send("idVideogioco=" + encodeURIComponent(idVideogioco) + "&quantita=" + encodeURIComponent(quantita));
                });
            });
        </script>
</body>
</html>