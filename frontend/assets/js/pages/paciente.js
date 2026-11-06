import { criarCard } from "../componentes/card-base.js";

const dadosCaminho = '../assets/js/dados.json'
async function main(dadosCaminho) {
    
    const response = await fetch(dadosCaminho);
    const dados =  await response.json();
    const pacientes = dados.pacientes;
    
    const container = document.querySelector('[data-type="card"]')

    if (!container) {
    console.warn('Container [data-type="card"] não encontrado');
    return;
    }
    pacientes.forEach(element => {
        const card = criarCard(
        element.nome,
        element.imagem,
        element.info,
        "",
        );
    container.appendChild(card);
});
container.classList.add('tts-card__container')
}

main(dadosCaminho);