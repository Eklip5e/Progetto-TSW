<%@ page import="com.unigame.model.Videogioco" %>
<%@ page import="com.unigame.model.DAO.VideogiocoDAO" %>

<%
    String id = request.getParameter("id");
    Videogioco videogioco = new VideogiocoDAO().doRetrieveById(Integer.parseInt(id));
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UniGame</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/game-page.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&family=Roboto&display=swap" rel="stylesheet">
</head>
<body>

<%@ include file="navbar.jsp" %>

<!-- Game Banner Section -->
<section class="game-banner" style="background-image: url('img/banners/theLastOfUs-banner.png')"></section>

<div class="content">
    <div class="panel-container">
        <div class="game-cover">
            <img src="img/game1.png" alt="Copertina Gioco">
        </div>
        <div class="game-info">
            <h2><%= videogioco.getTitolo() %></h2>
            <div class="stock">
                <i class="fa-solid fa-check"></i>
                <span>Disponibile</span>
            </div>
            <select>
                <option value="pc">PC</option>
                <option value="playstation">PlayStation</option>
                <option value="xbox">Xbox</option>
                <option value="nintendo">Nintendo</option>
            </select>
            <div class="amount">
                <div class="discount">
                    <span>-<%= videogioco.getSconto() %>%</span>
                </div>
                <div class="total">
                    <span><%= videogioco.getPrezzo() %> €</span>
                </div>
            </div>
            <div class="actions">
                <div class="wish-list">
                    <i class="fa-regular fa-heart"></i>
                </div>
                <button class="add-to-cart">Aggiungi al Carrello</button>
            </div>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', () => {
        const wishListIcon = document.querySelector('.wish-list i');

        wishListIcon.addEventListener('click', () => {
            if (wishListIcon.classList.contains('fa-regular')) {
                // Passa a preferito
                wishListIcon.classList.remove('fa-regular');
                wishListIcon.classList.add('fa-solid');
            } else {
                // Togli da preferiti
                wishListIcon.classList.remove('fa-solid');
                wishListIcon.classList.add('fa-regular');
            }
        });
    });
</script>

<%@ include file="footer.jsp" %>

</body>
</html>