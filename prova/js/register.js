function togglePasswordHint(visible) {
    const passwordHint = document.getElementById('passwordHint');
    
    if(visible) {
        passwordHint.style.display = 'block';
    } else {
        passwordHint.style.display = 'none';
    }
}