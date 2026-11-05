import java.util.Date;

public abstract class Consulta{
    private Date dataConsulta;
    private Date horario;
    private SituacaoConsulta status;
    private String motivo;
    private String observacoes;
    private ProfissionalSaude profissional;
    private Paciente paciente;
    private Especialidade especialidade;

    public Consulta(Date dataConsulta, Date horario,String motivo,ProfissionalSaude profissional,Paciente paciente,Especialidade especialidade){
        this.dataConsulta = dataConsulta;
        this.horario = horario;
        this.motivo = motivo;
        this.profissional = profissional;
        this.paciente = paciente;
        this.especialidade = especialidade;
        status = SituacaoConsulta.AGENDADA;
    }
    public Consulta(){
        dataConsulta = null;
        horario = null;
        motivo = null;
        profissional = null;
        paciente = null;
        especialidade = null;
        status = null;
    }
    
    public Especialidade getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public void adicionarPaciente(Paciente paciente){
        this.paciente = paciente;
    }
        public void realizarConsulta(){
        status = SituacaoConsulta.CONCLUIDA;
        System.out.println("Consulta concluida");
    }

    public void adicionarMotivo(String motivo){
        this.motivo = motivo;
    }
    public void adicionarObservacoes(String observacoes){
        this.observacoes = observacoes;
    }

    public abstract void assignarProfissional(ProfissionalSaude profissional) throws ProfissionalInelegivelException, ProfissionalIndisponivelException;

    public void agendarConsulta(Date dataConsulta, Date horario){
        this.dataConsulta = dataConsulta;
        this.horario = horario;
        status = SituacaoConsulta.AGENDADA;
    }

    public void confirmar(){
        status = SituacaoConsulta.CONFIRMADA;
        System.out.println("Consulta confirmada com sucesso");
    }

    public void iniciar(){
        status = SituacaoConsulta.EM_ATENDIMENTO;
        System.out.println("Consulta Iniciada");
    }
    public void cancelar(){
        status = SituacaoConsulta.CANCELADA;
        System.out.println("Consulta cancelada");
    }
    public Date getDataConsulta() {
        return dataConsulta;
    }
    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }
    public Date getHorario() {
        return horario;
    }
    public void setHorario(Date horario) {
        this.horario = horario;
    }
    public SituacaoConsulta getStatus() {
        return status;
    }
    public void setStatus(SituacaoConsulta status) {
        this.status = status;
    }
    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    public ProfissionalSaude getProfissional() {
        return profissional;
    }
    public void setProfissional(ProfissionalSaude profissional) {
        this.profissional = profissional;
    }
}