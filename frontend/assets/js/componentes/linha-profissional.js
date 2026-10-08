export function criarLinhaProfissional(p) {
  const tr = document.createElement('tr');

  // Coluna 1: Nome com avatar
  const tdNome = document.createElement('td');
  tdNome.classList.add('app-table__primary');

  const wrap = document.createElement('div');
  wrap.style.display = 'flex';
  wrap.style.alignItems = 'center';
  wrap.style.gap = '0.6rem';

  if (p.imagem) {
    const avatar = document.createElement('img');
    avatar.src = p.imagem;
    avatar.alt = p.nome;
    avatar.style.width = '32px';
    avatar.style.height = '32px';
    avatar.style.borderRadius = '50%';
    avatar.style.objectFit = 'cover';
    wrap.appendChild(avatar);
  }

  const nome = document.createElement('span');
  nome.textContent = p.nome;
  wrap.appendChild(nome);

  tdNome.appendChild(wrap);

  // Coluna 2: Especialidade
  const tdEspecialidade = document.createElement('td');
  tdEspecialidade.textContent = p.especialidade ?? '-';

  // Coluna 3: CRM
  const tdCrm = document.createElement('td');
  tdCrm.textContent = p.crm ?? '-';

  // Coluna 4: Ações
  const tdAcoes = document.createElement('td');
  tdAcoes.classList.add('app-table__actions');

  const btnEditar = document.createElement('button');
  btnEditar.textContent = 'Editar';

  const btnExcluir = document.createElement('button');
  btnExcluir.classList.add('app-table__delete');
  btnExcluir.textContent = 'Excluir';

  tdAcoes.append(btnEditar, btnExcluir);

  tr.append(tdNome, tdEspecialidade, tdCrm, tdAcoes);
  return tr;
}