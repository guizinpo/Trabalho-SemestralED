package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.ProfessorController;

public class TelaProfessor extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTabbedPane tabbedPane;

    // Cadastrar
    private JTextField tfCpfCadastrar;
    private JTextField tfNomeCadastrar;
    private JTextField tfAreaCadastrar;
    private JTextArea taResultadoCadastrar;

    // Atualizar
    private JComboBox<String> cbCpfAtualizar;
    private JTextField tfNovoNome;
    private JTextField tfNovaArea;
    private JTextArea taResultadoAtualizar;

    // Consultar
    private JComboBox<String> cbBuscarCpf;
    private JTable tabelaResultado;
    
    // Listar
    private JTable tabelaListar;

    // Deletar
    private JComboBox<String> cbCpfDeletar;
    private JTextArea taResultadoDeletar;

    public TelaProfessor() {

        setTitle("Professores");
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

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(30, 30, 200, 25);
        tabCadastrar.add(lblCpf);

        tfCpfCadastrar = new JTextField();
        tfCpfCadastrar.setBounds(30, 55, 250, 25);
        tabCadastrar.add(tfCpfCadastrar);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 90, 200, 25);
        tabCadastrar.add(lblNome);

        tfNomeCadastrar = new JTextField();
        tfNomeCadastrar.setBounds(30, 115, 250, 25);
        tabCadastrar.add(tfNomeCadastrar);

        JLabel lblArea = new JLabel("Área:");
        lblArea.setBounds(30, 150, 200, 25);
        tabCadastrar.add(lblArea);

        tfAreaCadastrar = new JTextField();
        tfAreaCadastrar.setBounds(30, 175, 250, 25);
        tabCadastrar.add(tfAreaCadastrar);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(320, 175, 140, 30);
        tabCadastrar.add(btnCadastrar);

        taResultadoCadastrar = new JTextArea();
        taResultadoCadastrar.setBounds(30, 230, 580, 150);
        tabCadastrar.add(taResultadoCadastrar);

        JButton btnVoltar1 = new JButton("Voltar");
        btnVoltar1.setBounds(520, 10, 100, 30);
        tabCadastrar.add(btnVoltar1);

        tabbedPane.addTab("Cadastrar", null, tabCadastrar, "Cadastrar professor");

        JPanel tabAtualizar = new JPanel();
        tabAtualizar.setLayout(null);

        JLabel lblCpfAtt = new JLabel("CPF do Professor:");
        lblCpfAtt.setBounds(30, 30, 200, 25);
        tabAtualizar.add(lblCpfAtt);

        cbCpfAtualizar = new JComboBox<>();
        cbCpfAtualizar.setBounds(30, 55, 250, 25);
        cbCpfAtualizar.setEditable(true);
        tabAtualizar.add(cbCpfAtualizar);

        JLabel lblNovoNome = new JLabel("Novo Nome:");
        lblNovoNome.setBounds(30, 95, 250, 25);
        tabAtualizar.add(lblNovoNome);

        tfNovoNome = new JTextField();
        tfNovoNome.setBounds(30, 120, 250, 25);
        tabAtualizar.add(tfNovoNome);

        JLabel lblNovaArea = new JLabel("Nova Área:");
        lblNovaArea.setBounds(30, 155, 250, 25);
        tabAtualizar.add(lblNovaArea);

        tfNovaArea = new JTextField();
        tfNovaArea.setBounds(30, 180, 250, 25);
        tabAtualizar.add(tfNovaArea);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(320, 180, 140, 30);
        tabAtualizar.add(btnAtualizar);

        taResultadoAtualizar = new JTextArea();
        taResultadoAtualizar.setBounds(30, 230, 580, 150);
        tabAtualizar.add(taResultadoAtualizar);

        JButton btnVoltar2 = new JButton("Voltar");
        btnVoltar2.setBounds(520, 10, 100, 30);
        tabAtualizar.add(btnVoltar2);

        tabbedPane.addTab("Atualizar", null, tabAtualizar, "Atualizar professor");

        JPanel tabConsultar = new JPanel();
        tabConsultar.setLayout(null);

        JLabel lblBuscar = new JLabel("CPF:");
        lblBuscar.setBounds(30, 30, 200, 25);
        tabConsultar.add(lblBuscar);

        cbBuscarCpf = new JComboBox<>();
        cbBuscarCpf.setBounds(30, 55, 250, 25);
        cbBuscarCpf.setEditable(true);
        tabConsultar.add(cbBuscarCpf);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(320, 55, 140, 30);
        tabConsultar.add(btnBuscar);

        tabelaResultado = new JTable();
        JScrollPane scrollConsultar = new JScrollPane(tabelaResultado);
        scrollConsultar.setBounds(30, 110, 580, 250);
        tabConsultar.add(scrollConsultar);

        JButton btnVoltar3 = new JButton("Voltar");
        btnVoltar3.setBounds(520, 10, 100, 30);
        tabConsultar.add(btnVoltar3);

        tabbedPane.addTab("Consultar", null, tabConsultar, "Consultar professor");

        JPanel tabDeletar = new JPanel();
        tabDeletar.setLayout(null);

        JLabel lblCpfDel = new JLabel("CPF do Professor:");
        lblCpfDel.setBounds(30, 30, 250, 25);
        tabDeletar.add(lblCpfDel);

        cbCpfDeletar = new JComboBox<>();
        cbCpfDeletar.setBounds(30, 55, 250, 25);
        cbCpfDeletar.setEditable(true);
        tabDeletar.add(cbCpfDeletar);

        JButton btnDeletar = new JButton("Deletar");
        btnDeletar.setBounds(320, 55, 140, 30);
        tabDeletar.add(btnDeletar);

        taResultadoDeletar = new JTextArea();
        taResultadoDeletar.setBounds(30, 120, 580, 160);
        tabDeletar.add(taResultadoDeletar);

        JButton btnVoltar4 = new JButton("Voltar");
        btnVoltar4.setBounds(520, 10, 100, 30);
        tabDeletar.add(btnVoltar4);

        tabbedPane.addTab("Deletar", null, tabDeletar, "Deletar professor");

        ProfessorController controller = new ProfessorController(
                // CADASTRAR
                tfCpfCadastrar,
                tfNomeCadastrar,
                tfAreaCadastrar,
                taResultadoCadastrar,

                // ATUALIZAR
                cbCpfAtualizar,
                tfNovoNome,
                tfNovaArea,
                taResultadoAtualizar,

                // CONSULTAR
                cbBuscarCpf,
                tabelaResultado,
                
                // LISTAR
                tabelaListar,

                // DELETAR
                cbCpfDeletar,
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

    private void deletar() {
        try {
            controller.remover((String) cbCpfDeletar.getSelectedItem());
            taDeletar.setText("Professor removido com sucesso!");
            carregarCbProfessor();
        } catch (Exception e) {
            taDeletar.setText("Erro: " + e.getMessage());
        }
    }
    
    public void voltarParaInicial() {
		TelaInicial tela = new TelaInicial();
		tela.setVisible(true);
		dispose();
	}
    
    private void carregarCbProfessor() throws Exception {
    	cbBuscarCpf.removeAllItems();
    	cbCpfAtualizar.removeAllItems();
    	cbCpfDeletar.removeAllItems();
    	
    	Lista<Professor> professores = controller.listar();
    	
    	int tamanho = professores.size();
    	cbBuscarCpf.addItem("");
    	cbCpfAtualizar.addItem("");
    	cbCpfDeletar.addItem("");
    	for(int i = 0; i < tamanho; i++) {
    		Professor professor = professores.get(i);
    		cbBuscarCpf.addItem(professor.getCpf());
        	cbCpfAtualizar.addItem(professor.getCpf());
        	cbCpfDeletar.addItem(professor.getCpf());
    	}
    }
}
