package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.edu.fateczl.listaSimples.Lista;
import model.Professor;
import util.arquivoUtil;

public class ProfessorController implements ActionListener {

    private final String CAMINHO = "resources/professores.csv";

    // === CAMPOS DA TELA ===
    private JTextField tfCpfCadastrar;
    private JTextField tfNomeCadastrar;
    private JTextField tfAreaCadastrar;
    private JTextArea taCadastrar;

    private JComboBox<String> cbCpfAtualizar;
    private JTextField tfNovoNome;
    private JTextField tfNovaArea;
    private JTextArea taAtualizar;

    private JComboBox<String> cbBuscarCpf;
    private JTable tabelaBuscar;

    private JTable tabelaListar;

    private JComboBox<String> cbCpfDeletar;
    private JTextArea taDeletar;


    // === CONSTRUTOR VAZIO PARA USO INTERNO ===
    public ProfessorController() {}


    // === CONSTRUTOR CHEIO VINDO DA TELA ===
    public ProfessorController(
            JTextField tfCpfCadastrar,
            JTextField tfNomeCadastrar,
            JTextField tfAreaCadastrar,
            JTextArea taCadastrar,

            JComboBox<String> cbCpfAtualizar,
            JTextField tfNovoNome,
            JTextField tfNovaArea,
            JTextArea taAtualizar,

            JComboBox<String> cbBuscarCpf,
            JTable tabelaBuscar,

            JTable tabelaListar,

            JComboBox<String> cbCpfDeletar,
            JTextArea taDeletar
    ) {
        this.tfCpfCadastrar = tfCpfCadastrar;
        this.tfNomeCadastrar = tfNomeCadastrar;
        this.tfAreaCadastrar = tfAreaCadastrar;
        this.taCadastrar = taCadastrar;

        this.cbCpfAtualizar = cbCpfAtualizar;
        this.tfNovoNome = tfNovoNome;
        this.tfNovaArea = tfNovaArea;
        this.taAtualizar = taAtualizar;

        this.cbBuscarCpf = cbBuscarCpf;
        this.tabelaBuscar = tabelaBuscar;

        this.tabelaListar = tabelaListar;

        this.cbCpfDeletar = cbCpfDeletar;
        this.taDeletar = taDeletar;

        try {
            carregarCombos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        try {
            switch (cmd) {

                case "Cadastrar":
                    cadastrar();
                    carregarCombos();
                    break;

                case "Atualizar":
                    atualizar();
                    //carregarCombos();
                    break;

                case "Buscar":
                    buscar();
                    break;

                case "Listar":
                    listarTabela();
                    break;

                case "Deletar":
                    deletar();
                    carregarCombos();
                    break;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void cadastrar() throws Exception {

        String cpf = tfCpfCadastrar.getText().trim();
        String nome = tfNomeCadastrar.getText().trim();
        String area = tfAreaCadastrar.getText().trim();

        if (cpf.isEmpty() || nome.isEmpty() || area.isEmpty()) {
            taCadastrar.setText("Preencha todos os campos!");
            return;
        }

        if (buscarPorCPF(cpf) != null) {
            taCadastrar.setText("Já existe um professor com este CPF!");
            return;
        }

        int pontos = (int) (Math.random() * 101);

        Professor p = new Professor(cpf, nome, area, pontos);

        arquivoUtil.adicionarLinha(CAMINHO, p.toString());

        taCadastrar.setText("Professor cadastrado com sucesso!\nPontuação: " + pontos);
    }


    private void atualizar() throws Exception {
        String cpf = (String) cbCpfAtualizar.getSelectedItem();

        if (cpf == null || cpf.isEmpty()) {
            taAtualizar.setText("Selecione um CPF!");
            return;
        }

        String novoNome = tfNovoNome.getText().trim();
        String novaArea = tfNovaArea.getText().trim();

        if (novoNome.isEmpty() || novaArea.isEmpty()) {
            taAtualizar.setText("Preencha todos os campos!");
            return;
        }

        Lista<String> linhas = arquivoUtil.lerArquivo(CAMINHO);
        Lista<String> novas = new Lista<>();

        for (int i = 0; i < linhas.size(); i++) {
            String[] d = linhas.get(i).split(";");

            if (d[0].equals(cpf)) {
                Professor att = new Professor(cpf, novoNome, novaArea, Integer.parseInt(d[3]));
                novas.add(att.toString(), novas.size());
            } else {
                novas.add(linhas.get(i), novas.size());
            }
        }

        arquivoUtil.gravarArquivo(CAMINHO, novas);
        taAtualizar.setText("Professor atualizado com sucesso!");
    }


    private void buscar() throws Exception {

        String cpf = (String) cbBuscarCpf.getSelectedItem();
        Professor p = buscarPorCPF(cpf);

        String[] colunas = { "CPF", "Nome", "Área", "Pontos" };
        Object[][] dados = new Object[1][4];

        if (p != null) {
            dados[0][0] = p.getCpf();
            dados[0][1] = p.getNome();
            dados[0][2] = p.getArea();
            dados[0][3] = p.getPontos();
        }

        tabelaBuscar.setModel(new DefaultTableModel(dados, colunas));
    }


    private void listarTabela() throws Exception {

        Lista<Professor> lista = listar();
        String[] colunas = { "CPF", "Nome", "Área", "Pontos" };
        Object[][] dados = new Object[lista.size()][4];

        for (int i = 0; i < lista.size(); i++) {
            Professor p = lista.get(i);
            dados[i][0] = p.getCpf();
            dados[i][1] = p.getNome();
            dados[i][2] = p.getArea();
            dados[i][3] = p.getPontos();
        }

        tabelaListar.setModel(new DefaultTableModel(dados, colunas));
    }


    public void deletar() throws Exception {

        String cpf = (String) cbCpfDeletar.getSelectedItem();

        if (cpf == null || cpf.isEmpty()) {
            taDeletar.setText("Selecione um CPF!");
            return;
        }

        Lista<String> linhas = arquivoUtil.lerArquivo(CAMINHO);
        Lista<String> novas = new Lista<>();

        for (int i = 0; i < linhas.size(); i++) {
            String[] d = linhas.get(i).split(";");

            if (!d[0].equals(cpf)) {
                novas.add(linhas.get(i), novas.size());
            }
        }

        arquivoUtil.gravarArquivo(CAMINHO, novas);
        taDeletar.setText("Professor removido com sucesso!");
    }

    public Lista<Professor> listar() throws Exception {

        Lista<String> linhas = arquivoUtil.lerArquivo(CAMINHO);
        Lista<Professor> lista = new Lista<>();

        for (int i = 0; i < linhas.size(); i++) {
            String[] d = linhas.get(i).split(";");

            Professor p = new Professor(d[0], d[1], d[2], Integer.parseInt(d[3]));
            lista.add(p, lista.size());
        }

        return lista;
    }


    public Professor buscarPorCPF(String cpf) throws Exception {

        Lista<Professor> lista = listar();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCpf().equals(cpf)) {
                return lista.get(i);
            }
        }

        return null;
    }


    private void carregarCombos() throws Exception {
        Lista<Professor> lista = listar();

        cbCpfAtualizar.removeAllItems();
        cbBuscarCpf.removeAllItems();
        cbCpfDeletar.removeAllItems();

        cbCpfAtualizar.addItem("");
        cbBuscarCpf.addItem("");
        cbCpfDeletar.addItem("");

        for (int i = 0; i < lista.size(); i++) {
            Professor p = lista.get(i);
            cbCpfAtualizar.addItem(p.getCpf());
            cbBuscarCpf.addItem(p.getCpf());
            cbCpfDeletar.addItem(p.getCpf());
        }
    }
}
