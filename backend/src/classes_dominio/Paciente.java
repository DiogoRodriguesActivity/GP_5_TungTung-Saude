import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Paciente {

    private String idPaciente;
    private String idAcompanhante;
    private String cpf;
    private String nome;
    private Date dataNascimento;
    private String telefone;
    private ArrayList<String> telefoneOutro;
    private String endereco;
    private String email;
    private Historico historico;
    private String acompanhanteNome;

    // Construtor vazio
    public Paciente() {
        this.telefoneOutro = new ArrayList<>();
    }

    public void cadastrar() {

        Scanner input = new Scanner(System.in);

        System.out.println("BEM VINDO AO CADASTRO DE PACIENTE");

        System.out.println("DIGITE O ID DO PACIENTE");
        this.idPaciente = input.nextLine();

        System.out.println("DIGITE O ID DO ACOMPANHANTE");
        this.idAcompanhante = input.nextLine();

        System.out.println("DIGITE O CPF DO PACIENTE");
        this.cpf = input.nextLine();

        System.out.println("DIGITE O NOME DO PACIENTE");
        this.nome = input.nextLine();

        System.out.println("DIGITE A DATA DE NASCIMENTO (DD/MM/AAAA)");
        String dataTexto = input.nextLine();

        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            formato.setLenient(false);
            this.dataNascimento = formato.parse(dataTexto);

        } catch (ParseException e) {
            System.out.println("Data inválida!");
            input.close();
            return;
        }

        System.out.println("DIGITE O TELEFONE PRINCIPAL");
        this.telefone = input.nextLine();

        this.telefoneOutro.clear();

        String resposta;

        do {
            System.out.println("DESEJA CADASTRAR OUTRO TELEFONE? (S/N)");
            resposta = input.nextLine();

            if (resposta.equalsIgnoreCase("S")) {
                System.out.println("DIGITE O TELEFONE ADICIONAL");
                this.telefoneOutro.add(input.nextLine());
            }

        } while (resposta.equalsIgnoreCase("S"));

        System.out.println("DIGITE O ENDEREÇO");
        this.endereco = input.nextLine();

        System.out.println("DIGITE O EMAIL");
        this.email = input.nextLine();

        System.out.println("DIGITE O NOME DO ACOMPANHANTE");
        this.acompanhanteNome = input.nextLine();

        this.historico = new Historico();

        System.out.println("CADASTRO REALIZADO COM SUCESSO!");
        input.close();
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getIdAcompanhante() {
        return idAcompanhante;
    }

    public void setIdAcompanhante(String idAcompanhante) {
        this.idAcompanhante = idAcompanhante;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ArrayList<String> getTelefoneOutro() {
        return telefoneOutro;
    }

    public void setTelefoneOutro(ArrayList<String> telefoneOutro) {
        this.telefoneOutro = telefoneOutro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Historico getHistorico() {
        return historico;
    }

    public void setHistorico(Historico historico) {
        this.historico = historico;
    }

    public String getAcompanhanteNome() {
        return acompanhanteNome;
    }

    public void setAcompanhanteNome(String acompanhanteNome) {
        this.acompanhanteNome = acompanhanteNome;
    }
    
}
