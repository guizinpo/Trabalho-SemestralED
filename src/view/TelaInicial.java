package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Font;

public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1638617809677772L;
	private JPanel contentPane;

	public TelaInicial() {
		setTitle("Tela Inicial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(24, 0, 604, 431);
		contentPane.add(tabbedPane);
		
		JPanel tabTelaInicial = new JPanel();
		tabbedPane.addTab("Inicio", null, tabTelaInicial, "Tela inicial para acessar as outras telas");
		tabTelaInicial.setLayout(null);
		
		JButton btnProfessores = new JButton("Professores");
		btnProfessores.setFont(new Font("Dialog", Font.BOLD, 20));
		btnProfessores.setBounds(172, 277, 238, 66);
		tabTelaInicial.add(btnProfessores);
		
		JButton btnCursos = new JButton("Cursos");
		btnCursos.setFont(new Font("Dialog", Font.BOLD, 20));
		btnCursos.setBounds(172, 121, 238, 66);
		tabTelaInicial.add(btnCursos);
		
		JButton btnInscricoes = new JButton("Inscrições");
		btnInscricoes.setFont(new Font("Dialog", Font.BOLD, 20));
		btnInscricoes.setBounds(172, 43, 238, 66);
		tabTelaInicial.add(btnInscricoes);
		
		JButton btnDisciplinas = new JButton("Disciplinas");
		btnDisciplinas.setFont(new Font("Dialog", Font.BOLD, 20));
		btnDisciplinas.setBounds(172, 199, 238, 66);
		tabTelaInicial.add(btnDisciplinas);

		btnCursos.addActionListener(e -> irCurso());
		btnInscricoes.addActionListener(e -> irInscricao());
		btnProfessores.addActionListener(e -> irProfessores());
		btnDisciplinas.addActionListener(e -> irDisciplina());
		
	}
	
	private void irCurso() {
	    TelaCurso tela = new TelaCurso();
	    tela.setVisible(true);
	    dispose();
	}
	
	private void irInscricao() {
	    TelaInscricao tela = new TelaInscricao();
	    tela.setVisible(true);
	    dispose();
	}
	
	private void irProfessores() {
		TelaProfessor tela = new TelaProfessor();

	private void irDisciplina() {
	    TelaDisciplina tela = new TelaDisciplina();
	    tela.setVisible(true);
	    dispose();
	}
}
