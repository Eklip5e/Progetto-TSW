// Funzione per validare la password e aggiornare i requisiti
function checkPasswordStrength() {
    const passwordInput = document.getElementById('password');
    const password = passwordInput.value;

    // Requisiti della password
    const minLength = 8;
    const maxLength = 25;
    const hasUppercase = /[A-Z]/.test(password);
    const hasSymbol = /[:@?!+=\-_.,]/.test(password);

    // Controllo dei requisiti
    const isLengthValid = password.length >= minLength && password.length <= maxLength;
    const isValid = isLengthValid && hasUppercase && hasSymbol;

    // Aggiorna lo stile del campo password
    if (isValid) {
        passwordInput.classList.remove('invalid');
        passwordInput.classList.add('valid');
    } else {
        passwordInput.classList.remove('valid');
        passwordInput.classList.add('invalid');
    }

    // Aggiorna i requisiti nell'elenco puntato
    updateRequirement("length-requirement", isLengthValid);
    updateRequirement("uppercase-requirement", hasUppercase);
    updateRequirement("symbol-requirement", hasSymbol);

    return isValid;
}

// Funzione per aggiornare un singolo requisito
function updateRequirement(requirementId, isSatisfied) {
    const requirementElement = document.getElementById(requirementId);
    if (isSatisfied) {
        requirementElement.innerHTML = "✅ " + requirementElement.textContent.replace(/❌|✅/, "");
    } else {
        requirementElement.innerHTML = "❌ " + requirementElement.textContent.replace(/❌|✅/, "");
    }
}

// Funzione di validazione prima dell'invio del form
function validatePassword() {
    const isValid = checkPasswordStrength();
    if (!isValid) {
        alert("La password non soddisfa i requisiti minimi. Controlla il prompt a sinistra.");
    }
    return isValid;
}