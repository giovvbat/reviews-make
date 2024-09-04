const dom_nova_senha = document.getElementById('nova-senha');
const dom_atual_senha = document.getElementById('atual-senha');
const token = localStorage.getItem('token');

document.getElementById('redefine-form').addEventListener('submit', function(event) {
    event.preventDefault();
    redefinir();
});

function redefinir() {
    const redefine_password = {
        currentPassword: dom_atual_senha.value,
        newPassword: dom_nova_senha.value
    }
    fetch(`http://localhost:8080/users/password`, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
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