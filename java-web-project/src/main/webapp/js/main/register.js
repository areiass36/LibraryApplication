const inputName = document.querySelector('#name');
const inputEmail = document.querySelector('#email');
const inputPassword = document.querySelector('#password');
const inputPasswordConfirm = document.querySelector('#confirm-password');

const form = [inputName, inputEmail, inputPassword, inputPasswordConfirm];

function validateForm() {
    form.isValid = form.every(e => e.isValid);
    document.querySelector('#registerButton').disabled = !form.isValid;
}

inputName.addEventListener('keyup', () => {
    inputName.isValid = inputName.value != null && inputName.value != "";
    validateForm();
})

inputEmail.addEventListener('keyup', () => {
    inputEmail.isValid = inputEmail.value.includes('@fatec.sp.gov.br');
    validateForm();
})

inputPasswordConfirm.addEventListener('keyup', () => {
    inputPasswordConfirm.isValid = inputPasswordConfirm.value == inputPassword.value;
    inputPassword.isValid = inputPasswordConfirm.value == inputPassword.value;
    validateForm();
})

inputPassword.addEventListener('keyup', () => {
    inputPassword.isValid = inputPasswordConfirm.value == inputPassword.value;
    inputPasswordConfirm.isValid = inputPasswordConfirm.value == inputPassword.value;
    validateForm();
})

validateForm();
