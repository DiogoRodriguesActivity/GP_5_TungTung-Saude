public class ClinicoGeral extends ProfissionalSaude {
    
    public Consulta encaminharPacienteConsultaEspecializada(Paciente paciente, Consulta consultaAtual,Especialidade especialidade){
        if(consultaAtual.getProfissional()==this && consultaAtual.getPaciente()==paciente){


            this.setDisponibilidade(true);
            Consulta novaConsulta = new ConsultaEspecializada();
            novaConsulta.setPaciente(paciente);
            
        }

    }


    
}
