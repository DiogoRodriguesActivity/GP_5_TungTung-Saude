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
├─ docs
│  ├─ Cartões CRC.pdf
│  └─ Diagrama_de_Classes
│     ├─ Tung Tund Saude.drawio.html
│     ├─ Tung Tung Saude.drawio.png
│     └─ Tung-Tung Saude.webp
├─ frontend
│  ├─ assets
│  │  ├─ css
│  │  │  ├─ base.css
│  │  │  ├─ componentes
│  │  │  │  ├─ botao-base.css
│  │  │  │  └─ card-base.css
│  │  │  ├─ estruturas-base
│  │  │  │  ├─ body.css
│  │  │  │  ├─ footer.css
│  │  │  │  ├─ header.css
│  │  │  │  ├─ main.css
│  │  │  │  ├─ nav.css
│  │  │  │  ├─ section.css
│  │  │  │  └─ sistema-interno.css
│  │  │  ├─ pages
│  │  │  │  ├─ consulta.css
│  │  │  │  ├─ consultas.css
│  │  │  │  ├─ historico.css
│  │  │  │  ├─ historicos.css
│  │  │  │  ├─ index.css
│  │  │  │  ├─ internacao.css
│  │  │  │  ├─ internacoes.css
│  │  │  │  ├─ paciente.css
│  │  │  │  ├─ profissionais.css
│  │  │  │  ├─ profissional.css
│  │  │  │  ├─ quarto.css
│  │  │  │  └─ quartos.css
│  │  │  ├─ reset.css
│  │  │  ├─ style.css
│  │  │  └─ var.css
│  │  └─ js
│  │     ├─ componentes
│  │     │  ├─ botao-base.js
│  │     │  ├─ card-base.js
│  │     │  ├─ footer-base.js
│  │     │  ├─ header-base.js
│  │     │  ├─ linha-historico.js
│  │     │  ├─ linha-internacao.js
│  │     │  ├─ linha-paciente.js
│  │     │  ├─ linha-profissional.js
│  │     │  └─ linha-quarto.js
│  │     ├─ dados.js
│  │     ├─ dados.json
│  │     ├─ estruturas-base
│  │     │  ├─ footer.js
│  │     │  ├─ header.js
│  │     │  ├─ main.js
│  │     │  └─ nav.js
│  │     ├─ pages
│  │     │  ├─ consulta.js
│  │     │  ├─ consultas.js
│  │     │  ├─ historico.js
│  │     │  ├─ historicos.js
│  │     │  ├─ internacao.js
│  │     │  ├─ internacoes.js
│  │     │  ├─ paciente.js
│  │     │  ├─ pacientes.js
│  │     │  ├─ profissionais.js
│  │     │  ├─ profissional.js
│  │     │  ├─ quarto.js
│  │     │  └─ quartos.js
│  │     └─ script.js
│  ├─ index.html
│  └─ pages
│     ├─ consulta.html
│     ├─ historico.html
│     ├─ internacao.html
│     ├─ paciente.html
│     ├─ profissional.html
│     └─ quarto.html
├─ images
└─ README.md

```