const token = localStorage.getItem('token');

document.addEventListener('DOMContentLoaded', function() {
    if (!token) {
        window.location.href = '../../usuarios/entrar/login.html';
        return;
    }
    fetch('http://localhost:8080/products', {
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
            throw new Error('Erro ao buscar produtos');
        }
    })
    .then(data => {
        const table = document.getElementById('products-table-body');
        data.forEach(product => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${product.name}</td>
                <td>${product.brand}</td>
                <td>${product.value}</td>
                <td><button class="btn btn-primary btn-extra-small" onclick="reviews('${product.productId}')">Ver Detalhes</button></td>
            `;
            table.appendChild(row);
        });
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao carregar os produtos');
    });
});

function reviews(productId) {
    window.location.href = `../reviews/listagem/reviews.html?productId=${productId}`;
}