const dom_nome = document.getElementById('nome');
const dom_email = document.getElementById('email');
const dom_senha = document.getElementById('senha');

document.getElementById('redefine-form').addEventListener('submit', function(event) {
    event.preventDefault();
    redefinir();
});

function redefinir() {
    const parte_url = encodeURIComponent(dom_nome.value);

    const usuario = {
        username: dom_nome.value,
        password: dom_senha.value,
        email: dom_email.value
    }
    fetch(`http://localhost:8080/users/${parte_url}`, { 
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuario)
    })
    .then(response => {
        if (response.ok) {
            return response.json(); 
        } else {
            throw new Error('Erro ao redefinir senha!'); 
        }
    })
    .then(data => {
        console.log(data);
        alert('Redefinição concluída com sucesso!'); 
        window.location.href = '../entrar/login.html';
    })
    .catch(error => {
        alert('Erro: ' + error.message);
        console.error('Erro:', error); 
    });
}