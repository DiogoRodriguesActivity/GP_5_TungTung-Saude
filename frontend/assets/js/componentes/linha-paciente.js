export function criarLinhaPaciente(p) {
    const tr = document.createElement('tr');

    const tdNome = document.createElement('td');
    tdNome.classList.add('app-table__primary');

    const wrap = document.createElement('div');
    wrap.style.display = 'flex';
    wrap.style.alignItems = 'center';
    wrap.style.gap = '0.6rem';

    const img = document.createElement('img');
    img.src = p.imagem ?? '';
    img.alt = p.nome;
    img.style.width = '36px';
    img.style.height = '36px';
    img.style.borderRadius = '50%';
    img.style.objectFit = 'cover';

    const nome = document.createElement('span');
    nome.textContent = p.nome;

    wrap.append(img, nome);
    tdNome.appendChild(wrap);

    const tdCpf = document.createElement('td');
    tdCpf.textContent = p.cpf ?? '-';

    const tdIdade = document.createElement('td');
    tdIdade.textContent = calcularIdade(p.dataNascimento);

    const tdAcoes = document.createElement('td');
    tdAcoes.classList.add('app-table__actions');

    const btnEditar = document.createElement('button');
    btnEditar.textContent = 'Editar';

    const btnExcluir = document.createElement('button');
    btnExcluir.classList.add('app-table__delete');
    btnExcluir.textContent = 'Excluir';

    tdAcoes.append(btnEditar, btnExcluir);

    tr.append(tdNome, tdCpf, tdIdade, tdAcoes);
    return tr;
}

function calcularIdade(dataNascimento) {
    if (!dataNascimento) return '-';

    const partes = dataNascimento.split('/');
    if (partes.length !== 3) return '-';

    const dia = parseInt(partes[0], 10);
    const mes = parseInt(partes[1], 10) - 1;
    const ano = parseInt(partes[2], 10);

    const nascimento = new Date(ano, mes, dia);
    const hoje = new Date();

    let idade = hoje.getFullYear() - nascimento.getFullYear();
    const mesAtual = hoje.getMonth() - nascimento.getMonth();

    if (mesAtual < 0 || (mesAtual === 0 && hoje.getDate() < nascimento.getDate())) {
        idade--;
    }

    return `${idade} anos`;
}