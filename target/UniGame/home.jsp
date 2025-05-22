<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UniGame</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/register.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&family=Roboto&display=swap" rel="stylesheet">
</head>
<body>

<%@ include file="navbar.jsp" %>

<!-- Game Banner Section -->
<section class="game-banner">
    <div class="banner-content">
        <h1>Grand Theft Auto VI</h1>
        <div class="price-row">
            <span class="discount-tag">-30%</span>
            <p class="price">€59,99</p>
        </div>
    </div>
</section>

<!-- Game Grid Section -->
<section class="game-grid">
    <div class="game-card">
        <img src="img/game3.png" alt="Game 3">
        <h2>Grand Theft Auto V</h2>
        <div class="card-price-row">
            <span class="discount-tag">-20%</span>
            <p class="price">€47,99</p>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>

</body>
</html>