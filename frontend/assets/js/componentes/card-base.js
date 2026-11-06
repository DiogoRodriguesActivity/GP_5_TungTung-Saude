export function criarCard(nome,imagemLink,info,botaoLink){

    const container = document.createElement('div');
    container.className = 'tts-card';
    const titulo = document.createElement('h1');
    titulo.className = 'tts-card__nome';
    titulo.textContent = nome;
    const imagem = document.createElement('img');
    imagem.src = imagemLink;
    imagem.className = 'tts-card__imagem';
    const informacao = document.createElement('p');
    informacao.textContent=info;
    informacao.className='tts-card__info';
    const botao = document.createElement('a');
    botao.className = 'tts-card__botao tts-button--basic';
    botao.href = botaoLink;
    botao.textContent='Saiba Mais'


    container.appendChild(imagem);
    container.appendChild(titulo);
    container.appendChild(informacao);
    container.appendChild(botao);

    return container
}