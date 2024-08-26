const dom_nome = document.getElementById('nome');
const dom_senha = document.getElementById('senha');

document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault();
    verificar();
});

function verificar() {
    const usuario = {
        username: dom_nome.value,
        password: dom_senha.value
    }
    fetch('http://localhost:8080/users/login', { 
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
            throw new Error('Credenciais inválidas!'); 
        }
    })
    .then(data => {
        if(data.token) {
            localStorage.setItem('token', data.token);
            console.log('Login realizado com sucesso!');
            alert(''); 
            window.location.href = '../../listagem/home.html';
        }
        else {
            console.error('Falha ao realizar o login!');
        }
    })
    .catch(error => {
        alert('Erro: ' + error.message);
        console.error('Erro:', error); 
    });
}