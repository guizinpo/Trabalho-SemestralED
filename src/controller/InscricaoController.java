package controller;

import model.Inscricao;
import util.arquivoUtil;
import br.edu.fateczl.listaSimples.Lista;

public class InscricaoController {

    private static final String CAMINHO = "resources/inscricoes.csv";

    public void inserir(Inscricao inscricao) throws Exception {
        String linha = inscricao.toString();
        arquivoUtil.adicionarLinha(CAMINHO, linha);
    }

    public Lista<Inscricao> listar() throws Exception {
        Lista<String> linhas = arquivoUtil.lerArquivo(CAMINHO);
        Lista<Inscricao> inscricoes = new Lista<>();

        for (int i = 0; i < linhas.size(); i++) {
            String linha = linhas.get(i);
            if (linha != null && !linha.isEmpty()) {
                String[] dados = linha.split(";");
                Inscricao ins = new Inscricao();
                ins.setCpfProfessor(dados[0]);
                ins.setIdDisciplina(dados[1]);
                ins.setCodigoProcesso(dados[2]);
                inscricoes.add(ins, i);
            }
        }
        return inscricoes;
    }

    public void remover(String cpfProfessor, String codigoProcesso) throws Exception {
        Lista<Inscricao> inscricoes = listar();
        Lista<String> novasLinhas = new Lista<>();

        for (int i = 0; i < inscricoes.size(); i++) {
            Inscricao ins = inscricoes.get(i);
            if (!ins.getCpfProfessor().equals(cpfProfessor) ||
                !ins.getCodigoProcesso().equals(codigoProcesso)) {
                novasLinhas.add(ins.toString(), 0);
            }
        }

        arquivoUtil.gravarArquivo(CAMINHO, novasLinhas);
    }

    public void atualizar(String cpfProfessor, String novoCodigo) throws Exception {
        Lista<Inscricao> inscricoes = listar();
        Lista<String> novasLinhas = new Lista<>();

        for (int i = 0; i < inscricoes.size(); i++) {
            Inscricao ins = inscricoes.get(i);
            if (ins.getCpfProfessor().equals(cpfProfessor)) {
                ins.setCodigoProcesso(novoCodigo);
            }
            novasLinhas.add(ins.toString(), 0);
        }

        arquivoUtil.gravarArquivo(CAMINHO, novasLinhas);
    }

    public Lista<Inscricao> buscarPorProfessor(String cpfProfessor) throws Exception {
        Lista<Inscricao> inscricoes = listar();
        Lista<Inscricao> resultado = new Lista<>();

        for (int i = 0; i < inscricoes.size(); i++) {
            Inscricao ins = inscricoes.get(i);
            if (ins.getCpfProfessor().equals(cpfProfessor)) {
                resultado.add(ins, 0);
            }
        }
        return resultado;
    }
}
