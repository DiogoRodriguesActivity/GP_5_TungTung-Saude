import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    private final List<ProfissionalSaude> profissionais = new ArrayList<>();

    /* tipo: CLINICO_GERAL, MEDICO_ESPECIALISTA ou CIRURGIAO */
    public static class ProfissionalRequest {
        public String tipo;
        public String registroProfissional;
        public String nome;
        public String telefone;
        public String email;
        public List<Especialidade> especialidades;
    }

    public static class ProfissionalResponse {
        public String tipo;
        public String registroProfissional;
        public String nome;
        public String telefone;
        public String email;
        public List<Especialidade> especialidades;
        public Boolean disponibilidade;

        public ProfissionalResponse(ProfissionalSaude profissional) {
            this.tipo = profissional.getClass().getSimpleName();
            this.registroProfissional = profissional.getRegistroProfissional();
            this.nome = profissional.getNome();
            this.telefone = profissional.getTelefone();
            this.email = profissional.getEmail();
            this.especialidades = profissional.getEspecialidades();
            this.disponibilidade = profissional.getDisponibilidade();
        }
    }

    @GetMapping
    public List<ProfissionalResponse> listar(
            @RequestParam(required = false) Especialidade especialidade,
            @RequestParam(required = false) Boolean disponibilidade) {
        List<ProfissionalResponse> resposta = new ArrayList<>();
        for (ProfissionalSaude profissional : profissionais) {
            boolean temEspecialidade = especialidade == null || profissional.getEspecialidades().contains(especialidade);
            boolean estaDisponivel = disponibilidade == null || disponibilidade == profissional.VerificarDisponibilidade();
            if (temEspecialidade && estaDisponivel) {
                resposta.add(new ProfissionalResponse(profissional));
            }
        }
        return resposta;
    }

    @GetMapping("/{registroProfissional}")
    public ProfissionalResponse buscar(@PathVariable String registroProfissional) {
        return new ProfissionalResponse(buscarProfissional(registroProfissional));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfissionalResponse cadastrar(@RequestBody ProfissionalRequest dados) {
        if (dados.registroProfissional == null || dados.nome == null || dados.tipo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "tipo, registroProfissional e nome são obrigatórios");
        }
        for (ProfissionalSaude existente : profissionais) {
            if (existente.getRegistroProfissional().equals(dados.registroProfissional)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Já existe um profissional com o registro " + dados.registroProfissional);
            }
        }

        ProfissionalSaude profissional;
        if (dados.tipo.equals("CLINICO_GERAL")) {
            profissional = new ClinicoGeral(dados.registroProfissional, dados.nome, dados.telefone, dados.email);
        } else if (dados.tipo.equals("MEDICO_ESPECIALISTA")) {
            profissional = new MedicoEspecialista(dados.registroProfissional, dados.nome, dados.telefone, dados.email);
        } else if (dados.tipo.equals("CIRURGIAO")) {
            profissional = new Cirurgiao(dados.registroProfissional, dados.nome, dados.telefone, dados.email);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "tipo deve ser CLINICO_GERAL, MEDICO_ESPECIALISTA ou CIRURGIAO");
        }

        if (dados.especialidades != null) {
            for (Especialidade especialidade : dados.especialidades) {
                profissional.adicionarEspecialidade(especialidade);
            }
        }

        profissionais.add(profissional);
        return new ProfissionalResponse(profissional);
    }

    @PutMapping("/{registroProfissional}")
    public ProfissionalResponse atualizar(@PathVariable String registroProfissional,
            @RequestBody ProfissionalRequest dados) {
        ProfissionalSaude profissional = buscarProfissional(registroProfissional);
        profissional.setNome(dados.nome);
        profissional.setTelefone(dados.telefone);
        profissional.setEmail(dados.email);

        if (dados.especialidades != null) {
            for (Especialidade antiga : new ArrayList<>(profissional.getEspecialidades())) {
                profissional.removerEspecialidade(antiga);
            }
            for (Especialidade nova : dados.especialidades) {
                profissional.adicionarEspecialidade(nova);
            }
        }
        return new ProfissionalResponse(profissional);
    }

    @DeleteMapping("/{registroProfissional}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable String registroProfissional) {
        profissionais.remove(buscarProfissional(registroProfissional));
    }

    /* Usado pelo ConsultaController */
    public ProfissionalSaude buscarProfissional(String registroProfissional) {
        for (ProfissionalSaude profissional : profissionais) {
            if (profissional.getRegistroProfissional().equals(registroProfissional)) {
                return profissional;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Profissional " + registroProfissional + " não encontrado");
    }
}