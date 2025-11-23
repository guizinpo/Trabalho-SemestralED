package view;

import java.awt.EventQueue;
import java.awt.Font;

import controller.ProcessoController;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTable;

public class TelaProcesso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfCadastrarCodigo;
	private JTextField tfCadastrarDisciplina;
	private JTextField tfAtualizarCodigo;
	private JTextField tfAtualizarStatus;
	private JTable tbConsultar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaProcesso frame = new TelaProcesso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaProcesso() {
		setTitle("Processo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 0, 614, 409);
		contentPane.add(tabbedPane);
		
		/* 
		 * Tab Cadastro
		 */
		JPanel tabCadastroProcesso = new JPanel();
		tabbedPane.addTab("Cadastro", null, tabCadastroProcesso, "Cadastro Processo");
		tabCadastroProcesso.setLayout(null);
		
		//Labels
		JLabel lblCadastrarCodigo = new JLabel("Código do Processo:");
		lblCadastrarCodigo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCadastrarCodigo.setBounds(10, 12, 292, 31);
		tabCadastroProcesso.add(lblCadastrarCodigo);
		
		JLabel lblCadastrarDisciplina = new JLabel("ID da Disciplina:");
		lblCadastrarDisciplina.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCadastrarDisciplina.setBounds(10, 96, 292, 31);
		tabCadastroProcesso.add(lblCadastrarDisciplina);
		
		//TextFields
		tfCadastrarCodigo = new JTextField();
		tfCadastrarCodigo.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfCadastrarCodigo.setBounds(10, 54, 292, 31);
		tabCadastroProcesso.add(tfCadastrarCodigo);
		tfCadastrarCodigo.setColumns(10);
		
		tfCadastrarDisciplina = new JTextField();
		tfCadastrarDisciplina.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfCadastrarDisciplina.setColumns(10);
		tfCadastrarDisciplina.setBounds(10, 138, 292, 31);
		tabCadastroProcesso.add(tfCadastrarDisciplina);
		
		//Buttons
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(10, 180, 120, 33);
		btnAdicionar.setFont(new Font("Dialog", Font.BOLD, 14));
		tabCadastroProcesso.add(btnAdicionar);
		
		JButton btnVoltar1 = new JButton("Voltar");
		btnVoltar1.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltar1.setBounds(466, 337, 120, 33);
		tabCadastroProcesso.add(btnVoltar1);
		
		//TextArea
		JTextArea taResultadoCadastro = new JTextArea();
		taResultadoCadastro.setBounds(10, 224, 436, 146);
		tabCadastroProcesso.add(taResultadoCadastro);

		/*
		 * Tab Atualizar
		 */
		JPanel tabAtualizarProcesso = new JPanel();
		tabbedPane.addTab("Atualizar", null, tabAtualizarProcesso, "Atualizar Processo");
		tabAtualizarProcesso.setLayout(null);
		
		//Label
		JLabel lblAtualizarCodigo = new JLabel("Código do Processo:");
		lblAtualizarCodigo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAtualizarCodigo.setBounds(10, 11, 292, 31);
		tabAtualizarProcesso.add(lblAtualizarCodigo);
		
		JLabel lblStatusDoProcesso = new JLabel("Status do Processo:");
		lblStatusDoProcesso.setFont(new Font("Dialog", Font.BOLD, 14));
		lblStatusDoProcesso.setBounds(10, 95, 292, 31);
		tabAtualizarProcesso.add(lblStatusDoProcesso);
		
		//TextFields
		tfAtualizarCodigo = new JTextField();
		tfAtualizarCodigo.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfAtualizarCodigo.setColumns(10);
		tfAtualizarCodigo.setBounds(10, 53, 292, 31);
		tabAtualizarProcesso.add(tfAtualizarCodigo);
		
		tfAtualizarStatus = new JTextField();
		tfAtualizarStatus.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfAtualizarStatus.setColumns(10);
		tfAtualizarStatus.setBounds(10, 137, 292, 31);
		tabAtualizarProcesso.add(tfAtualizarStatus);

		//Buttons
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAtualizar.setBounds(10, 179, 120, 33);
		tabAtualizarProcesso.add(btnAtualizar);
		
		JButton btnVoltar2 = new JButton("Voltar");
		btnVoltar2.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltar2.setBounds(460, 337, 120, 33);
		tabAtualizarProcesso.add(btnVoltar2);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnPesquisar.setBounds(342, 53, 120, 33);
		tabAtualizarProcesso.add(btnPesquisar);

		//TextArea
		JTextArea taResultadoAtualizar = new JTextArea();
		taResultadoAtualizar.setBounds(10, 224, 416, 146);
		tabAtualizarProcesso.add(taResultadoAtualizar);
		
		/*
		 * Tab Consultar
		 */
		JPanel tabConsultarProcesso = new JPanel();
		tabbedPane.addTab("Consultar", null, tabConsultarProcesso, "Consultar Processo");
		tabConsultarProcesso.setLayout(null);
		
		//Labels
		JLabel lblConsultar = new JLabel("Consultar Processos Abertos");
		lblConsultar.setFont(new Font("Dialog", Font.BOLD, 14));
		lblConsultar.setBounds(10, 11, 292, 31);
		tabConsultarProcesso.add(lblConsultar);
		
		//Buttos
		JButton btnVoltar3 = new JButton("Voltar");
		btnVoltar3.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltar3.setBounds(464, 337, 120, 33);
		tabConsultarProcesso.add(btnVoltar3);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnListar.setBounds(10, 45, 120, 33);
		tabConsultarProcesso.add(btnListar);
		
		//Table
		tbConsultar = new JTable();
		tbConsultar.setBounds(10, 87, 589, 239);
		tabConsultarProcesso.add(tbConsultar);
		
		
		/*
		 * Ações
		 */
		ProcessoController pc = new ProcessoController(tfCadastrarCodigo, tfCadastrarDisciplina, tfAtualizarCodigo, tfAtualizarStatus, tbConsultar);
		
		btnAdicionar.addActionListener(pc);
		btnPesquisar.addActionListener(pc);
		btnAtualizar.addActionListener(pc);
		btnListar.addActionListener(pc);
		
		btnVoltar1.addActionListener(e -> voltarParaInicial());
		btnVoltar2.addActionListener(e -> voltarParaInicial());
		btnVoltar3.addActionListener(e -> voltarParaInicial());
	}
	
	public void voltarParaInicial() {
		TelaInicial tela = new TelaInicial();
		tela.setVisible(true);
		dispose();
	}
}
