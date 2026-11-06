import java.util.Date;

public class ConsultaEspecializada extends Consulta{
    


    // public void buscarEspecialista()

    public ConsultaEspecializada(Date dataConsulta, Date horario,String motivo,ProfissionalSaude profissional,Paciente paciente,Especialidade especialidade){
        super(dataConsulta,horario,motivo,profissional,paciente,especialidade);
    }
    public ConsultaEspecializada(){
        super();
    }
@Override

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


}
