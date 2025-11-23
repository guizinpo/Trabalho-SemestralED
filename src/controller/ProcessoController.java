package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextField;

import br.edu.fateczl.fila.Fila;

public class ProcessoController implements ActionListener {
	
	private static final String CAMINHOPROCESSO = "resources/processo.csv";

	private JTextField tfCadastrarCodigo;
	private JTextField tfCadastrarDisciplina;
	private JTextField tfAtualizarCodigo;
	private JTextField tfAtualizarStatus;
	private JTable tbConsultar;
	
	public ProcessoController(JTextField tfCadastrarCodigo, JTextField tfCadastrarDisciplina,
			JTextField tfAtualizarCodigo, JTextField tfAtualizarStatus, JTable tbConsultar) {
		super();
		
		this.tfCadastrarCodigo = tfCadastrarCodigo;
		this.tfCadastrarDisciplina = tfCadastrarDisciplina;
		this.tfAtualizarCodigo = tfAtualizarCodigo;
		this.tfAtualizarStatus = tfAtualizarStatus;
		this.tbConsultar = tbConsultar;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Cadastrar")) {
			try {
				
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
		}
		if(cmd.equals("Atualizar")) {
			try {
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(cmd.equals("Listar")) {
			try {

		    } catch (Exception e1) {
		        e1.printStackTrace();
		    }
		}
		if(cmd.equals("Pesquisar")) {
			try {

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
