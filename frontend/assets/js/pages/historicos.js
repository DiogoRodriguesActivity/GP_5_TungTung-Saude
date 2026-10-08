import { criarLinhaHistorico } from "../componentes/linha-historico.js";

const CAMINHO_JSON = '../assets/js/dados.json';

function buscarRelacionados(historico, dados) {
    const paciente = dados.pacientes.find(p => p.id === historico.pacienteId);
    const profissional = dados.profissionais.find(p => p.id === historico.profissionalId);
    return { paciente, profissional };
}

async function main() {
    const resposta = await fetch(CAMINHO_JSON);
    const dados = await resposta.json();

    const painel = document.querySelector('[data-type="lista-historicos"]');
    if (!painel) {
        console.warn('Painel de históricos não encontrado');
        return;
    }

    const tabelaVazia = painel.querySelector('.app-table-wrap');
    if (tabelaVazia) tabelaVazia.remove();

    const wrap = document.createElement('div');
    wrap.classList.add('app-table-wrap');

    const tabela = document.createElement('table');
    tabela.classList.add('app-table');
    tabela.innerHTML = `
        <thead>
        <tr>
            <th>Data</th>
            <th>Paciente</th>
            <th>Profissional</th>
            <th>Resumo</th>
        </tr>
        </thead>
        <tbody></tbody>
    `;

    wrap.appendChild(tabela);
    painel.appendChild(wrap);

    const tbody = tabela.querySelector('tbody');
    dados.historicos.forEach(h => {
        const { paciente, profissional } = buscarRelacionados(h, dados);
        tbody.appendChild(criarLinhaHistorico(h, paciente, profissional));
    });

    const contador = painel.querySelector('.app-record-count');
    if (contador) {
        contador.textContent = `${dados.historicos.length} registro(s)`;
    }
}

main();