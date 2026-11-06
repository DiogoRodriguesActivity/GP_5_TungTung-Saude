import { criarLinhaQuarto } from "../componentes/linha-quarto.js";

const CAMINHO_JSON = '../assets/js/dados.json';

async function main() {
    const resposta = await fetch(CAMINHO_JSON);
    const dados = await resposta.json();

    const painel = document.querySelector('[data-type="lista-quartos"]');
    if (!painel) {
        console.warn('Painel de quartos não encontrado');
        return;
    }

    const empty = painel.querySelector('.app-empty');
    if (empty) empty.remove();

    const wrap = document.createElement('div');
    wrap.classList.add('app-table-wrap');

    const tabela = document.createElement('table');
    tabela.classList.add('app-table');
    tabela.innerHTML = `
        <thead>
        <tr>
            <th>Número</th>
            <th>Andar</th>
            <th>Capacidade</th>
            <th>Situação</th>
            <th class="app-table__actions">Ações</th>
        </tr>
        </thead>
        <tbody></tbody>
    `;

    wrap.appendChild(tabela);
    painel.appendChild(wrap);

    const tbody = tabela.querySelector('tbody');
    dados.quartos.forEach(q => {
        tbody.appendChild(criarLinhaQuarto(q));
    });

    const contador = painel.querySelector('.app-record-count');
    if (contador) {
        contador.textContent = `${dados.quartos.length} registro(s)`;
    }
}

main();