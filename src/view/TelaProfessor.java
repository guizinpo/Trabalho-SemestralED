package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ProfessorController;
import model.Professor;

import java.awt.Font;

import br.edu.fateczl.listaSimples.Lista;

public class TelaProfessor extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTabbedPane tabbedPane;

    // CADASTRAR
    private JTextField tfCpfCadastrar;
    private JTextField tfNomeCadastrar;
    private JTextField tfAreaCadastrar;
    private JTextArea taCadastrar;

    // ATUALIZAR
    private JTextField tfCpfAtualizar;
    private JTextField tfNovoNome;
    private JTextField tfNovaArea;
    private JTextArea taAtualizar;

    // CONSULTAR
    private JTextField tfBuscarCpf;
    private JTable tabelaBuscar;

    // LISTAR TODOS
    private JTable tabelaListar;

    // DELETAR
    private JTextField tfCpfDeletar;
    private JTextArea taDeletar;

    private ProfessorController controller;

    public TelaProfessor() {

        setTitle("Professores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 520);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 10, 660, 460);
        contentPane.add(tabbedPane);

        controller = new ProfessorController();

        JPanel tabCadastrar = new JPanel();
        tabCadastrar.setLayout(null);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setFont(new Font("Dialog", Font.BOLD, 14));
        lblCpf.setBounds(30, 20, 200, 25);
        tabCadastrar.add(lblCpf);

        tfCpfCadastrar = new JTextField();
        tfCpfCadastrar.setBounds(30, 45, 250, 25);
        tabCadastrar.add(tfCpfCadastrar);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Dialog", Font.BOLD, 14));
        lblNome.setBounds(30, 80, 200, 25);
        tabCadastrar.add(lblNome);

        tfNomeCadastrar = new JTextField();
        tfNomeCadastrar.setBounds(30, 105, 250, 25);
        tabCadastrar.add(tfNomeCadastrar);

        JLabel lblArea = new JLabel("Área:");
        lblArea.setFont(new Font("Dialog", Font.BOLD, 14));
        lblArea.setBounds(30, 140, 200, 25);
        tabCadastrar.add(lblArea);

        tfAreaCadastrar = new JTextField();
        tfAreaCadastrar.setBounds(30, 165, 250, 25);
        tabCadastrar.add(tfAreaCadastrar);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(330, 165, 150, 30);
        tabCadastrar.add(btnCadastrar);

        taCadastrar = new JTextArea();
        taCadastrar.setBounds(30, 220, 580, 160);
        tabCadastrar.add(taCadastrar);

        tabbedPane.addTab("Cadastrar", tabCadastrar);

        JPanel tabAtualizar = new JPanel();
        tabAtualizar.setLayout(null);

        JLabel lblCpfAtt = new JLabel("CPF do Professor:");
        lblCpfAtt.setFont(new Font("Dialog", Font.BOLD, 14));
        lblCpfAtt.setBounds(30, 20, 200, 25);
        tabAtualizar.add(lblCpfAtt);

        tfCpfAtualizar = new JTextField();
        tfCpfAtualizar.setBounds(30, 45, 250, 25);
        tabAtualizar.add(tfCpfAtualizar);

        JLabel lblNovoNomeLbl = new JLabel("Novo Nome:");
        lblNovoNomeLbl.setFont(new Font("Dialog", Font.BOLD, 14));
        lblNovoNomeLbl.setBounds(30, 80, 200, 25);
        tabAtualizar.add(lblNovoNomeLbl);

        tfNovoNome = new JTextField();
        tfNovoNome.setBounds(30, 105, 250, 25);
        tabAtualizar.add(tfNovoNome);

        JLabel lblNovaAreaLbl = new JLabel("Nova Área:");
        lblNovaAreaLbl.setFont(new Font("Dialog", Font.BOLD, 14));
        lblNovaAreaLbl.setBounds(30, 140, 200, 25);
        tabAtualizar.add(lblNovaAreaLbl);

        tfNovaArea = new JTextField();
        tfNovaArea.setBounds(30, 165, 250, 25);
        tabAtualizar.add(tfNovaArea);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(330, 165, 150, 30);
        tabAtualizar.add(btnAtualizar);

        taAtualizar = new JTextArea();
        taAtualizar.setBounds(30, 220, 580, 160);
        tabAtualizar.add(taAtualizar);

        tabbedPane.addTab("Atualizar", tabAtualizar);

        JPanel tabConsultar = new JPanel();
        tabConsultar.setLayout(null);

        JLabel lblBuscarCpfLbl = new JLabel("CPF do Professor:");
        lblBuscarCpfLbl.setFont(new Font("Dialog", Font.BOLD, 14));
        lblBuscarCpfLbl.setBounds(30, 20, 200, 25);
        tabConsultar.add(lblBuscarCpfLbl);

        tfBuscarCpf = new JTextField();
        tfBuscarCpf.setBounds(30, 45, 250, 25);
        tabConsultar.add(tfBuscarCpf);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(330, 45, 150, 30);
        tabConsultar.add(btnBuscar);

        tabelaBuscar = new JTable();
        JScrollPane scrollBuscar = new JScrollPane(tabelaBuscar);
        scrollBuscar.setBounds(30, 100, 580, 280);
        tabConsultar.add(scrollBuscar);

        tabbedPane.addTab("Consultar", tabConsultar);

        JPanel tabListar = new JPanel();
        tabListar.setLayout(null);

        JButton btnListar = new JButton("Listar Todos");
        btnListar.setBounds(30, 25, 150, 30);
        tabListar.add(btnListar);

        tabelaListar = new JTable();
        JScrollPane scrollListar = new JScrollPane(tabelaListar);
        scrollListar.setBounds(30, 80, 580, 300);
        tabListar.add(scrollListar);

        tabbedPane.addTab("Listar Todos", tabListar);

        JPanel tabDeletar = new JPanel();
        tabDeletar.setLayout(null);

        JLabel lblCpfDel = new JLabel("CPF do Professor:");
        lblCpfDel.setFont(new Font("Dialog", Font.BOLD, 14));
        lblCpfDel.setBounds(30, 20, 200, 25);
        tabDeletar.add(lblCpfDel);

        tfCpfDeletar = new JTextField();
        tfCpfDeletar.setBounds(30, 45, 250, 25);
        tabDeletar.add(tfCpfDeletar);

        JButton btnDeletar = new JButton("Deletar");
        btnDeletar.setBounds(330, 45, 150, 30);
        tabDeletar.add(btnDeletar);

        taDeletar = new JTextArea();
        taDeletar.setBounds(30, 100, 580, 280);
        tabDeletar.add(taDeletar);

        tabbedPane.addTab("Deletar", tabDeletar);

        btnCadastrar.addActionListener(e -> cadastrar());
        btnAtualizar.addActionListener(e -> atualizar());
        btnBuscar.addActionListener(e -> buscarCPF());
        btnListar.addActionListener(e -> listarTodos());
        btnDeletar.addActionListener(e -> deletar());
    }

    private void cadastrar() {
        try {
            Professor p = new Professor();
            p.setCpf(tfCpfCadastrar.getText());
            p.setNome(tfNomeCadastrar.getText());
            p.setArea(tfAreaCadastrar.getText());
            p.setPontos((int) (Math.random() * 101));

            controller.inserir(p);
            taCadastrar.setText("Professor cadastrado com sucesso!\nPontuação: " + p.getPontos());
        } catch (Exception e) {
            taCadastrar.setText("Erro: " + e.getMessage());
        }
    }

    private void atualizar() {
        try {
            Professor p = new Professor();
            p.setCpf(tfCpfAtualizar.getText());
            p.setNome(tfNovoNome.getText());
            p.setArea(tfNovaArea.getText());
            p.setPontos(controller.buscarPorCPF(p.getCpf()).getPontos());

            controller.atualizar(p);
            taAtualizar.setText("Professor atualizado com sucesso!");
        } catch (Exception e) {
            taAtualizar.setText("Erro: " + e.getMessage());
        }
    }

    private void buscarCPF() {
        try {
            String cpf = tfBuscarCpf.getText();
            Professor p = controller.buscarPorCPF(cpf);

            String[] colunas = { "CPF", "Nome", "Área", "Pontos" };
            Object[][] dados = new Object[1][4];

            if (p != null) {
                dados[0][0] = p.getCpf();
                dados[0][1] = p.getNome();
                dados[0][2] = p.getArea();
                dados[0][3] = p.getPontos();
            }

            tabelaBuscar.setModel(new DefaultTableModel(dados, colunas));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listarTodos() {
        try {
            Lista<Professor> lista = controller.listar();

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deletar() {
        try {
            controller.remover(tfCpfDeletar.getText());
            taDeletar.setText("Professor removido com sucesso!");
        } catch (Exception e) {
            taDeletar.setText("Erro: " + e.getMessage());
        }
    }
}