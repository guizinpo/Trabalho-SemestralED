package controller;

import java.io.IOException;
import model.Disciplina;
import util.arquivoUtil;
import br.edu.fateczl.listaSimples.Lista;

public class DisciplinaController {

private final String caminhoDisciplina = "disciplina.csv";

    private Lista<Disciplina> disciplinas;

    public DisciplinaController() throws Exception{
        this.disciplinas = new Lista<>();
        carregarDisciplinas();
    }

    public void carregarDisciplinas() throws Exception{
        try{
            Lista<String> linhas = arquivoUtil.lerArquivo(caminhoDisciplina);
            while(!disciplinas.isEmpty()) {
            	disciplinas.removeFirst();
            }
            int tamanho = linhas.size();
            for(int i = 0; i < tamanho; i++){
            	String linha = linhas.get(i);
                String[] info = linha.toString().split(";");
                if(info.length == 6){
                    Disciplina dis = new Disciplina(info[0], info[1], info[2], info[3], info[4], info[5]);
                    disciplinas.add(dis, 0);
                }
            } 
        }catch(IOException e){
            System.err.println("Erro ao caregar disciplina: " + e.getMessage());
        }
    }

    private void salvarDisciplinas() throws Exception{
        Lista<String> linhas = new Lista<>();
        int tamanho = disciplinas.size();
        for(int i = 0; i < tamanho; i++){
        	Disciplina dis = disciplinas.get(i);
            linhas.add(dis.toString(), 0);
        }
        try{
            arquivoUtil.gravarArquivo(caminhoDisciplina, linhas);
        }catch(IOException e){
            System.err.println("Erro ao salvar disciplina: " + e.getMessage());
        }
    }

    public void adicionarDisciplina(Disciplina dis) throws Exception{
        disciplinas.add(dis, 0);
        salvarDisciplinas();
    }

    public boolean removerDisciplina(String idDisciplina, String nome) throws Exception{
    	boolean removido = false;
        int tamanho = disciplinas.size();
        for(int i = 0; i < tamanho; i++){
            Disciplina dis = disciplinas.get(i);
            if(dis.getId().equals(idDisciplina) && dis.getNome().equals(nome)){
                disciplinas.remove(i);
                break;
            }
        }
        if(removido){
            salvarDisciplinas();
        }
        return removido;
    }

    public boolean atualizarDisciplina(String idDisciplina, String nome, Disciplina atualizacao) throws Exception{
        boolean atualizado = false;
        int tamanho = disciplinas.size();
        for(int i = 0; i < tamanho; i++){
            Disciplina dis = disciplinas.get(i);
            if(dis.getId().equals(idDisciplina) && dis.getNome().equals(nome)){
                disciplinas.remove(i);
                break;
            }
        }
        if(atualizado){
            adicionarDisciplina(atualizacao);
        }
        return atualizado;
    }

    public void verificarDisciplinas() throws Exception{
        if(!disciplinas.isEmpty()) {
        	int tamanho = disciplinas.size();
	        for(int i = 0; i < tamanho; i++){
	        	Disciplina dis = disciplinas.get(i);
	            System.out.println(dis.toString());
	        }
        }else{
            System.out.println("Não existe nenhuma disciplina registrada");
        }
    }
}
