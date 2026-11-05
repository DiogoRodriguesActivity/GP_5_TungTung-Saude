public class Quarto {
    private String numero;
    private String andar;
    private int capacidadeMaxima;
    private int capacidadeAtual;
    private boolean situacao;
    

    public Quarto(String numero, String andar, int capacidadeMaxima){

    }
    public void assignarPacientes(int quantidadePacientes) throws QuartoCheioExcpetion{
        if(capacidadeAtual + quantidadePacientes < capacidadeMaxima){
            this.capacidadeAtual = capacidadeAtual+quantidadePacientes;
            System.out.println("Pacientes Assignados com sucesso");
        }
        else{
            throw new QuartoCheioExcpetion("Quarto não comporta essa quantidade de pacientes");
        }
    }

    public boolean VerificarDisponibilidade(){
        if(capacidadeAtual >= capacidadeMaxima){
            situacao=false;
        }
        else{
            situacao = true;
        }
        return isSituacao();
    }



    public String getNumero() {
        return numero;
    }



    public void setNumero(String numero) {
        this.numero = numero;
    }



    public String getAndar() {
        return andar;
    }



    public void setAndar(String andar) {
        this.andar = andar;
    }



    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }



    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }



    public boolean isSituacao() {
        return situacao;
    }



    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }



    public int getCapacidadeAtual() {
        return capacidadeAtual;
    }



    public void setCapacidadeAtual(int capacidadeAtual) {
        this.capacidadeAtual = capacidadeAtual;
    }

    
}
