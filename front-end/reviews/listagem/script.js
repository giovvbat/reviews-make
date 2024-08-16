document.addEventListener('DOMContentLoaded', function() {
const params = new URLSearchParams(window.location.search);
const productId = params.get('productId');

function add_review() {
    const add_review_link = document.getElementById("add-review-link")
    add_review_link.setAttribute("href", `../cadastro/add-review.html?productId=${productId}`)
}
    
if (productId) {
    add_review()
    fetch(`http://localhost:8080/products/${productId}/reviews`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Erro ao buscar reviews');
            }
        })
        .then(data => {
            const table = document.getElementById('reviews-table-body');
            data.forEach(review => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${review.reviewProduct.name}</td>
                    <td>${review.reviewUser.name}</td>
                    <td>${review.comment}</td>
                `;
                table.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao carregar as reviews');
        });
} else {
    window.location.href = '../../listagem/home.html';
}
});
