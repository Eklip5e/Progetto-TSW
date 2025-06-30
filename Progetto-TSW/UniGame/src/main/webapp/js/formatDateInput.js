document.addEventListener('DOMContentLoaded', function () {
    const dataInput = document.querySelector('input[name="dataDiNascita"]');

    dataInput.addEventListener('input', function () {
        let value = dataInput.value.replace(/\D/g, '');

        if (value.length > 4) {
            value = value.slice(0, 2) + '/' + value.slice(2, 4) + '/' + value.slice(4, 8);
        } else if (value.length > 2) {
            value = value.slice(0, 2) + '/' + value.slice(2);
        }

        dataInput.value = value;
    });
});