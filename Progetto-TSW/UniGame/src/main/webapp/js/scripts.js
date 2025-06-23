/*<script>
    document.getElementById('data_nascita').addEventListener('input', function (e) {
    let input = e.target.value.replace(/[^0-9]/g, ''); // Rimuove tutto tranne numeri
    if (input.length >= 3 && input.length <= 4) {
    input = input.slice(0,2) + '/' + input.slice(2);
} else if (input.length >= 5) {
    input = input.slice(0,2) + '/' + input.slice(2,4) + '/' + input.slice(4,8);
}
    e.target.value = input;

    // Validazione
    if (input.length === 10) {
    const [day, month, year] = input.split('/');
    const date = new Date(`${year}-${month}-${day}`);
    if (date.getFullYear() != year || (date.getMonth()+1) != month || date.getDate() != day) {
    // data non valida
    e.target.style.border = '2px solid red';
} else {
    // data valida
    e.target.style.border = '2px solid green';
}
} else {
    // incompleto → neutro
    e.target.style.border = '';
}
});
</script>*/
