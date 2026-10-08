export function criarFooter(){

    const footer = document.createElement('footer')
    footer.classList.add('app-footer')
    const link = document.createElement('a')
    link.textContent='TungTung Saúde'
    link.setAttribute('href','../index.html')
    
    footer.appendChild(link)

    return footer

}