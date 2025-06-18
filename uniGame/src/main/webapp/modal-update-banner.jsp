<%@ page import="com.unigame.model.Videogioco" %>
<head>
    <link rel="stylesheet" href="css/modal.css">
</head>

<div id="modal-update-banner" class="modal-update-banner">
    <div class="modal-content">
        <div class="close-modal">
            <span onclick="chiudiModaleUpdateBanner()">x</span>
        </div>
        <h2>Nuovo Videogioco</h2>
        <form action="updateBanner">
            <select name="videogioco">
                <option value="">-- Seleziona un gioco --</option>
                <%
                    for (Videogioco videogioco : videogiochi) {
                %>
                <option value="<%= videogioco.getIdVideogioco() %>"><%= videogioco.getTitolo() %></option>
                <%
                    }
                %>
            </select>
            <input type="submit" value="Modifica">
        </form>
    </div>
</div>

<script>
    function apriModaleUpdateBanner() {
        document.getElementById("modal-update-banner").style.display = "flex";
        document.body.style.overflow = "hidden";
    }

    function chiudiModaleUpdateBanner() {
        document.getElementById("modal-update-banner").style.display = "none";
        document.body.style.overflow = "auto";
    }
</script>
