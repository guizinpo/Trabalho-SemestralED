package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.listaSimples.Lista;
import br.edu.fateczl.quick.quick;
import model.Inscricao;
import model.Professor;
import model.InscritoCompleto;
import util.arquivoUtil;

public class InscricaoController implements ActionListener {

    private static final String CAMINHO = "resources/inscricoes.csv";

    private JTextField tfCpfCadastrar;
    private JTextField tfIdDisciplinaCadastrar;
    private JTextField tfCodigoProcessoCadastrar;
    private JTextArea taCadastrar;

    private JTextField tfCpfAtualizar;
    private JTextField tfNovoCodigoProcesso;
    private JTextArea taAtualizar;

    private JTextField tfBuscarDisciplina;
    private JTable tabelaConsulta;

    private JTextField tfCpfDeletar;
    private JTextField tfCodigoProcessoDeletar;
    private JTextArea taDeletar;

    private ProfessorController professorController = new ProfessorController();
    
    public InscricaoController() {
    	super();
    }

    public InscricaoController(
            JTextField tfCpfCadastrar,
            JTextField tfIdDisciplinaCadastrar,
            JTextField tfCodigoProcessoCadastrar,
            JTextArea taCadastrar,

            JTextField tfCpfAtualizar,
            JTextField tfNovoCodigoProcesso,
            JTextArea taAtualizar,

            JTextField tfBuscarDisciplina,
            JTable tabelaConsulta,

            JTextField tfCpfDeletar,
            JTextField tfCodigoProcessoDeletar,
            JTextArea taDeletar
    ) {

        this.tfCpfCadastrar = tfCpfCadastrar;
        this.tfIdDisciplinaCadastrar = tfIdDisciplinaCadastrar;
        this.tfCodigoProcessoCadastrar = tfCodigoProcessoCadastrar;
        this.taCadastrar = taCadastrar;

        this.tfCpfAtualizar = tfCpfAtualizar;
        this.tfNovoCodigoProcesso = tfNovoCodigoProcesso;
        this.taAtualizar = taAtualizar;

        this.tfBuscarDisciplina = tfBuscarDisciplina;
        this.tabelaConsulta = tabelaConsulta;

        this.tfCpfDeletar = tfCpfDeletar;
        this.tfCodigoProcessoDeletar = tfCodigoProcessoDeletar;
        this.taDeletar = taDeletar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        try {
            switch (cmd) {

                case "Cadastrar":
                    cadastrarInscricao();
                    break;

                case "Atualizar":
                    atualizarInscricao();
                    break;

                case "Consultar":
                    consultarInscritos();
                    break;

                case "Deletar":
                    deletarInscricao();
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void cadastrarInscricao() throws Exception {
        String cpf = tfCpfCadastrar.getText().trim();
        String disciplina = tfIdDisciplinaCadastrar.getText().trim();
        String processo = tfCodigoProcessoCadastrar.getText().trim();

        if (cpf.isEmpty() || disciplina.isEmpty() || processo.isEmpty()) {
            taCadastrar.setText("Preencha todos os campos!");
            return;
        }

        Professor p = professorController.buscarPorCPF(cpf);
        if (p == null) {
            taCadastrar.setText("Professor não encontrado no sistema.");
            return;
        }

        Inscricao ins = new Inscricao(cpf, disciplina, processo);
        arquivoUtil.adicionarLinha(CAMINHO, ins.toString());

        taCadastrar.setText("Inscrição cadastrada com sucesso!");
    }

    private void atualizarInscricao() throws Exception {
        String cpf = tfCpfAtualizar.getText().trim();
        String novoProcesso = tfNovoCodigoProcesso.getText().trim();

        if (cpf.isEmpty() || novoProcesso.isEmpty()) {
            taAtualizar.setText("Preencha todos os campos!");
            return;
        }

        Lista<Inscricao> inscricoes = listar();
        Lista<String> novasLinhas = new Lista<>();

        boolean atualizado = false;

        for (int i = 0; i < inscricoes.size(); i++) {
            Inscricao ins = inscricoes.get(i);

            if (ins.getCpfProfessor().equals(cpf)) {
                ins.setCodigoProcesso(novoProcesso);
                atualizado = true;
            }

            novasLinhas.add(ins.toString(), novasLinhas.size());
        }

        arquivoUtil.gravarArquivo(CAMINHO, novasLinhas);

        if (atualizado) {
            taAtualizar.setText("Inscrição atualizada com sucesso!");
        } else {
            taAtualizar.setText("Nenhuma inscrição encontrada para este CPF.");
        }
    }

    private void consultarInscritos() throws Exception {
        String idDisciplina = tfBuscarDisciplina.getText().trim();

        Lista<Inscricao> todas = listar();
        Lista<InscritoCompleto> lista = new Lista<>();

        // Montar estrutura completa
        for (int i = 0; i < todas.size(); i++) {
            Inscricao ins = todas.get(i);

            if (ins.getIdDisciplina().equals(idDisciplina)) {

                Professor p = professorController.buscarPorCPF(ins.getCpfProfessor());

                if (p != null) {
                    lista.add(new InscritoCompleto(
                            p.getNome(),
                            p.getCpf(),
                            p.getArea(),
                            p.getPontos(),
                            ins.getIdDisciplina(),
                            ins.getCodigoProcesso()
                    ), 0);
                }
            }
        }

        ordenarListaPorPontos(lista);
        atualizarTabela(lista);
    }

    private void deletarInscricao() throws Exception {
        String cpf = tfCpfDeletar.getText().trim();
        String processo = tfCodigoProcessoDeletar.getText().trim();

        Lista<Inscricao> todas = listar();
        Lista<String> novasLinhas = new Lista<>();

        boolean removido = false;

        for (int i = 0; i < todas.size(); i++) {
            Inscricao ins = todas.get(i);

            if (ins.getCpfProfessor().equals(cpf)
                    && ins.getCodigoProcesso().equals(processo)) {
                removido = true;
            } else {
                novasLinhas.add(ins.toString(), novasLinhas.size());
            }
        }

        arquivoUtil.gravarArquivo(CAMINHO, novasLinhas);

        if (removido) taDeletar.setText("Inscrição removida com sucesso!");
        else taDeletar.setText("Inscrição não encontrada.");
    }
    
    public void remover(String cpf, String processo) throws Exception {
        Lista<Inscricao> todas = listar();
        Lista<String> novasLinhas = new Lista<>();

        boolean removido = false;

        for (int i = 0; i < todas.size(); i++) {
            Inscricao ins = todas.get(i);

            if (ins.getCpfProfessor().equals(cpf) &&
                ins.getCodigoProcesso().equals(processo)) {
                removido = true;
            } else {
                novasLinhas.add(ins.toString(), novasLinhas.size());
            }
        }

        arquivoUtil.gravarArquivo(CAMINHO, novasLinhas);
    }


    Lista<Inscricao> listar() throws Exception{
        Lista<String> linhas = arquivoUtil.lerArquivo(CAMINHO);
        Lista<Inscricao> lista = new Lista<>();

        for (int i = 0; i < linhas.size(); i++) {
            String linha = linhas.get(i);
            if (linha == null || linha.isEmpty()) continue;

            String[] d = linha.split(";");

            lista.add(new Inscricao(d[0], d[1], d[2]), lista.size());
        }

        return lista;
    }

    private void ordenarListaPorPontos(Lista<InscritoCompleto> lista) {
        try {
            int n = lista.size();
            int[] pontos = new int[n];

            for (int i = 0; i < n; i++) {
                pontos[i] = lista.get(i).getPontos();
            }

            quick q = new quick();
            q.quickSort(pontos, 0, n - 1);

            Lista<InscritoCompleto> ordenada = new Lista<>();

            for (int i = 0; i < n; i++) {
                int pAtual = pontos[i];

                for (int j = 0; j < lista.size(); j++) {
                    if (lista.get(j).getPontos() == pAtual) {
                        ordenada.add(lista.get(j), 0);
                        break;
                    }
                }
            }

            lista = ordenada;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void atualizarTabela(Lista<InscritoCompleto> lista) throws Exception {
        String[] colunas = {"Nome", "CPF", "Área", "Pontos", "Disciplina", "Processo"};
        Object[][] dados = new Object[lista.size()][6];

        for (int i = 0; i < lista.size(); i++) {
            InscritoCompleto ic = lista.get(i);

            dados[i][0] = ic.getNome();
            dados[i][1] = ic.getCpf();
            dados[i][2] = ic.getArea();
            dados[i][3] = ic.getPontos();
            dados[i][4] = ic.getDisciplina();
            dados[i][5] = ic.getProcesso();
        }

        tabelaConsulta.setModel(new javax.swing.table.DefaultTableModel(dados, colunas));
    }
}
