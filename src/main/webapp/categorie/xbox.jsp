<%@ page import="com.unigame.model.Videogioco" %>
<%@ page import="java.util.List" %>
<%@ page import="com.unigame.model.DAO.VideogiocoDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UniGame</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&family=Roboto&display=swap"
          rel="stylesheet">
</head>
<body>

<%@ include file="../navbar.jsp" %>

<!-- Game Grid Section -->
<%@ include file="../game-grid.jsp" %>

<!-- Modale -->
<%@ include file="../modal.jsp" %>

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

<%@ include file="../footer.jsp" %>

</body>
</html>