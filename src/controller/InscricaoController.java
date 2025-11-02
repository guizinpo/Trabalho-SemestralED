package controller;

import model.Inscricao;
import util.arquivoUtil;
import java.io.IOException;
import java.util.*;

public class InscricaoController {

    private final String caminhoInscricoes = "inscricoes.csv";
    
    private LinkedList<Inscricao> inscricoes;

    public InscricaoController() {
        this.inscricoes = new LinkedList<>();
        carregarInscricoes();
    }

    private void carregarInscricoes() {
        try {
            List<String> linhas = arquivoUtil.lerArquivo(caminhoInscricoes);
            inscricoes.clear();
            for (String linha : linhas) {
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    inscricoes.add(new Inscricao(partes[0], partes[1], partes[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar inscrições: " + e.getMessage());
        }
    }

    private void salvarInscricoes() {
        List<String> linhas = new ArrayList<>();
        for (Inscricao i : inscricoes) {
            linhas.add(i.toString());
        }
        try {
            arquivoUtil.gravarArquivo(caminhoInscricoes, linhas);
        } catch (IOException e) {
            System.out.println("Erro ao salvar inscrições: " + e.getMessage());
        }
    }

    public void adicionarInscricao(Inscricao inscricao) {
        inscricoes.add(inscricao);
        salvarInscricoes();
    }

    public boolean removerInscricao(String cpfProfessor, String idDisciplina) {
        Iterator<Inscricao> it = inscricoes.iterator();
        boolean removido = false;
        while (it.hasNext()) {
            Inscricao i = it.next();
            if (i.getCpfProfessor().equals(cpfProfessor) && i.getIdDisciplina().equals(idDisciplina)) {
                it.remove();
                removido = true;
            }
        }
        if (removido) {
            salvarInscricoes();
        }
        return removido;
    }

    public List<Inscricao> consultarPorDisciplina(String idDisciplina, Map<String, Integer> pontuacoes) {
        List<Inscricao> resultado = new ArrayList<>();
        for (Inscricao i : inscricoes) {
            if (i.getIdDisciplina().equals(idDisciplina)) {
                resultado.add(i);
            }
        }
        quickSort(resultado, pontuacoes, 0, resultado.size() - 1);
        return resultado;
    }

    private void quickSort(List<Inscricao> lista, Map<String, Integer> pontuacoes, int low, int high) {
        if (low < high) {
            int pi = partition(lista, pontuacoes, low, high);
            quickSort(lista, pontuacoes, low, pi - 1);
            quickSort(lista, pontuacoes, pi + 1, high);
        }
    }

    private int partition(List<Inscricao> lista, Map<String, Integer> pontuacoes, int low, int high) {
        Inscricao pivot = lista.get(high);
        int pivotValue = pontuacoes.getOrDefault(pivot.getCpfProfessor(), 0);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            int valorJ = pontuacoes.getOrDefault(lista.get(j).getCpfProfessor(), 0);
            if (valorJ >= pivotValue) { // ordem decrescente
                i++;
                Collections.swap(lista, i, j);
            }
        }
        Collections.swap(lista, i + 1, high);
        return i + 1;
    }

    public List<Inscricao> consultarTodas() {
        return new ArrayList<>(inscricoes);
    }
}
