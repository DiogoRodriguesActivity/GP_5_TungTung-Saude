function pesquisarConsulta(consultaId,dados){
const consulta = dados.consultas.find(element => element.id === consultaId);
    if(!consulta){
        return null;
    }
    const paciente = dados.pacientes.find(element => element.id === consulta.pacienteId);
    const profissional = dados.profissionais.find(element => element.id === consulta.profissionalId);

    if (!paciente || !profissional) {
    console.warn(`Consulta ${consultaId} tem paciente/profissional inválido`);
    return null;
    }
    return{consulta,paciente,profissional}
}
function renderizarConsulta(consulta,paciente,profissional){
    const container = document.createElement('div');
    container.classList.add('consulta');
    const nomePaciente=document.createElement('p');
    nomePaciente.textContent=`Nome Paciente: "${paciente.nome}"`
    nomePaciente.classList.add('consulta__texto');
    const nomeProfissional=document.createElement('p');
    nomeProfissional.textContent=`Nome Medico: "${profissional.nome}"`;
    nomeProfissional.classList.add('consulta__texto');
    const data=document.createElement('p');
    data.textContent=`Data: "${consulta.data}"`;
    data.classList.add('consulta__texto');
    const motivo=document.createElement('p');
    motivo.textContent=`Motivo: "${consulta.motivo}"`;
    motivo.classList.add('consulta__texto');
    const observacaoMedica=document.createElement('p');
    observacaoMedica.textContent=`Observação Médica: "${consulta.observacaoMedica}"`;
    observacaoMedica.classList.add('consulta__texto');
    const button=document.createElement('a');
    button.classList.add('tts-button--basic')
    button.textContent="Saiba Mais"
    button.href="#";
    
    container.append(nomePaciente,
                            nomeProfissional,
                            data,
                            motivo,
                            observacaoMedica,
                            button
                        );
    return container;
}


async function main(dadosCaminho) {
    const response= await fetch(dadosCaminho);
    const dados = await response.json();
    const consulta = dados.consultas;

    const container = document.querySelector('[data-type="consulta"]');
    if(!container){
        console.warn('Container [data-type="consulta"] não encontrado')
        return;
    }
    consulta.forEach(element => {
        const resultado =pesquisarConsulta(element.id,dados)
        if (!resultado) return;
        const {consulta,paciente,profissional} = resultado;
        const card = renderizarConsulta(consulta,paciente,profissional);
        container.appendChild(card)
        container.classList.add('consulta__container')
    });
}

const dadosCaminho = '../assets/js/dados.json'
main(dadosCaminho)