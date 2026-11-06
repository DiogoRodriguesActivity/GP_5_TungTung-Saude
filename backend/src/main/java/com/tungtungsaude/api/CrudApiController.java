package com.tungtungsaude.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CrudApiController {

    private static final Set<String> TIPOS = Set.of(
            "pacientes", "profissionais", "consultas", "internacoes", "quartos");

    private final Map<String, Map<String, Map<String, Object>>> registros = new ConcurrentHashMap<>();
    private final AtomicInteger sequencia = new AtomicInteger(1);

    public CrudApiController() {
        TIPOS.forEach(tipo -> registros.put(tipo, new LinkedHashMap<>()));
        seed();
    }

    private void seed() {
        inserirSemValidacao("pacientes", map("id", "IWK3MFG", "nome", "Julio Cesar",
                "cpf", "109.284.820-32", "dataNascimento", "02/10/2007",
                "telefone", "31925122431", "endereco", "Alameda das Alamedas",
                "acompanhante", ""));
        inserirSemValidacao("pacientes", map("id", "P1L4KGN", "nome", "Carla Viana",
                "cpf", "129.261.523-12", "dataNascimento", "10/02/1987",
                "telefone", "31967125563", "endereco", "Rua das Ruas",
                "acompanhante", ""));

        inserirSemValidacao("profissionais", map("id", "OI2034R", "nome", "Augusto Silva Santos",
                "tipo", "MEDICO_ESPECIALISTA", "especialidade", "OTORRINOLARINGOLOGISTA",
                "crm", "000011", "email", "augusto.silvasantos@gmail.com", "disponibilidade", true));
        inserirSemValidacao("profissionais", map("id", "OI3023K", "nome", "Josiane Ferreira Cardoso",
                "tipo", "MEDICO_ESPECIALISTA", "especialidade", "NEUROLOGISTA",
                "crm", "000021", "email", "josiane.ferreiracardoso@gmail.com", "disponibilidade", true));

        inserirSemValidacao("quartos", map("id", "JAKAOQR", "numeroIdentificacao", "#1023",
                "andar", "2", "capacidadeMaxima", 10, "capacidadeAtual", 0,
                "situacaoAtual", "Disponivel"));
        inserirSemValidacao("quartos", map("id", "IJGIPAT", "numeroIdentificacao", "#3519",
                "andar", "1", "capacidadeMaxima", 2, "capacidadeAtual", 1,
                "situacaoAtual", "Ocupado"));

        inserirSemValidacao("consultas", map("id", "O1P4KSDL", "pacienteId", "IWK3MFG",
                "profissionalId", "OI2034R", "data", "11/09/2026",
                "horario", "14:00", "motivo", "Surdez momentanea",
                "observacaoMedica", "", "status", "AGENDADA", "tipo", "ESPECIALIZADA"));
        inserirSemValidacao("consultas", map("id", "Q01OEJKS", "pacienteId", "P1L4KGN",
                "profissionalId", "OI3023K", "data", "10/09/2026",
                "horario", "09:30", "motivo", "Dor de cabeça",
                "observacaoMedica", "", "status", "AGENDADA", "tipo", "ESPECIALIZADA"));

        inserirSemValidacao("internacoes", map("id", "1OAJFoAs", "pacienteId", "IWK3MFG",
                "profissionalId", "OI2034R", "quartoId", "IJGIPAT",
                "dataEntrada", "11/09/2026", "dataPrevistaAlta", "15/09/2026",
                "observacaoMedica", "Paciente em observacao"));
    }

    @GetMapping("/{tipo}")
    public List<Map<String, Object>> listar(@PathVariable String tipo,
                                            @RequestParam(required = false) String status,
                                            @RequestParam(required = false) String especialidade) {
        validarTipo(tipo);
        return registros.get(tipo).values().stream()
                .filter(item -> status == null || status.equalsIgnoreCase(String.valueOf(item.get("status"))))
                .filter(item -> especialidade == null || especialidade.equalsIgnoreCase(String.valueOf(item.get("especialidade"))))
                .map(item -> (Map<String, Object>) new LinkedHashMap<>(item))
                .toList();
    }

    @GetMapping("/{tipo}/{id}")
    public Map<String, Object> buscar(@PathVariable String tipo, @PathVariable String id) {
        validarTipo(tipo);
        Map<String, Object> item = registros.get(tipo).get(id);
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, tipo + " nao encontrado: " + id);
        }
        return new LinkedHashMap<>(item);
    }

    @PostMapping("/{tipo}")
    public Map<String, Object> criar(@PathVariable String tipo,
                                     @RequestBody Map<String, Object> dados) {
        validarTipo(tipo);
        validarRegra(tipo, dados);
        Map<String, Object> novo = new LinkedHashMap<>(dados);
        String id = String.valueOf(novo.getOrDefault("id", gerarId(tipo)));
        novo.put("id", id);
        aplicarPadroes(tipo, novo);
        registros.get(tipo).put(id, novo);
        return new LinkedHashMap<>(novo);
    }

    @PutMapping("/{tipo}/{id}")
    public Map<String, Object> atualizar(@PathVariable String tipo, @PathVariable String id,
                                         @RequestBody Map<String, Object> dados) {
        validarTipo(tipo);
        if (!registros.get(tipo).containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, tipo + " nao encontrado: " + id);
        }
        Map<String, Object> atualizados = new LinkedHashMap<>(dados);
        atualizados.put("id", id);
        validarRegra(tipo, atualizados);
        aplicarPadroes(tipo, atualizados);
        registros.get(tipo).put(id, atualizados);
        return new LinkedHashMap<>(atualizados);
    }

    @DeleteMapping("/{tipo}/{id}")
    public Map<String, String> excluir(@PathVariable String tipo, @PathVariable String id) {
        validarTipo(tipo);
        Map<String, Object> removido = registros.get(tipo).remove(id);
        if (removido == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, tipo + " nao encontrado: " + id);
        }
        return Map.of("id", id, "status", "removido");
    }

    private void validarTipo(String tipo) {
        if (!TIPOS.contains(tipo)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso nao encontrado: " + tipo);
        }
    }

    private void validarRegra(String tipo, Map<String, Object> dados) {
        if ("quartos".equals(tipo)) {
            int maxima = inteiro(dados.get("capacidadeMaxima"), 0);
            int atual = inteiro(dados.get("capacidadeAtual"), 0);
            if (maxima < 1 || atual < 0 || atual > maxima) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Capacidade invalida: capacidade atual deve ficar entre 0 e a capacidade maxima");
            }
        }
        if ("consultas".equals(tipo)) {
            String status = String.valueOf(dados.getOrDefault("status", "AGENDADA")).toUpperCase();
            try {
                Set.of("AGENDADA", "CONFIRMADA", "EM_ATENDIMENTO", "CONCLUIDA", "CANCELADA", "NAO_COMPARECEU").contains(status);
                if (!Set.of("AGENDADA", "CONFIRMADA", "EM_ATENDIMENTO", "CONCLUIDA", "CANCELADA", "NAO_COMPARECEU")
                        .contains(status)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Situacao de consulta invalida");
                }
            } catch (NullPointerException ignored) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Situacao de consulta invalida");
            }
        }
    }

    private void aplicarPadroes(String tipo, Map<String, Object> dados) {
        if ("consultas".equals(tipo) && !dados.containsKey("status")) {
            dados.put("status", "AGENDADA");
        }
        if ("quartos".equals(tipo)) {
            int maxima = inteiro(dados.get("capacidadeMaxima"), 0);
            int atual = inteiro(dados.get("capacidadeAtual"), 0);
            dados.put("situacaoAtual", atual >= maxima ? "Ocupado" : "Disponivel");
        }
        if ("profissionais".equals(tipo) && !dados.containsKey("disponibilidade")) {
            dados.put("disponibilidade", true);
        }
    }

    private int inteiro(Object valor, int padrao) {
        if (valor == null) {
            return padrao;
        }
        try {
            return Integer.parseInt(String.valueOf(valor));
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor numerico invalido: " + valor);
        }
    }

    private String gerarId(String tipo) {
        String prefixo = switch (tipo) {
            case "pacientes" -> "PAC";
            case "profissionais" -> "PRO";
            case "consultas" -> "CON";
            case "internacoes" -> "INT";
            case "quartos" -> "QUA";
            default -> "REG";
        };
        return prefixo + String.format("%04d", sequencia.getAndIncrement());
    }

    private void inserirSemValidacao(String tipo, Map<String, Object> dados) {
        registros.get(tipo).put(String.valueOf(dados.get("id")), dados);
    }

    private static Map<String, Object> map(Object... pares) {
        Map<String, Object> resultado = new LinkedHashMap<>();
        for (int i = 0; i < pares.length; i += 2) {
            resultado.put(String.valueOf(pares[i]), pares[i + 1]);
        }
        return resultado;
    }
}
