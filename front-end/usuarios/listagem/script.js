const token = localStorage.getItem('token');

document.addEventListener('DOMContentLoaded', function() {
    if (!token) {
        window.location.href = '../../usuarios/entrar/login.html';
        return;
    }
    fetch(`http://localhost:8080/users`, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        }
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Erro ao buscar usuários');
        }
    })
    .then(data => {
        const table = document.getElementById('users-table-body');
        data.forEach(user => {
            const row = document.createElement('tr');
            row.setAttribute('data-user-id', user.userId);
            row.innerHTML = `
                <td>${user.email}</td>
                <td><button class="btn btn-primary btn-extra-small" onclick="deleteUser('${user.userId}')">Excluir</button></td>
            `;
            table.appendChild(row);
        });
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao carregar os usuários');
    });
});

function deleteUser(userId) {
    fetch(`http://localhost:8080/users/${userId}`, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao deletar usuário');
            }

            const row = document.querySelector(`tr[data-user-id="${userId}"]`);
            if (row) {
                row.remove();
            }

            return response.json();
        })
        .catch(error => {
            console.error('Erro:', error);
        });
}