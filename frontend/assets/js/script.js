// Importa todos os js do sistema, menos o de cada página

import { criarHeader } from "./componentes/header-base.js";
import { criarFooter } from "./componentes/footer-base.js";
const componentes = {
    "header-base":criarHeader,
    "footer-base":criarFooter
    // Outros componentes, utilizar data-componente
}

function injetarComponentes(){
document.querySelectorAll('[data-componente]').forEach(elemento =>{
    const nome = elemento.dataset.componente;
    const fabrica = componentes[nome];

    if (!fabrica){
        console.warn(`Componente "${nome}"não registrado`);
        return;
    }
    elemento.appendChild(fabrica());
});
}
injetarComponentes();