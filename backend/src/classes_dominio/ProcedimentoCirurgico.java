import java.time.LocalDateTime;

public class ProcedimentoCirurgico extends Consulta {
    private Especialidade tipoCirurgico;
    private String observacaoPreCirurgia;
    private String observacaoPosCirurgia;
    private LocalDateTime horarioPrevisto;
    private LocalDateTime horarioEfetivo;
    private LocalDateTime horarioFim;
    private StatusCirurgico statusCirurgico;


    public ProcedimentoCirurgico(Especialidade especialidade, String observacaoPreCirurgia, LocalDateTime horarioPrevisto,StatusCirurgico status){
        tipoCirurgico = especialidade;
        this.observacaoPreCirurgia = observacaoPreCirurgia;
        this.horarioPrevisto = horarioPrevisto;
        this.statusCirurgico = status;
        this.observacaoPosCirurgia = null;
        this.horarioEfetivo = null;
        this.horarioFim = null;

    }

    

    public Especialidade getTipoCirurgico() {
        return tipoCirurgico;
    }



    public void setTipoCirurgico(Especialidade tipoCirurgico) {
        this.tipoCirurgico = tipoCirurgico;
    }



    public String getObservacaoPreCirurgia() {
        return observacaoPreCirurgia;
    }



    public void setObservacaoPreCirurgia(String observacaoPreCirurgia) {
        this.observacaoPreCirurgia = observacaoPreCirurgia;
    }



    public String getObservacaoPosCirurgia() {
        return observacaoPosCirurgia;
    }



    public void setObservacaoPosCirurgia(String observacaoPosCirurgia) {
        this.observacaoPosCirurgia = observacaoPosCirurgia;
    }



    public LocalDateTime getHorarioPrevisto() {
        return horarioPrevisto;
    }



    public void setHorarioPrevisto(LocalDateTime horarioPrevisto) {
        this.horarioPrevisto = horarioPrevisto;
    }



    public LocalDateTime getHorarioEfetivo() {
        return horarioEfetivo;
    }



    public void setHorarioEfetivo(LocalDateTime horarioEfetivo) {
        this.horarioEfetivo = horarioEfetivo;
    }



    public LocalDateTime getHorarioFim() {
        return horarioFim;
    }



    public void setHorarioFim(LocalDateTime horarioFim) {
        this.horarioFim = horarioFim;
    }



    public StatusCirurgico getStatusCirurgico() {
        return this.statusCirurgico;
    }



    public void setStatusCirurgico(StatusCirurgico status) {
        this.statusCirurgico = status;
    }



    @Override 
    public void assignarProfissional(ProfissionalSaude profissional) throws ProfissionalIndisponivelException, ProfissionalInelegivelException{
        if(profissional.getDisponibilidade()){

            if(profissional instanceof Cirurgiao){
                this.setProfissional(profissional);
                profissional.setDisponibilidade(false);
                System.out.println("Profissional adicionado com sucesso");
            }
            else{
                throw new ProfissionalInelegivelException("Esse profissional naõ é um cirurgião");
            }
            
        }   
        else{
            throw new ProfissionalIndisponivelException("Esse profissional naõ está disponível");
        }     
    }
    
}
