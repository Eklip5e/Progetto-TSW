document.addEventListener('DOMContentLoaded', () => {


    const inputDateBirth = document.getElementById("dataNascita");

    inputDateBirth.addEventListener('input', () => {
        let val = inputDateBirth.value.replace(/\D/g, '').slice(0, 8); // max 8 cifre

        // spezza in gruppi di 2,2,4 (giorno, mese, anno)
        const parts = val.match(/^(\d{0,2})(\d{0,2})(\d{0,4})$/);

        if (!parts) return;

        inputDateBirth.value = parts[1] + (parts[2] ? '/' + parts[2] : '') + (parts[3] ? '/' + parts[3] : '');
    });
});