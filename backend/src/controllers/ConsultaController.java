import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    /* O id da consulta e a posicao dela na lista + 1 (consultas nao sao apagadas, so canceladas) */
    private final List<Consulta> consultas = new ArrayList<>();

    private final PacienteController pacienteController;
    private final ProfissionalController profissionalController;

    public ConsultaController(PacienteController pacienteController, ProfissionalController profissionalController) {
        this.pacienteController = pacienteController;
        this.profissionalController = profissionalController;
    }

    /*
     * tipo: ROTINA, ESPECIALIZADA, EMERGENCIA ou PROCEDIMENTO_CIRURGICO
     * ESPECIALIZADA          -> precisa de especialidade
     * EMERGENCIA             -> precisa de especialidade e prioridade
     * PROCEDIMENTO_CIRURGICO -> precisa de tipoCirurgico (motivo = descricao do procedimento)
     */
    public static class ConsultaRequest {
        public String tipo;
        public String idPaciente;
        public String registroProfissional;
        @JsonFormat(pattern = "dd/MM/yyyy")
        public LocalDate dataConsulta;
        @JsonFormat(pattern = "HH:mm")
        public LocalTime horario;
        public String motivo;
        public Especialidade especialidade;
        public Prioridade prioridade;
        public Especialidade tipoCirurgico;
        public String observacaoPreCirurgia;
    }

    public static class ObservacoesRequest {
        public String observacoes;
    }

    public static class ConsultaResponse {
        public String id;
        public String tipo;
        public SituacaoConsulta status;
        @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
        public Date dataConsulta;
        @JsonFormat(pattern = "HH:mm", timezone = "America/Sao_Paulo")
        public Date horario;
        public String motivo;
        public String observacoes;
        public Especialidade especialidade;
        public String idPaciente;
        public String registroProfissional;

        // so para atendimento de emergencia
        public Prioridade prioridade;

        // so para procedimento cirurgico
        public Especialidade tipoCirurgico;
        public StatusCirurgico statusCirurgico;
        public String observacaoPreCirurgia;
        public String observacaoPosCirurgia;
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        public LocalDateTime horarioPrevisto;
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        public LocalDateTime horarioEfetivo;
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        public LocalDateTime horarioFim;

        public ConsultaResponse(String id, Consulta consulta) {
            this.id = id;
            this.tipo = consulta.getClass().getSimpleName();
            this.status = consulta.getStatus();
            this.dataConsulta = consulta.getDataConsulta();
            this.horario = consulta.getHorario();
            this.motivo = consulta.getMotivo();
            this.observacoes = consulta.getObservacoes();
            this.especialidade = consulta.getEspecialidade();
            if (consulta.getPaciente() != null) {
                this.idPaciente = consulta.getPaciente().getIdPaciente();
            }
            if (consulta.getProfissional() != null) {
                this.registroProfissional = consulta.getProfissional().getRegistroProfissional();
            }

            if (consulta instanceof AtendimentoEmergencia) {
                AtendimentoEmergencia emergencia = (AtendimentoEmergencia) consulta;
                this.prioridade = emergencia.getPrioridade();
            }
            if (consulta instanceof ProcedimentoCirurgico) {
                ProcedimentoCirurgico procedimento = (ProcedimentoCirurgico) consulta;
                this.tipoCirurgico = procedimento.getTipoCirurgico();
                this.statusCirurgico = procedimento.getStatusCirurgico();
                this.observacaoPreCirurgia = procedimento.getObservacaoPreCirurgia();
                this.observacaoPosCirurgia = procedimento.getObservacaoPosCirurgia();
                this.horarioPrevisto = procedimento.getHorarioPrevisto();
                this.horarioEfetivo = procedimento.getHorarioEfetivo();
                this.horarioFim = procedimento.getHorarioFim();
            }
        }
    }

    @GetMapping
    public List<ConsultaResponse> listar(@RequestParam(required = false) SituacaoConsulta status) {
        List<ConsultaResponse> resposta = new ArrayList<>();
        for (int i = 0; i < consultas.size(); i++) {
            Consulta consulta = consultas.get(i);
            if (status == null || consulta.getStatus() == status) {
                resposta.add(new ConsultaResponse(String.valueOf(i + 1), consulta));
            }
        }
        return resposta;
    }

    @GetMapping("/{id}")
    public ConsultaResponse buscar(@PathVariable String id) {
        return new ConsultaResponse(id, buscarConsulta(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultaResponse agendar(@RequestBody ConsultaRequest dados) {
        if (dados.tipo == null || dados.idPaciente == null || dados.dataConsulta == null || dados.horario == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "tipo, idPaciente, dataConsulta e horario são obrigatórios");
        }

        Paciente paciente = pacienteController.buscarPaciente(dados.idPaciente);
        LocalDateTime dataHora = LocalDateTime.of(dados.dataConsulta, dados.horario);
        Date data = java.sql.Date.valueOf(dados.dataConsulta);
        Date horario = java.sql.Timestamp.valueOf(dataHora);

        Consulta consulta;
        if (dados.tipo.equals("ROTINA")) {
            consulta = new ConsultaRotina(data, horario, dados.motivo, null, paciente, dados.especialidade);

        } else if (dados.tipo.equals("ESPECIALIZADA")) {
            consulta = new ConsultaEspecializada(data, horario, dados.motivo, null, paciente, dados.especialidade);

        } else if (dados.tipo.equals("EMERGENCIA")) {
            AtendimentoEmergencia emergencia = new AtendimentoEmergencia(data, horario, dados.motivo, null,
                    paciente, dados.especialidade, dados.prioridade);
            emergencia.setPrioridade(dados.prioridade);
            consulta = emergencia;

        } else if (dados.tipo.equals("PROCEDIMENTO_CIRURGICO")) {
            ProcedimentoCirurgico procedimento = new ProcedimentoCirurgico(dados.tipoCirurgico,
                    dados.observacaoPreCirurgia, dataHora, null);
            procedimento.setPaciente(paciente);
            procedimento.setMotivo(dados.motivo);
            procedimento.agendarConsulta(data, horario);
            consulta = procedimento;

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "tipo deve ser ROTINA, ESPECIALIZADA, EMERGENCIA ou PROCEDIMENTO_CIRURGICO");
        }

        // o profissional e atribuido pelo assignarProfissional(), que faz a validacao de cada modalidade
        if (dados.registroProfissional != null) {
            try {
                consulta.assignarProfissional(profissionalController.buscarProfissional(dados.registroProfissional));
            } catch (ProfissionalInelegivelException | ProfissionalIndisponivelException e) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
            }
        }

        consultas.add(consulta);
        if (paciente.getHistorico() != null) {
            paciente.getHistorico().adicionarConsulta(consulta);
        }
        return new ConsultaResponse(String.valueOf(consultas.size()), consulta);
    }

    @PatchMapping("/{id}/confirmar")
    public ConsultaResponse confirmar(@PathVariable String id) {
        Consulta consulta = buscarConsulta(id);
        consulta.confirmar();
        return new ConsultaResponse(id, consulta);
    }

    @PatchMapping("/{id}/iniciar")
    public ConsultaResponse iniciar(@PathVariable String id) {
        Consulta consulta = buscarConsulta(id);
        consulta.iniciar();
        return new ConsultaResponse(id, consulta);
    }

    @PatchMapping("/{id}/concluir")
    public ConsultaResponse concluir(@PathVariable String id,
            @RequestBody(required = false) ObservacoesRequest dados) {
        Consulta consulta = buscarConsulta(id);
        if (dados != null && dados.observacoes != null) {
            consulta.adicionarObservacoes(dados.observacoes);
        }
        consulta.realizarConsulta();
        return new ConsultaResponse(id, consulta);
    }

    @PatchMapping("/{id}/cancelar")
    public ConsultaResponse cancelar(@PathVariable String id) {
        Consulta consulta = buscarConsulta(id);
        consulta.cancelar();
        return new ConsultaResponse(id, consulta);
    }

    @PatchMapping("/{id}/nao-compareceu")
    public ConsultaResponse naoCompareceu(@PathVariable String id) {
        Consulta consulta = buscarConsulta(id);
        consulta.setStatus(SituacaoConsulta.NAO_COMPARECEU);
        return new ConsultaResponse(id, consulta);
    }

    @PatchMapping("/{id}/observacoes")
    public ConsultaResponse observacoes(@PathVariable String id, @RequestBody ObservacoesRequest dados) {
        Consulta consulta = buscarConsulta(id);
        consulta.adicionarObservacoes(dados.observacoes);
        return new ConsultaResponse(id, consulta);
    }

    /* Usado pelo InternacaoController */
    public Consulta buscarConsulta(String id) {
        try {
            int posicao = Integer.parseInt(id) - 1;
            if (posicao >= 0 && posicao < consultas.size()) {
                return consultas.get(posicao);
            }
        } catch (NumberFormatException e) {
            // id nao e numero: cai no erro de nao encontrada
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta " + id + " não encontrada");
    }
}