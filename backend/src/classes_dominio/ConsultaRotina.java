import java.util.Date;

public class ConsultaRotina extends Consulta{


    public ConsultaRotina(Date dataConsulta, Date horario,String motivo,ProfissionalSaude profissional,Paciente paciente,Especialidade especialidade){
    super(dataConsulta,horario,motivo,profissional,paciente,especialidade);
    }

    @Override 
    public void assignarProfissional(ProfissionalSaude profissional)throws ProfissionalIndisponivelException, ProfissionalInelegivelException{
        if(profissional.getDisponibilidade()){

            if(profissional instanceof ClinicoGeral){
                this.setProfissional(profissional);
                System.out.println("Profissional assignado com sucesso");
            }else{
                throw new ProfissionalInelegivelException("Esse profissional não é um clinico geral");
            }
        }
        else{
            throw new ProfissionalIndisponivelException("Esse profissional não está disponível");
        }

    }
    
}
