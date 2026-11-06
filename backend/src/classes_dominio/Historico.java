import java.util.ArrayList;
public class Historico {
    private String idHistorico;
    private String observacoes;
    private String observacoesMedicas;
    private ArrayList<Internacao> internacoes;
    private ArrayList<Consulta> consultas;   
    
    public Historico(){
        this.internacoes = new  ArrayList<Internacao>();
        this.consultas = new ArrayList<Consulta>();
    }
    public Historico(String observacoes, String observacoesMedicas){
        this.observacoes = observacoes;
        this.observacoesMedicas = observacoesMedicas;
        this.internacoes = new ArrayList<Internacao>();
        this.consultas = new ArrayList<Consulta>();
    }
    public void adicionarObservacoes(String observacoes){
        this.observacoes = observacoes;
    }
    public void adicionarObservacoesMedicas(String observacoesMedicas){
        this.observacoesMedicas = observacoesMedicas;
    }
    public void adicionarInternacao(Internacao internacao){
        internacoes.add(internacao);
        System.out.println("Internação adicionada com sucesso");
    }
    public void adicionarConsulta(Consulta consulta){
        consultas.add(consulta);
        System.out.println("Consulta adicionada com sucesso");
    }
    public void exibirHistorico(){

        System.out.println("HISTORICO HOSPITALAR \n"+
        "ID HISTORICO: "+ idHistorico + "\n CONSULTAS: "+
        consultas + "\n INTERNAÇÕES: "+ internacoes +
        "OBSERVAÇÕES: "+ observacoes + "OBSERVACOES MÉDICAS: "+
        observacoesMedicas
        );

    }

    public String getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(String idHistorico) {
        this.idHistorico = idHistorico;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getObservacoesMedicas() {
        return observacoesMedicas;
    }

    public void setObservacoesMedicas(String observacoesMedicas) {
        this.observacoesMedicas = observacoesMedicas;
    }

    public ArrayList<Internacao> getInternacoes() {
        return internacoes;
    }

    public void setInternacoes(ArrayList<Internacao> internacoes) {
        this.internacoes = internacoes;
    }

    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(ArrayList<Consulta> consultas) {
        this.consultas = consultas;
    }

}