<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UniGame - Profilo</title>

    <link rel="stylesheet" href="css/profilo.css">
</head>
<body>
<main>

<%@ include file="navbar.jsp" %>

<div class="user-profile">
    <div class="user-profile-header">
        <h1>Ciao, ${utente.nome}!</h1>
    </div>
    <div class="user-profile-content">

        <a href="ordini" id="user-orders">
            <i class="fa-solid fa-truck-fast"></i>
            <span>I miei ordini</span>
        </a>
        <a href="preferiti" id="user-favorites">
            <i class="fa-solid fa-heart"></i>
            <span>Preferiti</span>
        </a>

        <button id="update-section" onclick="openUpdateSection()">
            <span>Modifica profilo</span>
            <i class="fa-solid fa-arrow-right"></i>
        </button>

        <button onclick="apriModaleUpdateProfile()" id="update-user">
            <i class="fa-solid fa-user"></i>
            <span>Modifica profilo</span>
        </button>
        <button onclick="apriModaleUpdatePassword()" id="update-password">
            <i class="fa-solid fa-lock"></i>
            <span>Modifica password</span>
        </button>

        <c:if test="${sessionScope.utente != null  and sessionScope.utente.admin}">
                <button id="admin-section" onclick="openAdminSection()">
                    <span>Admin</span>
                    <i class="fa-solid fa-arrow-right"></i>
                </button>

                <a href="disattivati" id="idle-games">
                    <i class="fa-solid fa-ban"></i>
                    <span>Videogiochi Inattivi</span>
                </a>
        </c:if>

        <button id="user-section" onclick="openUserSection()">
            <span>Utente</span>
            <i class="fa-solid fa-arrow-right"></i>
        </button>
    </div>
</div>

    <%@ include file="modal-update-profile.jsp" %>
    <%@ include file="modal-update-password.jsp" %>

</main>

<%@ include file="footer.jsp" %>

<script>
    // Elementi utente base
    const modificaButton     = document.getElementById("update-section");
    const userButton         = document.getElementById("user-section");
    const modificaProfilo    = document.getElementById("update-user");
    const modificaPassword   = document.getElementById("update-password");
    const ordini             = document.getElementById("user-orders");
    const preferiti          = document.getElementById("user-favorites");

    // Elementi opzionali (solo admin)
    const adminButton        = document.getElementById("admin-section");
    const disattivati        = document.getElementById("idle-games");

    function openAdminSection() {
        hide(modificaButton, modificaProfilo, modificaPassword, ordini, preferiti, adminButton);
        show(disattivati, userButton);
    }

    function openUserSection() {
        show(modificaButton, ordini, preferiti);
        hide(modificaProfilo, modificaPassword, adminButton, disattivati, userButton);
    }

    function openUpdateSection() {
        hide(modificaButton, ordini, preferiti, disattivati);
        show(modificaProfilo, modificaPassword);

        <c:choose>
            <c:when test="${sessionScope.utente != null  and sessionScope.utente.admin}">
                show(adminButton);
                hide(userButton);
            </c:when>
            <c:otherwise>
                show(userButton);
            </c:otherwise>
        </c:choose>
    }

    // Utility per mostrare elementi
    function show(...elements) {
        elements.forEach(el => { if (el) el.style.display = "flex"; });
    }

    // Utility per nascondere elementi
    function hide(...elements) {
        elements.forEach(el => { if (el) el.style.display = "none"; });
    }
</script>
</body>
</html>