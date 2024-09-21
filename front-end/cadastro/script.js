const dom_nome = document.getElementById('nome');
const dom_marca = document.getElementById('marca');
const dom_valor = document.getElementById('valor');
const token = localStorage.getItem('token');

document.getElementById('product-form').addEventListener('submit', function(event) {
    event.preventDefault();
    cadastrar();
    limpar();
});

function cadastrar() {
    const produto = {
        name: dom_nome.value,
        value: dom_valor.value,
        brand: dom_marca.value
    }
    fetch('http://localhost:8080/products', { 
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        },
        body: JSON.stringify(produto)
    })
    .then(response => {
        if (response.ok) {
            return response.json(); 
        } else {
            throw new Error('Erro ao cadastrar produto!'); 
        }
    })
    .then(data => {
        alert('Produto cadastrado com sucesso!'); 
        console.log(data);
        window.location.href = `../listagem/home.html`;
    })
    .catch(error => {
        alert('Erro: ' + error.message);
        console.error('Erro:', error); 
    });
}

function limpar() {
    dom_nome.value = "",
    dom_marca.value = "",
    dom_valor.value = ""
}