import java.util.Date;
import java.time.LocalDate;

public class Internacao {
    private String idConsulta;
    private String idQuarto;
    private Date horario;
    private Date dataEntrada;
    private LocalDate altaPrevista;
    private Date altaEfetiva;
    private String observacoes;

    public Internacao(String idConsulta, String idQuarto, Date horario, Date dataEntrada, LocalDate altaPrevista, 
        Date altaEfetiva, String observacoes
    ){
        this.idConsulta = idConsulta;
        this.idQuarto = idQuarto;
        this.horario=horario;
        this.dataEntrada = dataEntrada;
        this.altaEfetiva = altaEfetiva;
        this.altaPrevista = altaPrevista;
        this.observacoes = observacoes;

    }
    public String buscarObservacao(){
        return getObservacoes();
    }

    public String getIdConsulta() {
        return idConsulta;
    }
    public void setIdConsulta(String idConsulta) {
        this.idConsulta = idConsulta;
    }
    public String getIdQuarto() {
        return idQuarto;
    }
    public void setIdQuarto(String idQuarto) {
        this.idQuarto = idQuarto;
    }
    public Date getHorario() {
        return horario;
    }
    public void setHorario(Date horario) {
        this.horario = horario;
    }
    public Date getDataEntrada() {
        return dataEntrada;
    }
    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
    public LocalDate getAltaPrevista() {
        return altaPrevista;
    }
    public void setAltaPrevista(LocalDate altaPrevista) {
        this.altaPrevista = altaPrevista;
    }
    public Date getAltaEfetiva() {
        return altaEfetiva;
    }
    public void setAltaEfetiva(Date altaEfetiva) {
        this.altaEfetiva = altaEfetiva;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }





}
