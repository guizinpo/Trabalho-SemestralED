package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CursoController;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTable;

public class TelaCurso extends JFrame {

	private static final long serialVersionUID = -5795366291746726261L;
	private JPanel contentPane;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JTextField tfCadastrarNome;
	private JTextField tfCadastrarID;
	private JTextField tfCadastrarArea;
	private JComboBox<String> cbNomeDesejavel;
	private JTextField tfNovoNome;
	private JTextField tfNovaArea;
	private JComboBox<String> cbNomeDesejavel2;
	private JComboBox<String> cbBuscar;
	
	private JTable tableConsultar;

	public TelaCurso() {
		setTitle("Cursos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tabbedPane.setBounds(12, 0, 616, 431);
		contentPane.add(tabbedPane);
		
		JPanel tabCadastroCurso = new JPanel();
		tabbedPane.addTab("Cadastro", null, tabCadastroCurso, "Cadastro de Cursos");
		tabCadastroCurso.setLayout(null);
		
		JLabel lblCadastrarID = new JLabel("ID:");
		lblCadastrarID.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCadastrarID.setBounds(30, 12, 99, 31);
		tabCadastroCurso.add(lblCadastrarID);
		
		JLabel lblCadastrarNome = new JLabel("Nome:");
		lblCadastrarNome.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCadastrarNome.setBounds(30, 86, 129, 33);
		tabCadastroCurso.add(lblCadastrarNome);
		
		JLabel lblCadastrarArea = new JLabel("Area de Atuação:");
		lblCadastrarArea.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCadastrarArea.setBounds(30, 163, 206, 31);
		tabCadastroCurso.add(lblCadastrarArea);
		
		tfCadastrarNome = new JTextField();
		tfCadastrarNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfCadastrarNome.setBounds(30, 120, 292, 31);
		tabCadastroCurso.add(tfCadastrarNome);
		tfCadastrarNome.setColumns(10);
		
		tfCadastrarID = new JTextField();
		tfCadastrarID.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfCadastrarID.setBounds(30, 41, 292, 33);
		tabCadastroCurso.add(tfCadastrarID);
		tfCadastrarID.setColumns(10);
		
		tfCadastrarArea = new JTextField();
		tfCadastrarArea.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfCadastrarArea.setBounds(30, 196, 292, 31);
		tabCadastroCurso.add(tfCadastrarArea);
		tfCadastrarArea.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnCadastrar.setBounds(412, 194, 120, 33);
		tabCadastroCurso.add(btnCadastrar);
		
		JTextArea taResultadoCadastrar = new JTextArea();
		taResultadoCadastrar.setFont(new Font("Dialog", Font.PLAIN, 14));
		taResultadoCadastrar.setBounds(30, 250, 552, 120);
		tabCadastroCurso.add(taResultadoCadastrar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltar.setBounds(470, 10, 120, 33);
		tabCadastroCurso.add(btnVoltar);
		
		JPanel tabAtualizarCurso = new JPanel();
		tabbedPane.addTab("Atualizar", null, tabAtualizarCurso, "Atualizar Cursos");
		tabAtualizarCurso.setLayout(null);
		
		JLabel lblNomeDesejavel = new JLabel("Nome do curso que deseja atualizar:");
		lblNomeDesejavel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNomeDesejavel.setBounds(26, 53, 311, 28);
		tabAtualizarCurso.add(lblNomeDesejavel);
		
		cbNomeDesejavel = new JComboBox<>();
		cbNomeDesejavel.setBounds(26, 80, 264, 28);
		tabAtualizarCurso.add(cbNomeDesejavel);
		cbNomeDesejavel.setEditable(true);
		
		JLabel lblNovoNome = new JLabel("Novo Nome:");
		lblNovoNome.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNovoNome.setBounds(27, 120, 263, 28);
		tabAtualizarCurso.add(lblNovoNome);
		
		tfNovoNome = new JTextField();
		tfNovoNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfNovoNome.setBounds(26, 147, 498, 28);
		tabAtualizarCurso.add(tfNovoNome);
		tfNovoNome.setColumns(10);
		
		JLabel lblNovaArea = new JLabel("Nova Area de Atuação:");
		lblNovaArea.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNovaArea.setBounds(25, 187, 418, 28);
		tabAtualizarCurso.add(lblNovaArea);
		
		tfNovaArea = new JTextField();
		tfNovaArea.setBounds(26, 217, 498, 28);
		tabAtualizarCurso.add(tfNovaArea);
		tfNovaArea.setColumns(10);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAtualizar.setBounds(326, 78, 198, 33);
		tabAtualizarCurso.add(btnAtualizar);
		
		JTextArea taResultadoAtualizar = new JTextArea();
		taResultadoAtualizar.setFont(new Font("Dialog", Font.PLAIN, 14));
		taResultadoAtualizar.setBounds(26, 275, 540, 96);
		tabAtualizarCurso.add(taResultadoAtualizar);
		
		JButton btnVoltar2 = new JButton("Voltar");
		btnVoltar2.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltar2.setBounds(470, 10, 120, 33);
		tabAtualizarCurso.add(btnVoltar2);
		
		JPanel tabConsultarCurso = new JPanel();
		tabbedPane.addTab("Consultar", null, tabConsultarCurso, "Consultar Cursos");
		tabConsultarCurso.setLayout(null);
		
		JLabel lblListar = new JLabel("Listar todos os cursos:");
		lblListar.setFont(new Font("Dialog", Font.BOLD, 14));
		lblListar.setBounds(29, 56, 216, 30);
		tabConsultarCurso.add(lblListar);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnListar.setBounds(29, 86, 121, 29);
		tabConsultarCurso.add(btnListar);
		
		JButton btnVoltar3 = new JButton("Voltar");
		btnVoltar3.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltar3.setBounds(470, 10, 120, 33);
		tabConsultarCurso.add(btnVoltar3);
		
		JLabel lblBuscar = new JLabel("Nome do Curso:");
		lblBuscar.setFont(new Font("Dialog", Font.BOLD, 14));
		lblBuscar.setBounds(244, 60, 168, 23);
		tabConsultarCurso.add(lblBuscar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(243, 85, 129, 30);
		tabConsultarCurso.add(btnBuscar);;
		
		cbBuscar = new JComboBox<>();
		cbBuscar.setBounds(379, 59, 203, 25);
		tabConsultarCurso.add(cbBuscar);
		cbBuscar.setEditable(true);
		
		tableConsultar = new JTable();
		tableConsultar.setBounds(12, 135, 587, 255);
		tabConsultarCurso.add(tableConsultar);
		
		JPanel tabDeleteCurso = new JPanel();
		tabbedPane.addTab("Deletar", null, tabDeleteCurso, "Deletar Cursos");
		tabDeleteCurso.setLayout(null);
		
		JLabel lblNomeDesejavel2 = new JLabel("Digite o nome do curso que deseja deletar:");
		lblNomeDesejavel2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNomeDesejavel2.setBounds(24, 59, 486, 30);
		tabDeleteCurso.add(lblNomeDesejavel2);
		
		cbNomeDesejavel2 = new JComboBox<>();
		cbNomeDesejavel2.setBounds(24, 89, 536, 30);
		tabDeleteCurso.add(cbNomeDesejavel2);
		cbNomeDesejavel2.setEditable(true);
		
		JTextArea taResultadoDeletar = new JTextArea();
		taResultadoDeletar.setFont(new Font("Dialog", Font.PLAIN, 14));
		taResultadoDeletar.setBounds(24, 240, 536, 111);
		tabDeleteCurso.add(taResultadoDeletar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnDeletar.setBounds(26, 131, 151, 36);
		tabDeleteCurso.add(btnDeletar);
		
		JButton btnVoltar4 = new JButton("Voltar");
		btnVoltar4.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltar4.setBounds(470, 10, 120, 33);
		tabDeleteCurso.add(btnVoltar4);
		
		CursoController vCurso = new CursoController(tfCadastrarID, tfCadastrarNome, tfCadastrarArea, taResultadoCadastrar, cbNomeDesejavel, 
				tfNovoNome, tfNovaArea, taResultadoAtualizar, cbBuscar, tableConsultar, cbNomeDesejavel2, taResultadoDeletar);
		
		btnCadastrar.addActionListener(vCurso);
		btnAtualizar.addActionListener(vCurso);
		btnListar.addActionListener(vCurso);
		btnBuscar.addActionListener(vCurso);
		btnDeletar.addActionListener(vCurso);
		
		btnVoltar.addActionListener(e -> voltarParaInicial());
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
