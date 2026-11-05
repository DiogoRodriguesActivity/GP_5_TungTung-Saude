import java.util.ArrayList;
public abstract class ProfissionalSaude {
    private String registroProfissional;
    private String nome;
    private String telefone;
    private String email;
    private ArrayList<Especialidade> atuacoes;
    private Boolean disponibilidade;


    public ProfissionalSaude(String registroProfissional,String nome, String telefone, String email){
        this.registroProfissional = registroProfissional;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        atuacoes = new ArrayList<Especialidade>();
        disponibilidade = true;

    }
    public void adicionarAtuacao(Especialidade atuacao){
        atuacoes.add(atuacao);
    }
    public void removerAtuacao(Especialidade atuacao){
        atuacoes.remove(atuacao);
    }
    public ArrayList<Especialidade> getEspecialidades(){
        return atuacoes;
    }
    public void internarPaciente(Consulta consulta, Paciente paciente) throws ProfissionalInelegivelException{
        
        if(this == consulta.getProfissional()){
            if(paciente == consulta.getPaciente()){

            }
            else{
                throw new ProfissionalInelegivelException("Esse profissional não consulta esse paciente");
            }
        }
        else{
            throw new ProfissionalIndisponivelException("Esse profissional não está assignado a esta consulta");
        }
    }
    public void adicionarObservacao(Consulta consulta, String observacao) throws ProfissionalIndisponivelException{
        if(this == consulta.getProfissional()){
            consulta.adicionarObservacoes(observacao);
        }
        else{
            throw new ProfissionalIndisponivelException("Esse profissional não está assignado a esta consulta");
        }
    }
    public boolean VerificarDisponibilidade(){
        if(disponibilidade){
            return true;
        }
        else{
            return false;
        }
    }



    public String getRegistroProfissional() {
        return registroProfissional;
    }



    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
    }



    public String getNome() {
        return nome;
    }



    public void setNome(String nome) {
        this.nome = nome;
    }



    public String getTelefone() {
        return telefone;
    }



    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public ArrayList<Especialidade> getAtuacao() {
        return atuacoes;
    }



    public void adicionarEspecialidade(Especialidade especialidade){
        atuacoes.add(especialidade);
    }
    public void removerEspecialidade(Especialidade especialidade){
        atuacoes.remove(especialidade);
    }


    public Boolean getDisponibilidade() {
        return disponibilidade;
    }



    public void setDisponibilidade(Boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
    

}
