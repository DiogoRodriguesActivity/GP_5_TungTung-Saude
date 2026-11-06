import { criarLinhaPaciente } from "../componentes/linha-paciente.js";

const CAMINHO_JSON = '../assets/js/dados.json';

async function main() {
    const resposta = await fetch(CAMINHO_JSON);
    const dados = await resposta.json();

    const painel = document.querySelector('[data-type="lista-pacientes"]');
    if (!painel) {
        console.warn('Painel de pacientes não encontrado');
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
            <th>Paciente</th>
            <th>CPF</th>
            <th>Idade</th>
            <th class="app-table__actions">Ações</th>
        </tr>
        </thead>
        <tbody></tbody>
    `;

    wrap.appendChild(tabela);
    painel.appendChild(wrap);

    const tbody = tabela.querySelector('tbody');
    dados.pacientes.forEach(p => {
        tbody.appendChild(criarLinhaPaciente(p));
    });

    const contador = painel.querySelector('.app-record-count');
    if (contador) {
        contador.textContent = `${dados.pacientes.length} registro(s)`;
    }
}

main();