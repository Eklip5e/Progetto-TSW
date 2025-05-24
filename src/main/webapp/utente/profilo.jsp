<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.*, com.unigame.model.DAO.UtenteDAO, com.unigame.model.Utente" %>
<%@ page import="jakarta.servlet.*, java.util.*" %>
<%@ page import="com.unigame.model.DAO.UtenteDAO" %>

<%
    session = request.getSession(false);
    String username = (session != null) ? (String) session.getAttribute("username") : null;

    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    UtenteDAO utenteDAO = new UtenteDAO();
    Utente utente = utenteDAO.doRetrieveByUsername(username);  // QUI è importante il tipo Utente
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Profilo Utente</title>
    <link rel="stylesheet" href="../../../../target/UniGame/css/style.css">
    <link rel="stylesheet" href="../../../../target/UniGame/css/profilo.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/principali/navbar.jsp" %>

<div class="profile-container">
    <div class="profile-header">
        <h1>Ciao, <%= utente.getNome() %>!</h1>
    </div>

    <div class="profile-info">
        <span><strong>Username:</strong> <%= utente.getUsername() %></span>
        <span><strong>Nome:</strong> <%= utente.getNome() %></span>
        <span><strong>Cognome:</strong> <%= utente.getCognome() %></span>
        <span><strong>Email:</strong> <%= utente.getEmail() %></span>
    </div>

    <a class="edit-button" href="#">Modifica Profilo</a>
</div>

<%@ include file="/principali/footer.jsp" %>

</body>
</html>