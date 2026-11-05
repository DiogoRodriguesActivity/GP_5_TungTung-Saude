import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final List<Paciente> pacientes = new ArrayList<>();
    private int proximoId = 1;

    /* O que chega no corpo da requisicao */
    public static class PacienteRequest {
        public String idPaciente;
        public String cpf;
        public String nome;
        @JsonFormat(pattern = "dd/MM/yyyy")
        public LocalDate dataNascimento;
        public String telefone;
        public List<String> telefoneOutro;
        public String endereco;
        public String email;
        public String idAcompanhante;
        public String acompanhanteNome;
    }

    /* O que volta na resposta */
    public static class PacienteResponse {
        public String idPaciente;
        public String cpf;
        public String nome;
        @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
        public Date dataNascimento;
        public String telefone;
        public List<String> telefoneOutro;
        public String endereco;
        public String email;
        public String idAcompanhante;
        public String acompanhanteNome;
        public boolean disponibilidade;

        public PacienteResponse(Paciente paciente) {
            this.idPaciente = paciente.getIdPaciente();
            this.cpf = paciente.getCpf();
            this.nome = paciente.getNome();
            this.dataNascimento = paciente.getDataNascimento();
            this.telefone = paciente.getTelefone();
            this.telefoneOutro = paciente.getTelefoneOutro();
            this.endereco = paciente.getEndereco();
            this.email = paciente.getEmail();
            this.idAcompanhante = paciente.getIdAcompanhante();
            this.acompanhanteNome = paciente.getAcompanhanteNome();
            this.disponibilidade = paciente.isDisponibilidade();
        }
    }

    @GetMapping
    public List<PacienteResponse> listar() {
        List<PacienteResponse> resposta = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            resposta.add(new PacienteResponse(paciente));
        }
        return resposta;
    }

    @GetMapping("/{idPaciente}")
    public PacienteResponse buscar(@PathVariable String idPaciente) {
        return new PacienteResponse(buscarPaciente(idPaciente));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteResponse cadastrar(@RequestBody PacienteRequest dados) {
        if (dados.nome == null || dados.cpf == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome e CPF são obrigatórios");
        }

        String idPaciente = dados.idPaciente != null ? dados.idPaciente : "PAC" + proximoId++;
        for (Paciente existente : pacientes) {
            if (existente.getIdPaciente().equals(idPaciente)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um paciente com o id " + idPaciente);
            }
        }

        Paciente paciente = new Paciente();
        paciente.setIdPaciente(idPaciente);
        preencher(paciente, dados);
        paciente.setHistorico(new Historico());

        pacientes.add(paciente);
        return new PacienteResponse(paciente);
    }

    @PutMapping("/{idPaciente}")
    public PacienteResponse atualizar(@PathVariable String idPaciente, @RequestBody PacienteRequest dados) {
        Paciente paciente = buscarPaciente(idPaciente);
        preencher(paciente, dados);
        return new PacienteResponse(paciente);
    }

    @DeleteMapping("/{idPaciente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable String idPaciente) {
        pacientes.remove(buscarPaciente(idPaciente));
    }

    /* Usado pelo ConsultaController */
    public Paciente buscarPaciente(String idPaciente) {
        for (Paciente paciente : pacientes) {
            if (paciente.getIdPaciente().equals(idPaciente)) {
                return paciente;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente " + idPaciente + " não encontrado");
    }

    private void preencher(Paciente paciente, PacienteRequest dados) {
        paciente.setCpf(dados.cpf);
        paciente.setNome(dados.nome);
        if (dados.dataNascimento != null) {
            paciente.setDataNascimento(java.sql.Date.valueOf(dados.dataNascimento));
        }
        paciente.setTelefone(dados.telefone);
        paciente.setTelefoneOutro(dados.telefoneOutro == null ? new ArrayList<>() : new ArrayList<>(dados.telefoneOutro));
        paciente.setEndereco(dados.endereco);
        paciente.setEmail(dados.email);
        paciente.setIdAcompanhante(dados.idAcompanhante);
        paciente.setAcompanhanteNome(dados.acompanhanteNome);
    }
}