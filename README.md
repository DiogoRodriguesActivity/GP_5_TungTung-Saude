# GP_5_TungTung-Saude

Consulta:
Motivo_da_Consulta: string
id_medico: string
id_paciente: string
Observção_Medicas: string
----------------------------
Agendamento(): void --cria objeto consulta
 

Internação:
id_quarto: string
id_medico: string
id_paciente: string
acompanhante: string
horário: date
data_entrada: date
data prevista: date
data efetiva: date
Observção_Medicas: string


Profissional:
id_medico: string
Nome: string
especialidade: string
Telefone: string
E-mail: string


Paciente:
CPF: string
Nome: string
endereço: string
Data_de_Nasc: Date
Telefone: string
acompanhante: string

Quarto:
Numero de identificação
Andar: 
Capacidade_maxima_de_paciente: int
Disponibilidade: Bool







Agendamento:
nome_paciente: string
numero_paciente: string
numero_convenio: string

```
GP_5_TungTung-Saude
├─ backend
├─ frontend
│  ├─ assets
│  │  ├─ css
│  │  │  ├─ componentes
│  │  │  │  └─ header-base.css
│  │  │  ├─ estruturas-base
│  │  │  │  ├─ footer.css
│  │  │  │  ├─ header.css
│  │  │  │  ├─ main.css
│  │  │  │  ├─ nav.css
│  │  │  │  └─ section.css
│  │  │  ├─ paginas
│  │  │  │  ├─ paciente.css
│  │  │  │  ├─ profissional.css
│  │  │  │  └─ quarto.css
│  │  │  ├─ reset.css
│  │  │  ├─ style.css
│  │  │  └─ var.css
│  │  ├─ img
│  │  └─ js
│  ├─ index.html
│  └─ pages
└─ README.md

```