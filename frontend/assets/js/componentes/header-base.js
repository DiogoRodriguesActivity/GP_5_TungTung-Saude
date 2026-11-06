export function criarHeader(){    
    const header = document.createElement('header');
    header.classList.add('app-topbar');
    const linkConteudo = document.createElement('a')
    linkConteudo.href='#app-content'
    linkConteudo.textContent='Pular para o conteúdo'
    linkConteudo.classList.add('app-skip-link')
    const linkMarca = document.createElement('a')
    linkMarca.classList.add('app-brand')
    linkMarca.href='../index.html'
    const marcaSpan1=document.createElement('span')
    marcaSpan1.classList.add('app-brand__mark')
    marcaSpan1.setAttribute('aria-hidden','true');

    const SVG_NS = 'http://www.w3.org/2000/svg'
    const marcaSvg = document.createElementNS(SVG_NS,'svg')
    marcaSvg.setAttribute('viewBox', '0 0 48 48')
    marcaSvg.setAttribute('focusable', 'false')

    const marcaPath = document.createElementNS(SVG_NS,'path')
    marcaPath.setAttribute('d','M19 7h10v12h12v10H29v12H19V29H7V19h12z')
    
    marcaSvg.appendChild(marcaPath)
    marcaSpan1.appendChild(marcaSvg)

    const marcaSpan2 = document.createElement('span')
    const forte = document.createElement('strong')
    forte.textContent='TungTung Saúde'
    const pequeno = document.createElement('small')
    pequeno.textContent='Gestão Hospitalar'

    marcaSpan2.append(forte,pequeno)

    linkMarca.append(marcaSpan1, marcaSpan2)

    const nav = document.createElement('nav')
    nav.classList.add('app-nav')
    nav.setAttribute('aria-label','Navegação principal')

    const navLinkPaciente =  document.createElement('a')
    navLinkPaciente.href='paciente.html'
    navLinkPaciente.textContent='Pacientes'

    const navLinkProfissional=document.createElement('a')
    navLinkProfissional.href = 'profissional.html'
    navLinkProfissional.textContent='Profissionais'

    const navLinkConsulta = document.createElement('a')
    navLinkConsulta.href='consulta.html'
    navLinkConsulta.textContent='Consultas'
    navLinkConsulta.setAttribute('aria-current','page')

    const navLinkInternacao = document.createElement('a')
    navLinkInternacao.href='internacao.html'
    navLinkInternacao.textContent='Internações'

    const navLinkQuarto = document.createElement('a')
    navLinkQuarto.href='quarto.html'
    navLinkQuarto.textContent='Quartos'
    
    const navLinkHistorico = document.createElement('a')
    navLinkHistorico.href='historico.html'
    navLinkHistorico.textContent='Historico'

    nav.append(navLinkPaciente,
        navLinkProfissional,
        navLinkConsulta,
        navLinkInternacao,
        navLinkQuarto,
        navLinkHistorico
    )

    const home = document.createElement('a')
    home.classList.add('app-home-link')
    home.href='../index.html'
    home.textContent='Visão geral'
    const homeSpan = document.createElement('span')
    homeSpan.setAttribute('aria-hidden','true')
    homeSpan.textContent="↗"
    home.appendChild(homeSpan)

    header.append(linkConteudo,linkMarca,nav,home)

    return header

}