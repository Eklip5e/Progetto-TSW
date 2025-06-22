<script>
    fetch('img/unigameLogo.svg')
    .then(response => response.text())
    .then(svgText => {
    // Crea un elemento temporaneo per parsare l'SVG
    const parser = new DOMParser();
    const svgElement = parser.parseFromString(svgText, "image/svg+xml").querySelector("svg");

    // Rimuovi eventuali attributi di dimensione non necessari
    svgElement.removeAttribute("width");
    svgElement.removeAttribute("height");

    // Imposta una classe per facilitare lo styling
    svgElement.classList.add("logo-svg");

    // Cerca tutti i path, circle, polygon, ecc. e imposta fill a currentColor
    const shapes = svgElement.querySelectorAll("path, circle, rect, polygon");
    shapes.forEach(shape => {
    shape.setAttribute("fill", "currentColor");
});

    // Inserisci l'SVG nel container
    const container = document.getElementById("logo-container");
    container.appendChild(svgElement);
})
    .catch(error => {
    console.error('Errore nel caricamento dell\'SVG:', error);
});
</script>