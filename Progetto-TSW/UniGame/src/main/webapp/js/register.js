function validatePassword() {
    const value = document.getElementById("password").value;

    const lowerCase = /[a-z]/;
    const upperCase = /[A-Z]/;
    const number = /[0-9]/;
    const specialChar = /[:!?@()*&$"\/\\]/;

    updateRequirement("letter", lowerCase.test(value));
    updateRequirement("capital", upperCase.test(value));
    updateRequirement("number", number.test(value));
    updateRequirement("special", specialChar.test(value));
    updateRequirement("length", value.length >= 8);
}

function updateRequirement(id, isValid) {
    const el = document.getElementById(id);
    el.className = isValid ? "valid" : "invalid";
    el.innerHTML = (isValid ? "✅" : "❌") + el.innerHTML.slice(1);
}

function togglePassword(id, button) {
    const input = document.getElementById(id);
    const openEye = button.querySelector('.eye.open');
    const closedEye = button.querySelector('.eye.closed');

    if (input.type === "password") {
        input.type = "text";
        openEye.classList.add("hidden");
        closedEye.classList.remove("hidden");
    } else {
        input.type = "password";
        closedEye.classList.add("hidden");
        openEye.classList.remove("hidden");
    }
}

function checkPasswordMatch() {
    const pass = document.getElementById("password").value;
    const confirm = document.getElementById("confermaPassword").value;
    const msg = document.getElementById("match-message");

    if (confirm === "") {
        msg.textContent = "";
        return;
    }

    if (pass === confirm) {
        msg.textContent = "✅ Le password coincidono";
        msg.style.color = "green";
    } else {
        msg.textContent = "❌ Le password non coincidono";
        msg.style.color = "red";
    }
}
