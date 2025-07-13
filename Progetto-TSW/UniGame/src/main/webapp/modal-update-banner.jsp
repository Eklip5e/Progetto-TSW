<%@ page import="model.Videogioco" %>
<head>
    <link rel="stylesheet" href="css/modal.css">
</head>

<div class="modal-update-banner">
    <div class="modal-content">
        <span id="close-modal" onclick="chiudiModaleUpdateBanner()">x</span>
        <h2>Modifica banner</h2>
        <form action="updateBanner">
            <select name="videogioco">
                <option value="">-- Seleziona un videogioco --</option>
                <%
                    for (Videogioco videogioco : videogiochi) {
                %>
                        <option value="<%= videogioco.getIdVideogioco() %>"><%= videogioco.getTitolo() %></option>
                <%
                    }
                %>
            </select>
            <input type="submit" id="update-banner-button" value="Modifica">
        </form>
    </div>
</div>

<script>
    function apriModaleUpdateBanner() {
        document.getElementsByClassName("modal-update-banner")[0].style.display = "flex";
    }

    function chiudiModaleUpdateBanner() {
        document.getElementsByClassName("modal-update-banner")[0].style.display = "none";
    }
</script>
