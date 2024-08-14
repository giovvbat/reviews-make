document.addEventListener('DOMContentLoaded', function() {
    fetch('http://localhost:8080/products')
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
                `;
                table.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao carregar os produtos');
        });
});