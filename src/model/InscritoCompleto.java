package model;

public class InscritoCompleto {

    private String nome;
    private String cpf;
    private String area;
    private int pontos;
    private String disciplina;
    private String processo;

    public InscritoCompleto(String nome, String cpf, String area, int pontos,
                            String disciplina, String processo) {
        this.nome = nome;
        this.cpf = cpf;
        this.area = area;
        this.pontos = pontos;
        this.disciplina = disciplina;
        this.processo = processo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getArea() {
        return area;
    }

    public int getPontos() {
        return pontos;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public String getProcesso() {
        return processo;
    }
}