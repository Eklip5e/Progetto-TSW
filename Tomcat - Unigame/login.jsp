<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    request.setAttribute("paginaCorrente", "login.jsp");
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>UniGame - Login</title>

    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    <main>
        <%@ include file="WEB-INF/navbar.jsp" %>

        <div class="login">
            <div class="login-container">
                <h1>Accedi</h1>
                <form action="login" method="post">

                    <!-- Email -->
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input id="username" type="text" name="username" required>
                    </div>

                    <!-- Password -->
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input id="password" type="password" name="password" required>
                    </div>

                    <p style="color: var(--color-error)">${error}</p>

                    <input type="submit" id="login-button" value="Login">
                </form>

                <a href="register.jsp" class="signup-redirect">Non hai un account?</a>
            </div>
        </div>
    </main>

    <%@ include file="WEB-INF/footer.jsp" %>
</body>
</html>