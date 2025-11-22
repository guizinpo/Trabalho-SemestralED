package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.InscricaoController;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
//import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTable;

public class TelaInscricao extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTabbedPane tabbedPane;

    // Cadastrar
    private JTextField tfCpf;
    private JTextField tfIdDisciplina;
    private JTextField tfCodigoProcesso;
    private JTextArea taResultadoCadastrar;

    // Atualizar
    private JTextField tfCpfAtualizar;
    private JTextField tfNovoCodigo;
    private JTextArea taResultadoAtualizar;

    // Consultar
    private JTextField tfBuscarDisciplina;
    private JTable tabelaResultado;

    // Deletar
    private JTextField tfCpfDeletar;
    private JTextField tfProcessoDeletar;
    private JTextArea taResultadoDeletar;

    public TelaInscricao() {

        setTitle("Inscrições");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 680, 520);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        contentPane.setLayout(null);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 10, 650, 470);
        contentPane.add(tabbedPane);

        JPanel tabCadastrar = new JPanel();
        tabCadastrar.setLayout(null);

        JLabel lblCpf = new JLabel("CPF do Professor:");
        lblCpf.setBounds(30, 30, 200, 25);
        tabCadastrar.add(lblCpf);

        tfCpf = new JTextField();
        tfCpf.setBounds(30, 55, 250, 25);
        tabCadastrar.add(tfCpf);

        JLabel lblDisciplina = new JLabel("ID da Disciplina:");
        lblDisciplina.setBounds(30, 90, 200, 25);
        tabCadastrar.add(lblDisciplina);

        tfIdDisciplina = new JTextField();
        tfIdDisciplina.setBounds(30, 115, 250, 25);
        tabCadastrar.add(tfIdDisciplina);

        JLabel lblProcesso = new JLabel("Código do Processo:");
        lblProcesso.setBounds(30, 150, 200, 25);
        tabCadastrar.add(lblProcesso);

        tfCodigoProcesso = new JTextField();
        tfCodigoProcesso.setBounds(30, 175, 250, 25);
        tabCadastrar.add(tfCodigoProcesso);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(320, 175, 140, 30);
        tabCadastrar.add(btnCadastrar);

        taResultadoCadastrar = new JTextArea();
        taResultadoCadastrar.setBounds(30, 230, 580, 150);
        tabCadastrar.add(taResultadoCadastrar);

        JButton btnVoltar1 = new JButton("Voltar");
        btnVoltar1.setBounds(520, 10, 100, 30);
        tabCadastrar.add(btnVoltar1);

        tabbedPane.addTab("Cadastrar", null, tabCadastrar, "Cadastrar nova inscrição");

        JPanel tabAtualizar = new JPanel();
        tabAtualizar.setLayout(null);

        JLabel lblCpfAtt = new JLabel("CPF do Professor:");
        lblCpfAtt.setBounds(30, 30, 200, 25);
        tabAtualizar.add(lblCpfAtt);

        tfCpfAtualizar = new JTextField();
        tfCpfAtualizar.setBounds(30, 55, 250, 25);
        tabAtualizar.add(tfCpfAtualizar);

        JLabel lblNovoCod = new JLabel("Novo Código do Processo:");
        lblNovoCod.setBounds(30, 95, 250, 25);
        tabAtualizar.add(lblNovoCod);

        tfNovoCodigo = new JTextField();
        tfNovoCodigo.setBounds(30, 120, 250, 25);
        tabAtualizar.add(tfNovoCodigo);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(320, 120, 140, 30);
        tabAtualizar.add(btnAtualizar);

        taResultadoAtualizar = new JTextArea();
        taResultadoAtualizar.setBounds(30, 180, 580, 160);
        tabAtualizar.add(taResultadoAtualizar);

        JButton btnVoltar2 = new JButton("Voltar");
        btnVoltar2.setBounds(520, 10, 100, 30);
        tabAtualizar.add(btnVoltar2);

        tabbedPane.addTab("Atualizar", null, tabAtualizar, "Atualizar inscrição");

        JPanel tabConsultar = new JPanel();
        tabConsultar.setLayout(null);

        JLabel lblBuscarDisc = new JLabel("ID da Disciplina:");
        lblBuscarDisc.setBounds(30, 30, 200, 25);
        tabConsultar.add(lblBuscarDisc);

        tfBuscarDisciplina = new JTextField();
        tfBuscarDisciplina.setBounds(30, 55, 250, 25);
        tabConsultar.add(tfBuscarDisciplina);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(320, 55, 140, 30);
        tabConsultar.add(btnBuscar);

        tabelaResultado = new JTable();
        JScrollPane scroll = new JScrollPane(tabelaResultado);
        scroll.setBounds(30, 110, 580, 250);
        tabConsultar.add(scroll);

        JButton btnVoltar3 = new JButton("Voltar");
        btnVoltar3.setBounds(520, 10, 100, 30);
        tabConsultar.add(btnVoltar3);

        tabbedPane.addTab("Consultar", null, tabConsultar, "Consultar inscrições");

        JPanel tabDeletar = new JPanel();
        tabDeletar.setLayout(null);

        JLabel lblCpfDel = new JLabel("CPF do Professor:");
        lblCpfDel.setBounds(30, 30, 200, 25);
        tabDeletar.add(lblCpfDel);

        tfCpfDeletar = new JTextField();
        tfCpfDeletar.setBounds(30, 55, 250, 25);
        tabDeletar.add(tfCpfDeletar);

        JLabel lblProcessoDel = new JLabel("Código do Processo:");
        lblProcessoDel.setBounds(30, 95, 200, 25);
        tabDeletar.add(lblProcessoDel);

        tfProcessoDeletar = new JTextField();
        tfProcessoDeletar.setBounds(30, 120, 250, 25);
        tabDeletar.add(tfProcessoDeletar);

        JButton btnDeletar = new JButton("Deletar");
        btnDeletar.setBounds(320, 120, 140, 30);
        tabDeletar.add(btnDeletar);

        taResultadoDeletar = new JTextArea();
        taResultadoDeletar.setBounds(30, 180, 580, 160);
        tabDeletar.add(taResultadoDeletar);

        JButton btnVoltar4 = new JButton("Voltar");
        btnVoltar4.setBounds(520, 10, 100, 30);
        tabDeletar.add(btnVoltar4);

        tabbedPane.addTab("Deletar", null, tabDeletar, "Deletar inscrição");

        InscricaoController controller = new InscricaoController(
                // CADASTRAR
                tfCpf,
                tfIdDisciplina,
                tfCodigoProcesso,
                taResultadoCadastrar,

                // ATUALIZAR
                tfCpfAtualizar,
                tfNovoCodigo,
                taResultadoAtualizar,

                // CONSULTAR
                tfBuscarDisciplina,
                tabelaResultado,

                // DELETAR
                tfCpfDeletar,
                tfProcessoDeletar,
                taResultadoDeletar
        );

        btnCadastrar.addActionListener(controller);
        btnAtualizar.addActionListener(controller);
        btnBuscar.addActionListener(controller);
        btnDeletar.addActionListener(controller);

        btnVoltar1.addActionListener(e -> voltarParaInicial());
        btnVoltar2.addActionListener(e -> voltarParaInicial());
        btnVoltar3.addActionListener(e -> voltarParaInicial());
        btnVoltar4.addActionListener(e -> voltarParaInicial());
    }

    private void voltarParaInicial() {
	    TelaInicial tela = new TelaInicial();
	    tela.setVisible(true);
	    dispose();
	}
}
