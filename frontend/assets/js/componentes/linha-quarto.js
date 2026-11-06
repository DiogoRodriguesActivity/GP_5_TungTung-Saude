export function criarLinhaQuarto(q) {
    const tr = document.createElement('tr');

    const tdNumero = document.createElement('td');
    tdNumero.classList.add('app-table__primary');
    tdNumero.textContent = q.numeroIdentificacao ?? '-';

    const tdAndar = document.createElement('td');
    tdAndar.textContent = q.andar ?? '-';

    const tdCapacidade = document.createElement('td');
    tdCapacidade.textContent = `${q.capacidadeMaxima ?? '-'} paciente(s)`;

    const tdSituacao = document.createElement('td');
    const badge = document.createElement('span');
    badge.classList.add('app-status');

    const situacao = (q.situacaoAtual ?? '').toLowerCase();
    if (situacao === 'disponível' || situacao === 'disponivel') {
    } else if (situacao === 'ocupado') {
        badge.classList.add('app-status--danger');
    }

    badge.textContent = q.situacaoAtual ?? '-';
    tdSituacao.appendChild(badge);

    const tdAcoes = document.createElement('td');
    tdAcoes.classList.add('app-table__actions');

    const btnEditar = document.createElement('button');
    btnEditar.textContent = 'Editar';

    const btnExcluir = document.createElement('button');
    btnExcluir.classList.add('app-table__delete');
    btnExcluir.textContent = 'Excluir';

    tdAcoes.append(btnEditar, btnExcluir);

    tr.append(tdNumero, tdAndar, tdCapacidade, tdSituacao, tdAcoes);
    return tr;
}