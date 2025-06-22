<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.*, model.DAO.UtenteDAO, model.Utente" %>
<%@ page import="jakarta.servlet.*, java.util.*" %>
<%@ page import="model.DAO.UtenteDAO" %>

<%
    session = request.getSession(false);
    Utente utente = (session != null) ? (Utente) session.getAttribute("utente") : null;

    if (utente == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Profilo Utente</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/profilo.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="profile-container">
    <div class="profile-header">
        <h1>Ciao, <%= utente.getUsername() %>!</h1>
    </div>

    <div class="profile-info">
        <span><strong>Username:</strong> <%= utente.getUsername() %></span>
        <span><strong>Email:</strong> <%= utente.getEmail() %></span>
        <span><strong>Nome:</strong> <%= utente.getNome() %></span>
        <span><strong>Cognome:</strong> <%= utente.getCognome() %></span>
    </div>

    <a class="edit-button" href="#">Modifica Profilo</a>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>