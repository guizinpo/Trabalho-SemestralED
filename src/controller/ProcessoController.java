package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Disciplina;
import model.Processo;
import util.arquivoUtil;
import br.edu.fateczl.listaSimples.Lista;

public class ProcessoController implements ActionListener {
	
	private static final String CAMINHOPROCESSO = "resources/processo.csv";

	private JTextField tfCadastrarCodigo;
	private JComboBox<String> cbCadastrarDisciplina;
	private JComboBox<String> cbAtualizarCodigo;
	private JTextField tfAtualizarStatus;
	private JTextArea taResultadoCadastro;
	private JTextArea taResultadoAtualizar;
	private JTable tbConsultar;
	
	public ProcessoController() {
		super();
	}
	
	public ProcessoController(JTextField tfCadastrarCodigo, JComboBox<String> cbCadastrarDisciplina,
			JComboBox<String> cbAtualizarCodigo, JTextField tfAtualizarStatus, JTable tbConsultar, JTextArea taResultadoCadastro, JTextArea taResultadoAtualizar) {
		super();
		
		this.tfCadastrarCodigo = tfCadastrarCodigo;
		this.cbCadastrarDisciplina = cbCadastrarDisciplina;
		this.cbAtualizarCodigo = cbAtualizarCodigo;
		this.tfAtualizarStatus = tfAtualizarStatus;
		this.tbConsultar = tbConsultar;
		this.taResultadoCadastro = taResultadoCadastro;
		this.taResultadoAtualizar = taResultadoAtualizar;
		
		try {
        	carregarCbDisciplina();
        	carregarCbCodProcesso();
        }catch(Exception e) {
        	e.printStackTrace();
        }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		try {
			if(cmd.equals("Adicionar")) {
				adicionarProcesso();
				carregarCbCodProcesso();
			}
			if(cmd.equals("Atualizar")) {
				atualizarProcesso();
			}
			if(cmd.equals("Listar")) {
				listarProcessosAbertos();
			}
			if(cmd.equals("Pesquisar")) {
				pesquisarProcesso();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Lista<Processo> carregarProcessos() throws Exception{
		Lista<String> linhas = arquivoUtil.lerArquivo(CAMINHOPROCESSO);
		Lista<Processo> processos = new Lista<>();
		
		int tamanho = linhas.size();
		
		for(int i = 0; i < tamanho; i++) {
			String linha = linhas.get(i);
			if(linha != null && !linha.isBlank()) {
				Processo proc = new Processo();
				String[] info = linha.split(";");
				
				proc.setCodigoProcesso(info[0]);
				proc.setIdDisciplina(info[1]);
				proc.setStatus(info[2]);;
				
				processos.addLast(proc);
			}
		}
		return processos;
	}
	
	private void salvarProcessos(Lista<Processo> processos) throws Exception{
        Lista<String> linhas = new Lista<>();
        int tamanho = processos.size();
        for(int i = 0; i < tamanho; i++) {
        	linhas.addLast(processos.get(i).toString());
        }
        arquivoUtil.gravarArquivo(CAMINHOPROCESSO, linhas);
    }
	
	public void adicionarProcesso() throws Exception{
        String codigoProcesso = tfCadastrarCodigo.getText().trim();
        String idDisciplina = (String) cbCadastrarDisciplina.getSelectedItem();
        String status = "aberto";
                
        if(codigoProcesso.isBlank() || idDisciplina.isBlank()) {
        	taResultadoCadastro.setText("Todos os campos precisam estar preenchidos");
        	return;
        }
        
        Lista<Processo> processos = carregarProcessos();
        int tamanho = processos.size();
        for(int i = 0; i < tamanho; i++) {
        	Processo dis = processos.get(i);
        	if(dis.getCodigoProcesso().equals(codigoProcesso)) {
        		taResultadoCadastro.setText("Já existe um processo com este codigo.");
            	return;
        	}
        }
        Processo processo = new Processo(codigoProcesso, idDisciplina.trim(), status);
        arquivoUtil.adicionarLinha(CAMINHOPROCESSO, processo.toString());
        taResultadoCadastro.setText("Adicionado com sucesso.");
    }
	
	 public void pesquisarProcesso() throws Exception{
	        String codigo = (String) cbAtualizarCodigo.getSelectedItem();

	        Lista<Processo> processos = carregarProcessos();
	        int tamanho = processos.size();
	        
	        for(int i = 0; i < tamanho; i++) {
	        	Processo proc = processos.get(i);
	    		if(proc.getCodigoProcesso().equals(codigo.trim())) {
	    			String msg = "";
	    			msg += "Codigo do Processo: " + proc.getCodigoProcesso();
	    			msg += "\nID da Disciplina: " + proc.getIdDisciplina();
	    			msg += "\nStatus do Processo: " + proc.getStatus();
	    			
	    			taResultadoAtualizar.setText(msg);
	    		}
	    	}
	    }
	 
	 public void atualizarProcesso() throws Exception{
	    	Lista<Processo> processos = carregarProcessos();
	    	Lista<Processo> linhas = new Lista<>();
	    	boolean atualizado = false;
	    	String codigo = (String) cbAtualizarCodigo.getSelectedItem();
	        String status = tfAtualizarStatus.getText().toLowerCase().trim();	       
	        
	        int tamanho = processos.size();

	    	for(int i = 0; i < tamanho; i++) {
	    		Processo proc = processos.get(i);
	    		if(proc.getCodigoProcesso().equals(codigo.trim())) {
	    			if(!status.isBlank()) {
	    				if(status.equals("fechado") || status.equals("aberto")) {
	    					proc.setStatus(status);
		    				atualizado = true;
	    				}
	    				else {
	    					taResultadoAtualizar.setText("O status do processo tem quer 'aberto' ou 'fechado'.");
	    				}
	    			}	
	    		}
	    		linhas.addLast(proc);
	    		
	    	} 
	    	if(atualizado) {
	    		taResultadoAtualizar.setText("Processo atualizado.");
	    		salvarProcessos(linhas);
	    	}
	    }
	 
	 public void listarProcessosAbertos() throws Exception {
		 Lista<Processo> processos = carregarProcessos();
		 Lista<Processo>[] tabelaEspelhamento = new Lista[2];
		 
		 for(int i = 0; i < tabelaEspelhamento.length; i++) {
			 tabelaEspelhamento[i] = new Lista<>();
		 }
		 
		 int procTamanho = processos.size();
		 
		 for(int i = 0; i < procTamanho; i++) {
			 Processo proc = processos.get(i);
			 int pos = hash(proc.getStatus());
			 tabelaEspelhamento[pos].addLast(proc);
		 }
		 
		 montarTabela(tabelaEspelhamento[0]);
	 }
	 
	 private int hash(String status) {
		 if(status.toLowerCase().equals("aberto")) {
			 return 0;
		 }
		 return 1;
	 }
	 
	 public void montarTabela(Lista<Processo> processos) throws Exception {
		 DisciplinaController dc = new DisciplinaController();
		 
		 String[] colunas = {"CodigoProcesso", "IdDisciplina", "nome", "Dia da Semana", "Hora Inicial", "Horas Diarias", "ID Curso"};
		 
		 int tamanho = processos.size();
		 Object[][] linhas = new Object[tamanho][7];
		  
		 for(int i = 0; i < tamanho; i++) {
			 Processo proc = processos.get(i);
			 
			 Disciplina dis = dc.pesquisarDisciplina(proc.getIdDisciplina());
			 
			 String codigo = proc.getCodigoProcesso();
			 String[] info = dis.toString().split(";");
					 
			 linhas[i][0] = codigo;
			 linhas[i][1] = info[0];
			 linhas[i][2] = info[1];
			 linhas[i][3] = info[2];
			 linhas[i][4] = info[3];
			 linhas[i][5] = info[4];
			 linhas[i][6] = info[5];
		 }
		 
		 tbConsultar.setModel(
	        		new javax.swing.table.DefaultTableModel(linhas, colunas)
	        		);
	 }
	 
	 private void carregarCbDisciplina() throws Exception {
		 cbCadastrarDisciplina.removeAllItems();
		 
		DisciplinaController dc = new DisciplinaController();
    	Lista<Disciplina> disciplinas = dc.carregarDisciplinas();
    	
    	int tamanho = disciplinas.size();
    	cbCadastrarDisciplina.addItem("");
    	
    	for(int i = 0; i < tamanho; i++) {
    		Disciplina disciplina = disciplinas.get(i);
    		cbCadastrarDisciplina.addItem(disciplina.getId());
    	}
    }
	    
	    private void carregarCbCodProcesso() throws Exception {
	    	cbAtualizarCodigo.removeAllItems();
	    	
	    	Lista<Processo> processos = carregarProcessos();
	    	
	    	int tamanho = processos.size();
	    	cbAtualizarCodigo.addItem("");
	    	for(int i = 0; i < tamanho; i++) {
	    		Processo processo = processos.get(i);
	    		cbAtualizarCodigo.addItem(processo.getCodigoProcesso());
    	}
    }
}
