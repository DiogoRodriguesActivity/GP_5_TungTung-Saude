export function criarLinhaHistorico(historico, paciente, profissional) {
    const tr = document.createElement('tr');

    const tdData = document.createElement('td');
    tdData.textContent = historico.data ?? '-';

    const tdPaciente = document.createElement('td');
    tdPaciente.classList.add('app-table__primary');
    tdPaciente.textContent = paciente?.nome ?? '-';

    const tdProfissional = document.createElement('td');
    tdProfissional.textContent = profissional?.nome ?? '-';

    const tdResumo = document.createElement('td');
    tdResumo.textContent = historico.resumo ?? '-';
    tdResumo.style.maxWidth = '320px';
    tdResumo.style.overflow = 'hidden';
    tdResumo.style.textOverflow = 'ellipsis';
    tdResumo.style.whiteSpace = 'nowrap';
    tdResumo.title = historico.resumo ?? '';

    tr.append(tdData, tdPaciente, tdProfissional, tdResumo);
    return tr;
}