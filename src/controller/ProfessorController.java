package controller;
import model.Professor;
import util.arquivoUtil;
import br.edu.fateczl.listaSimples.Lista;
import br.edu.fateczl.quick.quick;

public class ProfessorController {

    private static final String CAMINHO = "resources/professores.csv";

    public void inserir(Professor p) throws Exception {
        String linha = p.toString();
        arquivoUtil.adicionarLinha(CAMINHO, linha);
    }

    public Lista<Professor> listar() throws Exception {

        Lista<String> linhas = arquivoUtil.lerArquivo(CAMINHO);
        Lista<Professor> professores = new Lista<>();

        for (int i = 0; i < linhas.size(); i++) {

            String linha = linhas.get(i);
            if (linha == null || linha.isEmpty()) continue;

            String[] dados = linha.split(";");

            Professor p = new Professor();
            p.setCpf(dados[0]);
            p.setNome(dados[1]);
            p.setArea(dados[2]);
            p.setPontos(Integer.parseInt(dados[3]));

            professores.add(p, 0);
        }

        return professores;
    }

    public void remover(String cpf) throws Exception {

        Lista<Professor> professores = listar();
        Lista<String> novasLinhas = new Lista<>();

        for (int i = 0; i < professores.size(); i++) {
            Professor p = professores.get(i);

            if (!p.getCpf().equals(cpf)) {
                novasLinhas.add(p.toString(), 0);
            }
        }

        arquivoUtil.gravarArquivo(CAMINHO, novasLinhas);
    }

    public void atualizar(Professor atualizado) throws Exception {

        Lista<Professor> professores = listar();
        Lista<String> novasLinhas = new Lista<>();

        for (int i = 0; i < professores.size(); i++) {
            Professor p = professores.get(i);

            if (p.getCpf().equals(atualizado.getCpf())) {
                p = atualizado;
            }

            novasLinhas.add(p.toString(), 0);
        }

        arquivoUtil.gravarArquivo(CAMINHO, novasLinhas);
    }

    public Professor buscarPorCPF(String cpf) throws Exception {

        Lista<Professor> professores = listar();

        for (int i = 0; i < professores.size(); i++) {
            Professor p = professores.get(i);

            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }

        return null; 
    }

    public Lista<Professor> listarOrdenadosPorPontos() throws Exception {

    	Lista<Professor> professores = listar();
    	
    	int n = professores.size();
        int[] pontos = new int[n];

        for (int i = 0; i < n; i++) {
            pontos[i] = professores.get(i).getPontos();
        }
        
        quick qs = new quick();
        qs.quickSort(pontos, 0, n - 1);
        
        Lista<Professor> ordenada = new Lista<>();

        for (int i = 0; i < n; i++) {
            int pontoAtual = pontos[i];
            
            for (int j = 0; j < professores.size(); j++) {
                Professor p = professores.get(j);
                if (p.getPontos() == pontoAtual) {
                    ordenada.add(p, 0);
                    break;
                }
            }
        }
        
        return ordenada;
    }
}