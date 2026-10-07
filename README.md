# GP_5_TungTung-Saude

gendamento(): void --cria objeto consulta

Internação: id_quarto: string id_medico: string id_paciente: string acompanhante: string horário: date data_entrada: date data prevista: date data efetiva: date Observção_Medicas: string

Profissional: id_medico: string Nome: string especialidade: string Telefone: string E-mail: string

Paciente: CPF: string Nome: string endereço: string Data_de_Nasc: Date Telefone: string acompanhante: string

Quarto: Numero de identificação Andar: Capacidade_maxima_de_paciente: int Disponibilidade: Bool

Agendamento: nome_paciente: string numero_paciente: string numero_convenio: string

# TungTung Saúde

Sistema de informação hospitalar para organizar pacientes, profissionais da saúde, consultas, internações, quartos e histórico médico.

O projeto reúne as páginas do sistema e seus estilos compartilhados em `frontend/assets/css/style.css`.

## Telas

- Página inicial: `frontend/index.html`
- Pacientes: `frontend/pages/paciente.html`
- Profissionais: `frontend/pages/profissional.html`
- Consultas: `frontend/pages/consulta.html`
- Internações: `frontend/pages/internacao.html`
- Quartos: `frontend/pages/quarto.html`
- Histórico médico: `frontend/pages/historico.html`

## Funcionalidades do sistema

Cadastro de pacientes e profissionais; agendamento de consultas; controle de internações e quartos; e consulta do histórico de atendimentos.