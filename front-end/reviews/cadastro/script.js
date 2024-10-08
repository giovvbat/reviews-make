const token = localStorage.getItem('token');
const dom_comentario = document.getElementById('comentario');
const params = new URLSearchParams(window.location.search);
const productId = params.get('productId');

function go_back() {
    const go_back_link = document.getElementById("go-back-link")
    go_back_link.setAttribute("href", `../listagem/reviews.html?productId=${productId}`)
}

if (productId) {
    go_back();
    document.getElementById('review-form').addEventListener('submit', function(event) {
        event.preventDefault();
        avaliar();
    });
} else {
    window.location.href = '../../listagem/home.html';
}

function avaliar() {
    const review = {
        productId: productId,
        comment: dom_comentario.value
    }
    fetch(`http://localhost:8080/reviews`, { 
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        },
        body: JSON.stringify(review)
    })
    .then(response => {
        if (response.ok) {
            return response.json(); 
        } else {
            throw new Error('Erro ao cadastrar review!'); 
        }
    })
    .then(data => {
        console.log(data);
        alert('Review cadastrada com sucesso!'); 
        window.location.href = `../listagem/reviews.html?productId=${productId}`;
    })
    .catch(error => {
        alert('Erro: ' + error.message);
        console.error('Erro:', error); 
    });
}