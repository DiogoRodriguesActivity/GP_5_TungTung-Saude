Após a análise do modelo inicial, o hospital identificou que os profissionais da saúde
possuem diferentes atribuicões e que os atendimentos precisam respeitar regras especıficas
de acordo com sua natureza.

__"Profissionais de cargos diferentes, tem poderes diferentes dentro do sistema";
ex: medico pode abrir chamado
    enfermeiro não pode abrir+ VerificarSituacaoPrioridade(): Prioridade

Além disso, verificou-se que o sistema precisa representar adequadamente as diferentes
situações pelas quais uma consulta ou internação pode passar, evitando o registro de
informações inconsistentes.
__"Não será tolerada o pulo de etapas"
ex: Uma pessoa precisa ser consultada para ser internada



=========================================================================
Novas Sub-Classes (Diferentes categorias de profissionais da saúde)

Medico clínico geral

Realiza consultas de rotina e avaliações iniciais dos pacientes.
Pode encaminhar pacientes para atendimento especializado.
Deve registrar as informações referentes `as consultas realizadas.

Atributos e métodos:

Atributos:


consultas_Marcadas<>: Arraylist<DateTime>

Método:
Consultar(): void
"Realizar_Consulta(): void"
Avaliar_Paciente(): void 
Encaminhar_Paciente(): void <--- Pergunta: Criar uma nova Consulta para o próximo profissional
Registrar(): void

-----------------------------------------

Medico especialista

• Atua em uma área específica da medicina, como cardiologia, neurologia ou ortopedia.
• Realiza atendimentos relacionados à sua especialidade. <-- Pergunta: Como ele atuaria apenas na sua especialidade ?
• O sistema deve registrar a especialidade do profissional e permitir a consulta dos
profissionais disponíveis por área de atuação

Atributos e métodos:

Atributos:

- Especialidade(super)default: Especialidade<<Enum>>

Metodo:
Realizar_Consulta(Concreto): void <--- Estou repetindo, pois o paciente escolhe qual profissional vai atender-lo

--------------------------------------------

Cirurgião
• Realiza procedimentos cirurgicos.
• Alem das informacoes comuns aos medicos, deve possuir informacoes relacionadas a
sua atuacao cirurgica.
• O sistema deve permitir o registro dos procedimentos realizados pelo profissional,
incluindo data, paciente e descricao do procedimento.

Atributos e métodos:

Atributos:

- Especialidade(super): Especialidade<<Enum>>
- Situação_Cirugia: String <-- Pode ser um Enum do procedimento

Metodo:
Realizar_Consulta(): void <--- Estou repetindo, pois o paciente escolhe qual profissional vai atender-lo (Realiza procedimentos cirurgicos.)
Relatorio_Cirurgião(): void
VerificarSituação(): void

===========================================================================================
Diferentes modalidades de atendimento

O hospital oferece diferentes modalidades de atendimento. Cada modalidade possui ca-
racterısticas e regras proprias.

Atendimento (Nova Super Classe)
-----------------------------------------------------------------------
-- Consulta de rotina "Para o Clinico Geral" Agregação, medico-paciente:
 data,
 horario,
 paciente,
 medico responsável ((Encaminhar_Paciente() de Clinico Geral))
 motivo do atendimento. 
- prioridade: Prioridade
-----------------------------------------------------------------------
-- Consulta especializada:
 - Relaciona com especialidade medica.
 - Profissional de especialidade compatível. <---  - Deve permitir o registro da especialidade e das observações relacionadas ao atendimento. <--- Pergunta: Regra de negócios dentro do método (sem aparição no diagrama)

"- RegistroFuncao(): void"

-----------------------------------------------------------------
-- Atendimento de emergência:

• Destinado a situações que exigem avaliação imediata.
• Deve registrar a classificação de prioridade do atendimento.
• A prioridade deverá ser representada por categorias previamente definidas pelo sis-
tema, como baixa, moderada, alta e crítica.
• O sistema deverá permitir identificar os atendimentos que ainda aguardam avaliação
e aqueles que já foram iniciados ou concluídos.

Atributos e Métodos:

Atributo:


Método:

+ VerificarDisponibilidade(): void 
-----------------------------------------------------------------------------
-- Procedimento cirúrgico
• Destinado ao registro de procedimentos cirúrgicos realizados ou agendados pelo hospital. (Ligação com Consulta em casos que a Função for Cirurgia)
• //// Deve possuir informações específicas, como descrição do procedimento, tipo de cirurgia e observações pre e pos-operatorias. ////
• Deve estar associado a um paciente e a um cirurgião responsável.
• Deve registrar a data e o horário previstos para sua realização, bem como a data e
o horário efetivos, quando aplicável.
• O procedimento poderá estar agendado, em andamento, concluído ou cancelado.
• Não será permitido registrar um procedimento como concluído se ele não tiver sido
iniciado.

Atributos e Metodos:


Método:
Buscar_Relatorio_Cirurgião(): void
