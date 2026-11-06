import { criarLinhaInternacao } from "../componentes/linha-internacao.js";

const CAMINHO_JSON = '../assets/js/dados.json';

function buscarRelacionados(internacao, dados) {
    const paciente = dados.pacientes.find(p => p.id === internacao.pacienteId);
    const profissional = dados.profissionais.find(p => p.id === internacao.profissionalId);
    const quarto = dados.quartos.find(q => q.id === internacao.quarto);
    return { paciente, profissional, quarto };
}

async function main() {
    const resposta = await fetch(CAMINHO_JSON);
    const dados = await resposta.json();

    const painel = document.querySelector('[data-type="lista-internacoes"]');
    if (!painel) {
        console.warn('Painel de internações não encontrado');
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
            <th>Profissional</th>
            <th>Quarto</th>
            <th>Data de entrada</th>
            <th>Data efetiva de alta</th>
        </tr>
        </thead>
        <tbody></tbody>
    `;

    wrap.appendChild(tabela);
    painel.appendChild(wrap);

    const tbody = tabela.querySelector('tbody');
    dados.internacoes.forEach(i => {
        const { paciente, profissional, quarto } = buscarRelacionados(i, dados);
        tbody.appendChild(criarLinhaInternacao(i, paciente, profissional, quarto));
    });

    const contador = painel.querySelector('.app-record-count');
    if (contador) {
        contador.textContent = `${dados.internacoes.length} registro(s)`;
    }
}

main();