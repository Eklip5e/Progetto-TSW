<link rel="stylesheet" href="css/modal.css">

<div class="modal-update-banner">
    <div class="modal-container">
        <button id="close-modal" onclick="chiudiModaleUpdateBanner()"><i class="fa-solid fa-xmark"></i></button>
        <h2>Modifica banner</h2>
        <form action="modificaBanner" method="post">

            <div class="form-group">
                <label for="idVideogioco">Videogioco</label>
                <select id="idVideogioco" name="idVideogioco">
                    <c:forEach var="videogioco" items="${videogiochi}">
                        <option value="${videogioco.idVideogioco}">(${videogioco.piattaforma}) ${videogioco.titolo}</option>
                    </c:forEach>
                </select>
            </div>

            <input id="button" type="submit" value="Modifica">
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
