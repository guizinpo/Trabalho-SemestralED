package view;

import java.awt.EventQueue;
import java.awt.Font;

import controller.DisciplinaController;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TelaDisciplina extends JFrame {
//tfCadastrarNome, tfCadastrarID, tfCadastrarIdCurso, tfCadastroDiaSemana, tfCadastrarHoraInicial, tfCadastrarHorasDiarias, tfAtualizarId, tfNovoNome, tfNovoIdCurso, tfNovoDiaSemana, tfNovoHoraInicial, tfNovoHorasDiarias, tfDeletarId, tfBuscar, tableConsultar
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfCadastrarNome;
	private JTextField tfCadastrarID;
	private JTextField tfCadastrarIdCurso;
	private JTextField tfCadastroDiaSemana;
	private JTextField tfCadastrarHoraInicial;
	private JTextField tfCadastrarHorasDiarias;
	private JTextField tfAtualizarId;
	private JTextField tfNovoNome;
	private JTextField tfNovoIdCurso;
	private JTextField tfNovoDiaSemana;
	private JTextField tfNovoHoraInicial;
	private JTextField tfNovoHorasDiarias;
	private JTextField tfDeletarId;
	private JTextField tfBuscar;
	private JTable tableConsultar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDisciplina frame = new TelaDisciplina();
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
	public TelaDisciplina() {
		setTitle("Disciplina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 813, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 0, 800, 431);
		contentPane.add(tabbedPane);
		
		/*
		 * Tab Cadastro
		 */
		JPanel tabCadastroDisciplina = new JPanel();
		tabbedPane.addTab("Cadastro", null, tabCadastroDisciplina, "Cadastro de Disciplina");
		tabCadastroDisciplina.setLayout(null);
		
		//Labels
		JLabel lblCadastrarID = new JLabel("ID:");
		lblCadastrarID.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCadastrarID.setBounds(30, 12, 99, 31);
		tabCadastroDisciplina.add(lblCadastrarID);
		
		JLabel lblCadastrarNome = new JLabel("Nome:");
		lblCadastrarNome.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCadastrarNome.setBounds(30, 86, 129, 33);
		tabCadastroDisciplina.add(lblCadastrarNome);
		
		JLabel lblCadastrarIdCurso = new JLabel("ID do curso:");
		lblCadastrarIdCurso.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCadastrarIdCurso.setBounds(30, 163, 206, 31);
		tabCadastroDisciplina.add(lblCadastrarIdCurso);
		
		JLabel lblCadastrarIDiaSemana = new JLabel("Dia da Semana");
		lblCadastrarIDiaSemana.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCadastrarIDiaSemana.setBounds(409, 12, 196, 31);
		tabCadastroDisciplina.add(lblCadastrarIDiaSemana);
		
		JLabel lblCadastrarIHoraInicial = new JLabel("Hora Inicial:");
		lblCadastrarIHoraInicial.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCadastrarIHoraInicial.setBounds(409, 87, 196, 31);
		tabCadastroDisciplina.add(lblCadastrarIHoraInicial);
		
		JLabel lblCadastrarIHorasDiarias = new JLabel("Horas Diarias:");
		lblCadastrarIHorasDiarias.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCadastrarIHorasDiarias.setBounds(409, 163, 196, 31);
		tabCadastroDisciplina.add(lblCadastrarIHorasDiarias);
		
		//TextFields
		tfCadastrarNome = new JTextField();
		tfCadastrarNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfCadastrarNome.setBounds(30, 120, 292, 31);
		tabCadastroDisciplina.add(tfCadastrarNome);
		tfCadastrarNome.setColumns(10);
		
		tfCadastrarID = new JTextField();
		tfCadastrarID.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfCadastrarID.setBounds(30, 41, 292, 33);
		tabCadastroDisciplina.add(tfCadastrarID);
		tfCadastrarID.setColumns(10);
		
		tfCadastrarIdCurso = new JTextField();
		tfCadastrarIdCurso.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfCadastrarIdCurso.setBounds(30, 196, 292, 31);
		tabCadastroDisciplina.add(tfCadastrarIdCurso);
		tfCadastrarIdCurso.setColumns(10);
		
		tfCadastroDiaSemana = new JTextField();
		tfCadastroDiaSemana.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfCadastroDiaSemana.setColumns(10);
		tfCadastroDiaSemana.setBounds(409, 41, 292, 33);
		tabCadastroDisciplina.add(tfCadastroDiaSemana);
		
		tfCadastrarHoraInicial = new JTextField();
		tfCadastrarHoraInicial.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfCadastrarHoraInicial.setColumns(10);
		tfCadastrarHoraInicial.setBounds(409, 118, 292, 33);
		tabCadastroDisciplina.add(tfCadastrarHoraInicial);
		
		tfCadastrarHorasDiarias = new JTextField();
		tfCadastrarHorasDiarias.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfCadastrarHorasDiarias.setColumns(10);
		tfCadastrarHorasDiarias.setBounds(409, 196, 292, 33);
		tabCadastroDisciplina.add(tfCadastrarHorasDiarias);
		
		//Buttons
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnCadastrar.setBounds(622, 272, 120, 33);
		tabCadastroDisciplina.add(btnCadastrar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltar.setBounds(622, 359, 120, 33);
		tabCadastroDisciplina.add(btnVoltar);
		
		//TextArea
		JTextArea taResultadoCadastrar = new JTextArea();
		taResultadoCadastrar.setFont(new Font("Dialog", Font.PLAIN, 14));
		taResultadoCadastrar.setBounds(30, 272, 553, 120);
		tabCadastroDisciplina.add(taResultadoCadastrar);
		
		/*
		 * Tab Atualizar
		 */
		JPanel tabAtualizarDisciplina = new JPanel();
		tabbedPane.addTab("Atualizar", null, tabAtualizarDisciplina, "Atualizar Disciplina");
		tabAtualizarDisciplina.setLayout(null);
		
		//Labels
		JLabel lblAtualizarNome = new JLabel("Novo Nome:");
		lblAtualizarNome.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAtualizarNome.setBounds(26, 51, 263, 28);
		tabAtualizarDisciplina.add(lblAtualizarNome);
		
		JLabel lblAtualizarIdCurso = new JLabel("Novo ID Curso:");
		lblAtualizarIdCurso.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAtualizarIdCurso.setBounds(26, 129, 264, 28);
		tabAtualizarDisciplina.add(lblAtualizarIdCurso);
		
		JLabel lblAtualizarId = new JLabel("ID Disciplina:");
		lblAtualizarId.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAtualizarId.setBounds(27, 11, 263, 28);
		tabAtualizarDisciplina.add(lblAtualizarId);
		
		JLabel lblAtualizarDiaSemana = new JLabel("Novo Dia da Semana:");
		lblAtualizarDiaSemana.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAtualizarDiaSemana.setBounds(356, 60, 264, 28);
		tabAtualizarDisciplina.add(lblAtualizarDiaSemana);
		
		JLabel lblAtualizarHoraInicial = new JLabel("Novo Hora Inicial:");
		lblAtualizarHoraInicial.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAtualizarHoraInicial.setBounds(356, 129, 264, 28);
		tabAtualizarDisciplina.add(lblAtualizarHoraInicial);
		
		JLabel lblAtualizarHorasDiarias = new JLabel("Novo Horas Diarias");
		lblAtualizarHorasDiarias.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAtualizarHorasDiarias.setBounds(356, 207, 264, 28);
		tabAtualizarDisciplina.add(lblAtualizarHorasDiarias);
		
		//TextFields
		tfAtualizarId = new JTextField();
		tfAtualizarId.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfAtualizarId.setBounds(130, 12, 264, 28);
		tabAtualizarDisciplina.add(tfAtualizarId);
		tfAtualizarId.setColumns(10);
		
		tfNovoNome = new JTextField();
		tfNovoNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfNovoNome.setBounds(26, 90, 264, 28);
		tabAtualizarDisciplina.add(tfNovoNome);
		tfNovoNome.setColumns(10);
				
		tfNovoIdCurso = new JTextField();
		tfNovoIdCurso.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfNovoIdCurso.setBounds(26, 168, 264, 28);
		tabAtualizarDisciplina.add(tfNovoIdCurso);
		tfNovoIdCurso.setColumns(10);
		
		tfNovoDiaSemana = new JTextField();
		tfNovoDiaSemana.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfNovoDiaSemana.setColumns(10);
		tfNovoDiaSemana.setBounds(356, 90, 264, 28);
		tabAtualizarDisciplina.add(tfNovoDiaSemana);
		
		tfNovoHoraInicial = new JTextField();
		tfNovoHoraInicial.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfNovoHoraInicial.setColumns(10);
		tfNovoHoraInicial.setBounds(356, 168, 264, 28);
		tabAtualizarDisciplina.add(tfNovoHoraInicial);
		
		tfNovoHorasDiarias = new JTextField();
		tfNovoHorasDiarias.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfNovoHorasDiarias.setColumns(10);
		tfNovoHorasDiarias.setBounds(356, 246, 264, 28);
		tabAtualizarDisciplina.add(tfNovoHorasDiarias);
		
		//Buttons
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAtualizar.setBounds(576, 291, 198, 33);
		tabAtualizarDisciplina.add(btnAtualizar);
		
		JButton btnPesquisarA = new JButton("Pesquisar");
		btnPesquisarA.setFont(new Font("Dialog", Font.BOLD, 14));
		btnPesquisarA.setBounds(422, 12, 198, 33);
		btnPesquisarA.setActionCommand("PesquisarA");
		tabAtualizarDisciplina.add(btnPesquisarA);
		
		JButton btnVoltar2 = new JButton("Voltar");
		btnVoltar2.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltar2.setBounds(578, 359, 120, 33);
		tabAtualizarDisciplina.add(btnVoltar2);
		
		//TextArea
		JTextArea taResultadoAtualizar = new JTextArea();
		taResultadoAtualizar.setFont(new Font("Dialog", Font.PLAIN, 14));
		taResultadoAtualizar.setBounds(26, 296, 540, 96);
		tabAtualizarDisciplina.add(taResultadoAtualizar);
		
		/*
		 * Tab Consultar
		 */
		JPanel tabConsultarDisciplina = new JPanel();
		tabbedPane.addTab("Consultar", null, tabConsultarDisciplina, "Consultar Disciplinas");
		tabConsultarDisciplina.setLayout(null);
		
		//Label
		JLabel lblBuscar = new JLabel("ID da Disciplina:");
		lblBuscar.setFont(new Font("Dialog", Font.BOLD, 14));
		lblBuscar.setBounds(12, 11, 168, 23);
		tabConsultarDisciplina.add(lblBuscar);
		
		//TextField
		tfBuscar = new JTextField();
		tfBuscar.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfBuscar.setBounds(139, 10, 203, 25);
		tabConsultarDisciplina.add(tfBuscar);
		tfBuscar.setColumns(10);
		
		//Buttons
		JButton btnListar = new JButton("Listar");
		btnListar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnListar.setBounds(29, 86, 121, 29);
		tabConsultarDisciplina.add(btnListar);
		
		JButton btnVoltar3 = new JButton("Voltar");
		btnVoltar3.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltar3.setBounds(634, 357, 120, 33);
		tabConsultarDisciplina.add(btnVoltar3);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnBuscar.setBounds(352, 9, 129, 30);
		tabConsultarDisciplina.add(btnBuscar);
		
		//Tabela
		tableConsultar = new JTable();
		tableConsultar.setBounds(12, 135, 587, 255);
		tabConsultarDisciplina.add(tableConsultar);
		
		/*
		 * Deletar
		 */
		JPanel tabDeleteDisciplina = new JPanel();
		tabbedPane.addTab("Deletar", null, tabDeleteDisciplina, "Deletar Cursos");
		tabDeleteDisciplina.setLayout(null);
		
		//Labels
		JLabel lblDeletarId = new JLabel("Digite o ID da disciplina que deseja deletar:");
		lblDeletarId.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDeletarId.setBounds(24, 59, 486, 30);
		tabDeleteDisciplina.add(lblDeletarId);
		
		//TextField
		tfDeletarId = new JTextField();
		tfDeletarId.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfDeletarId.setBounds(24, 89, 305, 30);
		tabDeleteDisciplina.add(tfDeletarId);
		tfDeletarId.setColumns(10);
		
		//Buttons
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnDeletar.setBounds(24, 198, 151, 36);
		tabDeleteDisciplina.add(btnDeletar);
		
		JButton btnVoltar4 = new JButton("Voltar");
		btnVoltar4.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltar4.setBounds(629, 359, 120, 33);
		tabDeleteDisciplina.add(btnVoltar4);
		
		JButton btnPesquisarD = new JButton("Pesquisar");
		btnPesquisarD.setFont(new Font("Dialog", Font.BOLD, 14));
		btnPesquisarD.setBounds(24, 130, 151, 36);
		btnPesquisarD.setActionCommand("PesquisarD");
		tabDeleteDisciplina.add(btnPesquisarD);
		
		//TextArea
		JTextArea taResultadoDeletar = new JTextArea();
		taResultadoDeletar.setFont(new Font("Dialog", Font.PLAIN, 14));
		taResultadoDeletar.setBounds(24, 281, 536, 111);
		tabDeleteDisciplina.add(taResultadoDeletar);
		
		
		
		DisciplinaController dc = new DisciplinaController(tfCadastrarNome, tfCadastrarID, tfCadastrarIdCurso, tfCadastroDiaSemana,
				tfCadastrarHoraInicial, tfCadastrarHorasDiarias, taResultadoCadastrar, tfAtualizarId, tfNovoNome, tfNovoIdCurso, tfNovoDiaSemana,
				tfNovoHoraInicial, tfNovoHorasDiarias, taResultadoAtualizar, tfDeletarId,taResultadoDeletar, tfBuscar, tableConsultar);
		
		btnCadastrar.addActionListener(dc);
		btnAtualizar.addActionListener(dc);
		btnListar.addActionListener(dc);
		btnBuscar.addActionListener(dc);
		btnDeletar.addActionListener(dc);
		btnPesquisarA.addActionListener(dc);
		btnPesquisarD.addActionListener(dc);
		
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
