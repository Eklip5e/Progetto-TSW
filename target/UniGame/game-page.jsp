<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>UniGame</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/register.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&family=Roboto&display=swap"
          rel="stylesheet">
    </head>
    <body>

        <%@ include file="navbar.jsp" %>

        <!-- Game Banner Section -->
        <section class="game-banner" style="background-image: url('img/banners/theLastOfUs-banner.png')"></section>

        <section class="game-details">
            <div class="game-cover">
                <p>COPERTINA GIOCO</p>
            </div>
            <div class="game-info">
                <p>INFORMAZIONI GIOCO</p>
                <p>PREZZO, SCONTO<br>PIATTAFORMA<br>AGGIUNGI AL CARRELLO ECC.</p>
            </div>
        </section>

        <%@ include file="footer.jsp" %>

    </body>
</html>
