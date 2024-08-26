const dom_nome = document.getElementById('nome');
const dom_senha = document.getElementById('senha');
const dom_email = document.getElementById('email');

document.getElementById('user-form').addEventListener('submit', function(event) {
    event.preventDefault();
    cadastrar();
    limpar();
});

function cadastrar() {
    const usuario = {
        username: dom_nome.value,
        email: dom_email.value,
        password: dom_senha.value
    }
    fetch('http://localhost:8080/users/register', { 
        method: 'POST',
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
            throw new Error('Erro ao cadastrar usuário!'); 
        }
    })
    .then(data => {
        alert('Usuário cadastrado com sucesso!'); 
        console.log(data);
        window.location.href = '../entrar/login.html';
    })
    .catch(error => {
        alert('Erro: ' + error.message);
        console.error('Erro:', error); 
    });
}

function limpar() {
    dom_nome.value = "",
    dom_email.value = "",
    dom_senha.value = ""
}