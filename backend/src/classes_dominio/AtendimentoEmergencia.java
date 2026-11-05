import java.util.Date;

public class AtendimentoEmergencia extends Consulta{
    private Prioridade prioridade;

    public AtendimentoEmergencia(Date dataConsulta, Date horario,String motivo,ProfissionalSaude profissional,Paciente paciente,Especialidade especialidade,Prioridade prioridade){
        super(dataConsulta,horario, motivo, profissional, paciente, especialidade);
        prioridade = this.prioridade;
        
    }
        public void assignarProfissional(ProfissionalSaude profissional) throws ProfissionalIndisponivelException, ProfissionalInelegivelException{
        if(profissional.VerificarDisponibilidade()){
            if(profissional.getEspecialidades().contains(this.getEspecialidade())){

                this.setProfissional(profissional);
                profissional.setDisponibilidade(false);
            }
            else{
                throw new ProfissionalInelegivelException("Esse profissional não possui a especialidade necessaria");
            }
        }
        else{
        throw new ProfissionalIndisponivelException ("Profissional não disponível");            
        }
        
    }
        public Prioridade getPrioridade() {
            return prioridade;
        }
        public void setPrioridade(Prioridade prioridade) {
            this.prioridade = prioridade;
        }
    

}
