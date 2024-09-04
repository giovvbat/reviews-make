const dom_nova_senha = document.getElementById('senha');
const dom_credencial = document.getElementById('credencial');

document.getElementById('redefine-form').addEventListener('submit', function(event) {
    event.preventDefault();
    redefinir();
});

function redefinir() {
    const redefine_password = {
        credential: dom_credencial.value,
        password: dom_nova_senha.value
    }
    fetch(`http://localhost:8080/users/redefine-password`, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(redefine_password)
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