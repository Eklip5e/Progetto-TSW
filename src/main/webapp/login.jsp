<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UniGame - Login</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/register.css"> <!-- stessa di register -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>

<%@ include file="navbar.jsp" %>

<section class="register">
    <form action="login" method="post">

        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" required>
        </div>

        <div class="form-group password-group">
            <label for="password">Password</label>
            <div class="password-container">
                <input type="password" name="password" id="password" required>
            </div>
        </div>

        <input type="submit" id="register" value="Login"> <!-- stesso id del bottone -->

        <div class="form-group" id="signup-redirect">
            <a href="register.jsp" class="signup-redirect">Non hai ancora un account?</a>
        </div>

        <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
        <% } %>

    </form>
</section>

<%@ include file="footer.jsp" %>

</body>
</html>