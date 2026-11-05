import java.time.LocalDate;
import java.time.LocalDateTime;
public abstract class AtendimentoEspecializado {
    private String observacoes;
    private LocalDateTime horarioEntrada;
    private Prioridade prioridade;
    private Especialidade especialidade;
    private MedicoEspecialista profissional;
    private  Historico historico;


    public AtendimentoEspecializado(LocalDateTime horarioEntrada, Prioridade prioridade, Especialidade especialidade, Historico historico){
    this.horarioEntrada = horarioEntrada;
    this.prioridade = prioridade;
    this.especialidade = especialidade;
    this.profissional = null;
    this.observacoes = null;
    this.historico = historico;
    }

    public void assignarProfissional(MedicoEspecialista profissional) throws ProfissionalInelegivelException{
        if(profissional.getEspecialidades().contains(especialidade)){
            this.profissional = profissional;
            System.out.println("Profissional assignado com sucesso");
        }
        else{
            throw new ProfissionalInelegivelException("Esse profissional não possui a especialidade necessária");
        }
    }

    public void adicionarObservacao(String observacao){
        this.observacoes = observacao;   
    }
    

}
