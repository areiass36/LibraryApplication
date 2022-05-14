function seed() {
    let seed = '';
    for (let i = 0; i < 100; i++)
        seed += Math.floor((Math.random() * 8) + 1);
    return seed;
}

const url = `https://avatars.dicebear.com/api/avataaars/${seed()}.svg`

document.querySelector("#avatar").setAttribute('src', url);
