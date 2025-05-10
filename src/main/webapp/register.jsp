<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Registrazione</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<section class="register">
    <form action="RegisterServlet" method="post">

        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" required>
        </div>

        <div class="form-group">
            <label for="email">E-mail</label>
            <input type="email" name="email" id="email" required>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" id="password" required>
        </div>

        <div class="form-group">
            <label for="data_nascita">Data di Nascita</label>
            <input type="date" name="data_nascita" id="data_nascita" required>
        </div>

        <div class="form-group">
            <label for="indirizzo">Indirizzo</label>
            <input type="text" name="indirizzo" id="indirizzo" required>
        </div>

        <div class="form-group">
            <label for="cap">CAP</label>
            <input type="text" name="cap" id="cap" required>
        </div>

        <div class="form-group">
            <label for="citta">Città</label>
            <input type="text" name="citta" id="citta" required>
        </div>

        <input type="submit" value="Register">

        <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
        <% } %>

    </form>
</section>
</body>
</html>
