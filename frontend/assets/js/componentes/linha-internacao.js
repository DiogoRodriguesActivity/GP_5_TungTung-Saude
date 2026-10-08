export function criarLinhaInternacao(internacao, paciente, profissional, quarto) {
    const tr = document.createElement('tr');

    const tdPaciente = document.createElement('td');
    tdPaciente.classList.add('app-table__primary');
    tdPaciente.textContent = paciente?.nome ?? '-';

    const tdProfissional = document.createElement('td');
    tdProfissional.textContent = profissional?.nome ?? '-';

    const tdQuarto = document.createElement('td');
    tdQuarto.textContent = quarto?.numeroIdentificacao ?? '-';

    const tdEntrada = document.createElement('td');
    tdEntrada.textContent = internacao.dataEntrada ?? '-';

    const tdAlta = document.createElement('td');
    tdAlta.textContent = internacao.dataEfetivaAlta ?? '—';

    tr.append(tdPaciente, tdProfissional, tdQuarto, tdEntrada, tdAlta);
    return tr;
}