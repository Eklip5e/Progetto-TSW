document.addEventListener('DOMContentLoaded', () => {

    const inputExpirationDate = document.getElementById("dataScadenza");

    inputExpirationDate.addEventListener('input', () => {
        let val = inputExpirationDate.value.replace(/\D/g, '').slice(0, 4); // max 8 cifre

        // spezza in gruppi di 2,2,4 (giorno, mese, anno)
        const parts = val.match(/^(\d{0,2})(\d{0,2})$/);

        if (!parts) return;

        inputExpirationDate.value = parts[1] + (parts[2] ? '/' + parts[2] : '');
    });
});