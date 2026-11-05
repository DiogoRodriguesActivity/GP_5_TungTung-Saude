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
@RequestMapping("/quartos")
public class QuartoController {

    private final List<Quarto> quartos = new ArrayList<>();

    public static class QuartoRequest {
        public String numero;
        public String andar;
        public int capacidadeMaxima;
    }

    public static class QuartoResponse {
        public String numero;
        public String andar;
        public int capacidadeMaxima;
        public int capacidadeAtual;
        public boolean situacao;

        public QuartoResponse(Quarto quarto) {
            this.numero = quarto.getNumero();
            this.andar = quarto.getAndar();
            this.capacidadeMaxima = quarto.getCapacidadeMaxima();
            this.capacidadeAtual = quarto.getCapacidadeAtual();
            this.situacao = quarto.VerificarDisponibilidade();
        }
    }

    @GetMapping
    public List<QuartoResponse> listar(@RequestParam(required = false) Boolean disponivel) {
        List<QuartoResponse> resposta = new ArrayList<>();
        for (Quarto quarto : quartos) {
            if (disponivel == null || disponivel == quarto.VerificarDisponibilidade()) {
                resposta.add(new QuartoResponse(quarto));
            }
        }
        return resposta;
    }

    @GetMapping("/{numero}")
    public QuartoResponse buscar(@PathVariable String numero) {
        return new QuartoResponse(buscarQuarto(numero));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuartoResponse cadastrar(@RequestBody QuartoRequest dados) {
        if (dados.numero == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O número do quarto é obrigatório");
        }
        for (Quarto existente : quartos) {
            if (existente.getNumero().equals(dados.numero)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um quarto com o número " + dados.numero);
            }
        }

        Quarto quarto = new Quarto(dados.numero, dados.andar, dados.capacidadeMaxima);
        // o construtor de Quarto ainda nao atribui os campos, entao preenche pelos setters
        quarto.setNumero(dados.numero);
        quarto.setAndar(dados.andar);
        quarto.setCapacidadeMaxima(dados.capacidadeMaxima);

        quartos.add(quarto);
        return new QuartoResponse(quarto);
    }

    @PutMapping("/{numero}")
    public QuartoResponse atualizar(@PathVariable String numero, @RequestBody QuartoRequest dados) {
        Quarto quarto = buscarQuarto(numero);
        quarto.setAndar(dados.andar);
        quarto.setCapacidadeMaxima(dados.capacidadeMaxima);
        return new QuartoResponse(quarto);
    }

    @DeleteMapping("/{numero}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable String numero) {
        quartos.remove(buscarQuarto(numero));
    }

    /* Usado pelo InternacaoController */
    public Quarto buscarQuarto(String numero) {
        for (Quarto quarto : quartos) {
            if (quarto.getNumero().equals(numero)) {
                return quarto;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quarto " + numero + " não encontrado");
    }
}