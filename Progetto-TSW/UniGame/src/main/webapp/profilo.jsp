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
<main>

<%@ include file="navbar.jsp" %>

<div class="user-profile">
    <div class="user-profile-header">
        <span>Bentornato, <%= utente.getUsername() %>!</span>
        <button onclick="apriModaleUpdateProfile()">Modifica profilo</button>
    </div>
    <div class="user-profile-content">
        <a onclick="window.location.href='mostraOrdini'" class="user-orders">
            <i class="fa-solid fa-truck-fast"></i>
            <span>I miei ordini</span>
        </a>
        <a class="user-favorites">
            <i class="fa-solid fa-heart"></i>
            <span>Preferiti</span>
        </a>
        <a class="user-reviews">
            <i class="fa-solid fa-star"></i>
            <span>Recensioni</span>
        </a>
    </div>
</div>

    <%@ include file="modal-update-profile.jsp" %>

</main>

<%@ include file="footer.jsp" %>

</body>
</html>