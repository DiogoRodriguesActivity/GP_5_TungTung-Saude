export function criarFooter(){

const footer = document.createElement('footer');
footer.className='tts-footer';
const conteudo = document.createElement('div');
conteudo.className = 'tts-footer__conteudo';
const container_1 = document.createElement('div');
container_1.className='tts-footer__coluna';
const subtitulo_1 = document.createElement('h3')
subtitulo_1.className='tts-footer__subtitulo';
subtitulo_1.textContent='TungTung Saúde';
const texto_1 = document.createElement('p');
texto_1.className = 'tts-footer__texto';
texto_1.textContent = 'Sistema de gestão hospitalar';


const container_2 = document.createElement('div');
container_2.className = 'tts-footer__coluna';
const subtitulo_2 = document.createElement('h3');
subtitulo_2.className = 'tts-footer__subtitulo';
subtitulo_2.textContent = 'Contato';
const texto_2 = document.createElement('p');
texto_2.className='tts-footer__texto';
texto_2.textContent='contato@tungtungsaude.com';
const texto_3 = document.createElement('p');
texto_3.className='tts-footer__texto';
texto_3.textContent ='(31) 96767-6767';

const container_3 = document.createElement('div');
container_3.className='tts-footer__base';
const texto_4 = document.createElement('p');
texto_4.className = 'tts-footer__texto';
texto_4.textContent='© 2026 TungTung Saúde - PUC Minas - Grupo 5';




footer.appendChild(conteudo);
conteudo.appendChild(container_1);
container_1.appendChild(subtitulo_1);
container_1.appendChild(texto_1);
conteudo.appendChild(container_2);
container_2.appendChild(subtitulo_2);
container_2.appendChild(texto_2);
container_2.appendChild(texto_3);
footer.appendChild(container_3);
container_3.appendChild(texto_4)

return footer

}