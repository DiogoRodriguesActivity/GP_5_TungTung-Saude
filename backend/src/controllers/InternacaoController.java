import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@RequestMapping("/internacoes")
public class InternacaoController {

    /* O id da internacao e a posicao dela na lista + 1 */
    private final List<Internacao> internacoes = new ArrayList<>();

    private final ConsultaController consultaController;
    private final QuartoController quartoController;

    public InternacaoController(ConsultaController consultaController, QuartoController quartoController) {
        this.consultaController = consultaController;
        this.quartoController = quartoController;
    }

    public static class InternacaoRequest {
        public String idConsulta;
        public String idQuarto;
        @JsonFormat(pattern = "dd/MM/yyyy")
        public LocalDate altaPrevista;
        public String observacoes;
    }

    public static class ObservacoesRequest {
        public String observacoes;
    }

    public static class InternacaoResponse {
        public String id;
        public String idConsulta;
        public String idQuarto;
        @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
        public Date dataEntrada;
        @JsonFormat(pattern = "HH:mm", timezone = "America/Sao_Paulo")
        public Date horario;
        @JsonFormat(pattern = "dd/MM/yyyy")
        public LocalDate altaPrevista;
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
        public Date altaEfetiva;
        public String observacoes;

        public InternacaoResponse(String id, Internacao internacao) {
            this.id = id;
            this.idConsulta = internacao.getIdConsulta();
            this.idQuarto = internacao.getIdQuarto();
            this.dataEntrada = internacao.getDataEntrada();
            this.horario = internacao.getHorario();
            this.altaPrevista = internacao.getAltaPrevista();
            this.altaEfetiva = internacao.getAltaEfetiva();
            this.observacoes = internacao.getObservacoes();
        }
    }

    @GetMapping
    public List<InternacaoResponse> listar() {
        List<InternacaoResponse> resposta = new ArrayList<>();
        for (int i = 0; i < internacoes.size(); i++) {
            resposta.add(new InternacaoResponse(String.valueOf(i + 1), internacoes.get(i)));
        }
        return resposta;
    }

    @GetMapping("/{id}")
    public InternacaoResponse buscar(@PathVariable String id) {
        return new InternacaoResponse(id, buscarInternacao(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InternacaoResponse internar(@RequestBody InternacaoRequest dados) {
        if (dados.idConsulta == null || dados.idQuarto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idConsulta e idQuarto são obrigatórios");
        }

        Consulta consulta = consultaController.buscarConsulta(dados.idConsulta);
        Quarto quarto = quartoController.buscarQuarto(dados.idQuarto);

        try {
            quarto.assignarPacientes(1);
        } catch (QuartoCheioExcpetion e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

        Date agora = new Date();
        Internacao internacao = new Internacao(dados.idConsulta, dados.idQuarto, agora, agora,
                dados.altaPrevista, null, dados.observacoes);
        internacoes.add(internacao);

        Paciente paciente = consulta.getPaciente();
        if (paciente != null && paciente.getHistorico() != null) {
            paciente.getHistorico().adicionarInternacao(internacao);
        }
        return new InternacaoResponse(String.valueOf(internacoes.size()), internacao);
    }

    @PatchMapping("/{id}/alta")
    public InternacaoResponse darAlta(@PathVariable String id) {
        Internacao internacao = buscarInternacao(id);
        if (internacao.getAltaEfetiva() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Essa internação já recebeu alta");
        }
        internacao.setAltaEfetiva(new Date());

        Quarto quarto = quartoController.buscarQuarto(internacao.getIdQuarto());
        quarto.setCapacidadeAtual(quarto.getCapacidadeAtual() - 1);
        return new InternacaoResponse(id, internacao);
    }

    @PatchMapping("/{id}/observacoes")
    public InternacaoResponse observacoes(@PathVariable String id, @RequestBody ObservacoesRequest dados) {
        Internacao internacao = buscarInternacao(id);
        internacao.setObservacoes(dados.observacoes);
        return new InternacaoResponse(id, internacao);
    }

    private Internacao buscarInternacao(String id) {
        try {
            int posicao = Integer.parseInt(id) - 1;
            if (posicao >= 0 && posicao < internacoes.size()) {
                return internacoes.get(posicao);
            }
        } catch (NumberFormatException e) {
            // id nao e numero: cai no erro de nao encontrada
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Internação " + id + " não encontrada");
    }
}