package model;

public class Inscricao {
	
	public Inscricao() {
    	super();
    }
	
	private String cpfProfessor;
    private String idDisciplina;
    private String codigoProcesso;
    
    public Inscricao(String cpfProfessor, String idDisciplina, String codigoProcesso) {
        this.cpfProfessor = cpfProfessor;
        this.idDisciplina = idDisciplina;
        this.codigoProcesso = codigoProcesso;
    }
    
    public String getCpfProfessor() {
        return cpfProfessor;
    }

    public void setCpfProfessor(String cpfProfessor) {
        this.cpfProfessor = cpfProfessor;
    }

    public String getIdDisciplina() {
        return idDisciplina;
    }
    
    public void setIdDisciplina(String idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getCodigoProcesso() {
        return codigoProcesso;
    }
    
    public void setCodigoProcesso(String codigoProcesso) {
        this.codigoProcesso = codigoProcesso;
    }

    @Override
    public String toString() {
        return cpfProfessor + ";" + idDisciplina + ";" + codigoProcesso;
    }
}
