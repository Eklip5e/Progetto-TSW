<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.unigame.model.DAO.VideogiocoDAO, com.unigame.model.Videogioco" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UniGame</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&family=Roboto&display=swap" rel="stylesheet">
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <section class="game-banner" style="background: linear-gradient(to right, rgba(0, 0, 0, 0.9), rgba(0, 0, 0, 0.7) 40%, transparent 90%), url('http://cdn.cloudflare.steamstatic.com/steam/apps/1601570/library_hero.jpg') no-repeat center center / cover;">
        <div class="banner-content">
            <h1>Grand Theft Auto VI</h1>
            <div class="price-row">
                <span class="discount-tag">-30%</span>
                <p class="price">59,99 €</p>
            </div>
        </div>
    </section>

<!-- Game Grid Section -->
<%@ include file="game-grid.jsp" %>

<!-- Modale -->
<%@ include file="modal.jsp" %>

<script>
    function closeAddModal() {
        document.getElementById('addModal').style.display = 'none';
    }

    window.onclick = function (event) {
        let modal = document.getElementById('addModal');
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    }
</script>

<script>
    <% if (request.getAttribute("error") != null) { %>
    document.addEventListener("DOMContentLoaded", function () {
        document.getElementById('addModal').style.display = 'flex';
    });
    <% } %>
</script>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        document.querySelectorAll(".delete-button").forEach(button => {
            button.addEventListener("click", function () {
                const idGame = this.getAttribute("data-id");

                const conferma = confirm("Sei sicuro di voler eliminare questo videogioco?");
                if (!conferma) {
                    return; // Annulla l'operazione se l'utente clicca "Annulla"
                }

                fetch("RimuoviVideogiocoServlet?id=" + idGame)
                    .then(res => {
                        if (res.ok) {
                            location.reload(); // Ricarica la pagina dopo l’eliminazione
                        } else {
                            alert("Errore durante la rimozione del videogioco.");
                        }
                    });
            });
        });
    });
</script>

<%@ include file="footer.jsp" %>

</body>
</html>