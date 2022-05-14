const userEmail = document.querySelector("#user-email").innerHTML.replace("@fatec.sp.gov.br", "");

const url = `https://avatars.dicebear.com/api/avataaars/${userEmail}.svg`

document.querySelector("#avatar").setAttribute('src', url);