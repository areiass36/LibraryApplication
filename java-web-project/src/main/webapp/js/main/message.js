function showMessageDialog() {
    const message = document.querySelector("#sys-message").getAttribute("value");
    if (!message) return;

    const container = document.querySelector("#message-container");

    window.addEventListener("load", () => {
        setTimeout(() => {
            container.MaterialSnackbar.showSnackbar({ message: message });
        }, 100)

    });
}

showMessageDialog();