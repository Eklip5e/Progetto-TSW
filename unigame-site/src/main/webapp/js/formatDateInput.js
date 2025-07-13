document.addEventListener('DOMContentLoaded', function () {
    const dataInput = document.querySelector('input[name="dataDiNascita"]');

    dataInput.addEventListener('input', function (e) {
        let value = dataInput.value.replace(/\D/g, ''); // Rimuove tutto tranne numeri

        if (value.length > 2 && value.length <= 4)
            value = value.slice(0, 2) + '/' + value.slice(2);
        else if (value.length > 4)
            value = value.slice(0, 2) + '/' + value.slice(2, 4) + '/' + value.slice(4, 8);

        dataInput.value = value;
    });
});