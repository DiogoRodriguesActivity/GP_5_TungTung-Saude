public class ClinicoGeral extends ProfissionalSaude {

    public ClinicoGeral(String registroProfissional,String nome, String telefone, String email){
        super(registroProfissional,nome,telefone,email);
    }
    
    public Consulta encaminharPacienteConsultaEspecializada(Paciente paciente, Consulta consultaAtual,Especialidade especialidade){
        if(consultaAtual.getProfissional()==this && consultaAtual.getPaciente()==paciente){


            this.setDisponibilidade(true);
            Consulta novaConsulta = new ConsultaEspecializada();
            novaConsulta.setPaciente(paciente);
            novaConsulta.setEspecialidade(especialidade);
            return novaConsulta;
        }
        else{
            throw new ProfissionalInelegivelException("Esse profissional não está consultando esse paciente");
        }

    }


    
}
