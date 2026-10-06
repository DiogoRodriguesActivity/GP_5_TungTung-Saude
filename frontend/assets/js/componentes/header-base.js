export function criarHeader(){    

const linkNomes  =["Consulta","Historico","Internacao","Paciente","Profissional","Quarto"]; 
const href = ['','','','','',''];
const header = document.createElement('header');
header.className = 'tts-header'
const logotipo = document.createElement('div');
logotipo.className = 'tts-header__logotipo'
const titulo = document.createElement('h1');
titulo.className = 'tts-header__titulo'
titulo.textContent="TungTung Saúde"
const imagem = document.createElement('img')
imagem.src="https://cdn2.cdnstep.com/vmfBdKCwmq2YemPftKZB/cover-1.thumb256.png";
imagem.className = 'tts-header__imagem'
imagem.alt='';
const nav = document.createElement('nav');3
nav.className = 'tts-nav';


function criarNavLinks(){

    if( linkNomes.length !== href.length){
        console.error(`LinkNomes sem par em href, abortando`)
        return
    }

    for (let i=0;i<linkNomes.length;i++){
        if (href[i]=== ""){
            console.warn(`O link "${linkNomes[i]}" Está sem direcionamento`)
    }
    const navLink = document.createElement('a');
    navLink.className = 'tts-nav__link';
    navLink.textContent = linkNomes[i];
    navLink.href = href[i];
    nav.appendChild(navLink);
};
};
header.appendChild(logotipo);
logotipo.appendChild(titulo);
logotipo.appendChild(imagem)
header.appendChild(nav)
criarNavLinks();

return header
}