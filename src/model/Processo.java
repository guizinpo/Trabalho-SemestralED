package model;

public class Processo {
	
	private String codigoProcesso;
	private String idDisciplina;
	private String status;
	
	public Processo() {
		super();
	}
	
	public Processo(String codigoProcesso, String idDisciplina, String status) {
		this.setCodigoProcesso(codigoProcesso);
		this.setIdDisciplina(idDisciplina);
		this.setStatus(status);
	}

	public String getCodigoProcesso() {
		return codigoProcesso;
	}

	public void setCodigoProcesso(String codigoProcesso) {
		this.codigoProcesso = codigoProcesso;
	}

	public String getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(String idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toString() {
		return codigoProcesso + ";" + idDisciplina + ";" + status;
	}

}
