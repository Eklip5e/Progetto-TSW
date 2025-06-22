<%@ page import="com.unigame.model.Videogioco" %>
<head>
    <link rel="stylesheet" href="css/modal.css">
</head>

<div class="modal-update-banner">
    <div class="modal-content">
        <span id="close-modal" onclick="chiudiModaleUpdateBanner()">x</span>
        <h2>Modifica banner</h2>
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
        document.getElementById("modal-update-banner")[0].style.display = "flex";
    }

    function chiudiModaleUpdateBanner() {
        document.getElementById("modal-update-banner")[0].style.display = "none";
    }
</script>
