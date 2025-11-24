package model;

public class Curso {
	
	private String id;
	private String nome;
	private String area;
	
	@Override
	public String toString() {
		return id + ";" + nome + ";" + area;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
}