<%@ page import="com.unigame.model.Videogioco" %>
<head>
    <link rel="stylesheet" href="css/modal.css">
</head>

<div id="modal-update-game" class="modal-update-game">
    <div class="modal-content">
        <div class="close-modal">
            <span onclick="chiudiModaleUpdate()">x</span>
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
    function apriModaleUpdate() {
        document.getElementById("modal-update-game").style.display = "flex";
        document.body.style.overflow = "hidden";
    }

    function chiudiModaleUpdate() {
        document.getElementById("modal-update-game").style.display = "none";
        document.body.style.overflow = "auto";
    }
</script>
