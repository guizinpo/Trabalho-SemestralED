package model;

public class Disciplina {
        
    private String id;
    private String nome;
    private String diaSemana;
    private String horaInicial;
    private String horasDiarias;
    private String idCurso;

    public Disciplina(){
        super();
    }

    public Disciplina(String id, String nome, String diaSemana, String horaInicial,
            String horasDiarias, String idCurso) {
        this.diaSemana = diaSemana;
        this.horaInicial = horaInicial;
        this.horasDiarias = horasDiarias;
        this.id = id;
        this.idCurso = idCurso;
        this.nome = nome;
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
    public String getDiaSemana() {
        return diaSemana;
    }
    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }
    public String getHoraInicial() {
        return horaInicial;
    }
    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }
    public String getHorasDiarias() {
        return horasDiarias;
    }
    public void setHorasDiarias(String horasDiarias) {
        this.horasDiarias = horasDiarias;
    }
    public String getidCurso() {
        return idCurso;
    }
    public void setidCurso(String idCurso) {
        this.idCurso = idCurso;
    }
    @Override
    public String toString() {
        return id + ";" + nome + ";" + diaSemana + ";" + horaInicial
                + ";" + horasDiarias + ";" + idCurso;
    }

}
