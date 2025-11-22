package model;

public class Curso {
	
	public String id;
	public String nome;
	public String area;
	
	@Override
	public String toString() {
		return id + ";" + nome + ";" + area;
	}
	
}