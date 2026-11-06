const CAMINHO_JSON = '../assets/js/dados.json';

function pesquisarConsulta(consultaId, dados) {
    const consulta = dados.consultas.find(c => c.id === consultaId);
    if (!consulta) return null;

    const paciente = dados.pacientes.find(p => p.id === consulta.pacienteId);
    const profissional = dados.profissionais.find(p => p.id === consulta.profissionalId);

    if (!paciente || !profissional) {
        console.warn(`Consulta ${consultaId} tem paciente/profissional inválido`);
        return null;
    }

    return { consulta, paciente, profissional };
}

function criarLinhaConsulta(consulta, paciente, profissional) {
    const tr = document.createElement('tr');

    const tdPaciente = document.createElement('td');
    tdPaciente.classList.add('app-table__primary');
    tdPaciente.textContent = paciente.nome;

    const tdProfissional = document.createElement('td');
    tdProfissional.textContent = profissional.nome;

    const tdData = document.createElement('td');
    tdData.textContent = consulta.data ?? '-';

    const tdMotivo = document.createElement('td');
    tdMotivo.textContent = consulta.motivo ?? '-';

    const tdStatus = document.createElement('td');
    const status = document.createElement('span');
    status.classList.add('app-status');
    status.textContent = 'Agendada';
    tdStatus.appendChild(status);

    const tdAcoes = document.createElement('td');
    tdAcoes.classList.add('app-table__actions');

    const btnVer = document.createElement('button');
    btnVer.textContent = 'Ver';

    const btnExcluir = document.createElement('button');
    btnExcluir.classList.add('app-table__delete');
    btnExcluir.textContent = 'Excluir';

    tdAcoes.append(btnVer, btnExcluir);

    tr.append(tdPaciente, tdProfissional, tdData, tdMotivo, tdStatus, tdAcoes);
    return tr;
}

async function main() {
    const response = await fetch(CAMINHO_JSON);
    const dados = await response.json();

    const painel = document.querySelector('[data-type="lista-consultas"]');
    if (!painel) {
        console.warn('Painel de consultas não encontrado');
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
            <th>Data</th>
            <th>Motivo</th>
            <th>Situação</th>
            <th class="app-table__actions">Ações</th>
        </tr>
        </thead>
        <tbody></tbody>
    `;

    wrap.appendChild(tabela);
    painel.appendChild(wrap);

    const tbody = tabela.querySelector('tbody');
    let contador = 0;

    dados.consultas.forEach(c => {
        const resultado = pesquisarConsulta(c.id, dados);
        if (!resultado) return;

        const { consulta, paciente, profissional } = resultado;
        tbody.appendChild(criarLinhaConsulta(consulta, paciente, profissional));
        contador++;
    });

    const elContador = painel.querySelector('.app-record-count');
    if (elContador) {
        elContador.textContent = `${contador} registro(s)`;
    }
}

main();