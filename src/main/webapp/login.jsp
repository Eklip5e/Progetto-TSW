<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navbar</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>
<header>
    <nav>
        <a href="index.html" class="logo">
            <img src="img/logo.png" alt="logo" style="width:60px;height:60px;">
        </a>
    </nav>
    <div class="header-actions">
        <a class="cta" href="#"><i class="fa-solid fa-cart-shopping icon"></i></a>
        <a class="cta" href="register.html"><i class="fa-regular fa-circle-user icon"></i></a>
    </div>
</header>
<section class="register">
    <form action="LoginServlet" method="post">

        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" required>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" id="password" required>
        </div>

        <input type="submit" value="Login">

        <div class="form-group" id="signup-redirect">
        <a href="register.jsp" class="signup-redirect">Non hai ancora un account?</a>
        </div>

        <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
        <% } %>

    </form>
</section>
</body>
</html>
