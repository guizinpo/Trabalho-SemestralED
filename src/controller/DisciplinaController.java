package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Disciplina;
import model.Inscricao;
import util.arquivoUtil;
import br.edu.fateczl.listaSimples.Lista;
import br.edu.fateczl.fila.Fila;

public class DisciplinaController implements ActionListener{

	private static final String CAMINHODISCIPLINA = "resources/disciplina.csv";
    
    //Cadastrar
    private JTextField tfCadastrarNome;
	private JTextField tfCadastrarID;
	private JTextField tfCadastrarIdCurso;
	private JTextField tfCadastroDiaSemana;
	private JTextField tfCadastrarHoraInicial;
	private JTextField tfCadastrarHorasDiarias;
	private JTextArea taResultadoCadastrar;
	
	//Atualizar
	private JTextField tfAtualizarId;
	private JTextField tfNovoNome;
	private JTextField tfNovoIdCurso;
	private JTextField tfNovoDiaSemana;
	private JTextField tfNovoHoraInicial;
	private JTextField tfNovoHorasDiarias;
	private JTextArea taResultadoAtualizar;
	
	//Pesquisar
	private JTextField tfBuscar;
	private JTable tableConsultar;
	
	//Deletar
	private JTextField tfDeletarId;
	private JTextArea taResultadoDeletar;

    public DisciplinaController(JTextField tfCadastrarNome, JTextField tfCadastrarID, JTextField tfCadastrarIdCurso,
    		JTextField tfCadastroDiaSemana, JTextField tfCadastrarHoraInicial, JTextField tfCadastrarHorasDiarias,
    		JTextArea taResultadoCadastrar, JTextField tfAtualizarId, JTextField tfNovoNome, JTextField tfNovoIdCurso,
    		JTextField tfNovoDiaSemana, JTextField tfNovoHoraInicial, JTextField tfNovoHorasDiarias, JTextArea taResultadoAtualizar,
    		JTextField tfDeletarId, JTextArea taResultadoDeletar, JTextField tfBuscar, JTable tableConsultar){
        super();
        
        this.tfCadastrarNome = tfCadastrarNome;
        this.tfCadastrarID = tfCadastrarID;
        this.tfCadastrarIdCurso = tfCadastrarIdCurso;
        this.tfCadastroDiaSemana = tfCadastroDiaSemana;
        this.tfCadastrarHoraInicial = tfCadastrarHoraInicial;
        this.tfCadastrarHorasDiarias = tfCadastrarHorasDiarias;
        this.taResultadoCadastrar = taResultadoCadastrar;
        
        this.tfAtualizarId = tfAtualizarId;
        this.tfNovoNome = tfNovoNome;
        this.tfNovoIdCurso = tfNovoIdCurso;
        this.tfNovoDiaSemana = tfNovoDiaSemana;
        this.tfNovoHoraInicial = tfNovoHoraInicial;
        this.tfNovoHorasDiarias = tfNovoHorasDiarias;
        this.taResultadoAtualizar = taResultadoAtualizar;
        
        this.tfBuscar = tfBuscar;
        this.tableConsultar = tableConsultar;
        
        this.tfDeletarId = tfDeletarId;
        this.taResultadoDeletar = taResultadoDeletar;
        
        try {
        	carregarDisciplinas();
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
    }

    public Lista<Disciplina> carregarDisciplinas() throws Exception{
    	Lista<Disciplina> disciplinas = new Lista<>();
		Lista<String> linhas = arquivoUtil.lerArquivo(CAMINHODISCIPLINA);
		int tamanho = linhas.size();
		
		for(int i = 0; i < tamanho; i++) {
			String linha = linhas.get(i);
			if(linha != null && !linha.isBlank()) {
				Disciplina dis = new Disciplina();
				String[] info = linha.split(";");
				
				dis.setId(info[0]);
				dis.setNome(info[1]);
				dis.setDiaSemana(info[2]);
				dis.setHoraInicial(info[3]);
				dis.setHorasDiarias(info[4]);
				dis.setidCurso(info[5]);
				
				disciplinas.addLast(dis);
			}
		}
		return disciplinas;
    }

    private void salvarDisciplinas(Lista<Disciplina> disciplinas) throws Exception{
        Lista<String> linhas = new Lista<>();
        int tamanho = disciplinas.size();
        for(int i = 0; i < tamanho; i++) {
        	linhas.addLast(disciplinas.get(i).toString());
        }
        arquivoUtil.gravarArquivo(CAMINHODISCIPLINA, linhas);
    }

    public void adicionarDisciplina() throws Exception{
        String id = tfCadastrarID.getText().trim();
        String nome = tfCadastrarNome.getText().trim();
        String diaSemana = tfCadastroDiaSemana.getText().trim();
        String horaInicial = tfCadastrarHoraInicial.getText().trim();
        String horasDiarias = tfCadastrarHorasDiarias.getText().trim();
        String idCurso = tfCadastrarIdCurso.getText().trim();
        
        if(id.isBlank() || nome.isBlank() || diaSemana.isBlank() || horaInicial.isBlank() || horasDiarias.isBlank() || idCurso.isBlank()) {
        	taResultadoCadastrar.setText("Todos os campos precisam estar preenchidos");
        	return;
        }
        
        Lista<Disciplina> disciplinas = carregarDisciplinas();
        int tamanho = disciplinas.size();
        for(int i = 0; i < tamanho; i++) {
        	Disciplina dis = disciplinas.get(i);
        	if(dis.getId().equals(id) || dis.getNome().equals(nome)) {
        		taResultadoCadastrar.setText("Já existe uma disciplina com este ID ou nome.");
            	return;
        	}
        }
        Disciplina disciplina = new Disciplina(id, nome, diaSemana, horaInicial, horasDiarias, idCurso);
        arquivoUtil.adicionarLinha(CAMINHODISCIPLINA, disciplina.toString());
        taResultadoCadastrar.setText("Adicionado com sucesso.");
    }

    public void removerDisciplina() throws Exception{
    	Lista<Disciplina> disciplinas = carregarDisciplinas();
    	Lista<Disciplina> linhas = new Lista<>();
    	boolean removido = false;
    	String id = tfDeletarId.getText().trim();
    	int tamanho = disciplinas.size();
    	
    	for(int i = 0; i < tamanho; i++) {
    		Disciplina dis = disciplinas.get(i);
    		if(!dis.getId().equals(id)) {
    			linhas.addLast(dis);
    		}
    		else {
    			removido = true;
    		}
    	}
    	if(removido) {
    		taResultadoDeletar.setText("Deletado com sucesso.");
    		salvarDisciplinas(linhas);
    		
    		InscricaoController ic = new InscricaoController();
    		
    		Lista<Inscricao> inscricoes = ic.listar();
    		int insTamanho = inscricoes.size();
    		for(int i = 0; i< insTamanho; i++) {
    			Inscricao ins = inscricoes.get(i);
    			if(ins.getIdDisciplina().equals(id)) {
    				ic.remover(ins.getCpfProfessor(), ins.getCodigoProcesso());
    			}
    		}
    	}
    
    }

    public void atualizarDisciplina() throws Exception{
    	Lista<Disciplina> disciplinas = carregarDisciplinas();
    	Lista<Disciplina> linhas = new Lista<>();
    	boolean atualizado = false;
    	String id = tfAtualizarId.getText().trim();
        String nome = tfNovoNome.getText().trim();
        String diaSemana = tfNovoDiaSemana.getText().trim();
        String horaInicial = tfNovoHoraInicial.getText().trim();
        String horasDiarias = tfNovoHorasDiarias.getText().trim();
        String idCurso = tfNovoIdCurso.getText().trim();
        
        int tamanho = disciplinas.size();

    	for(int i = 0; i < tamanho; i++) {
    		Disciplina dis = disciplinas.get(i);
    		if(dis.getId().equals(id)) {
    			if(!nome.isBlank()) {dis.setNome(nome);}
    			if(!diaSemana.isBlank()) {dis.setDiaSemana(diaSemana);}
    			if(!horaInicial.isBlank()) {dis.setHoraInicial(horaInicial);}
    			if(!horasDiarias.isBlank()) {dis.setHorasDiarias(horasDiarias);}
    			if(!idCurso.isBlank()) {dis.setidCurso(idCurso);}
    			atualizado = true;
    		}
    		linhas.addLast(dis);
    		
    	} 
    	if(atualizado) {
    		taResultadoAtualizar.setText("Disciplina atualizada.");
    		salvarDisciplinas(linhas);
    	}
    }

    public Disciplina pesquisarDisciplina(String operacao) throws Exception{
        String id = "";
        if(operacao == "PesquisarA") {
        	id = tfAtualizarId.getText().trim();
        }
        else if(operacao == "PesquisarD") {
        	id = tfDeletarId.getText().trim();
        }
        else if(operacao == "Buscar") {
        	id = tfBuscar.getText().trim();
        }

        Lista<Disciplina> disciplinas = carregarDisciplinas();
        int tamanho = disciplinas.size();
        
        for(int i = 0; i < tamanho; i++) {
        	Disciplina dis = disciplinas.get(i);
    		if(dis.getId().equals(id)) {
    			return dis;
    		}
    	}
        return new Disciplina();
    }
    
    public void carregarTabela(Fila<String> fila) throws Exception {
    	String[] colunas = {"ID", "Nome", "Dia da Semana", "Hora Inicial", "Horas Diarias", "ID Curso"};
    	int tamanho = fila.size();
    	Object[][] linhas = new Object[tamanho][6];
    	
        for(int i = 0; i < tamanho; i++) {
        	String linha = fila.remove();
    		String[] info = linha.split(";");
    		
    		linhas[i][0] = info[0];
    		linhas[i][1] = info[1];
    		linhas[i][2] = info[2];
    		linhas[i][3] = info[3];
    		linhas[i][4] = info[4];
    		linhas[i][5] = info[5];
    	}
        
        tableConsultar.setModel(
        		new javax.swing.table.DefaultTableModel(linhas, colunas)
        		); 	
    }
    
    public  Fila<String> carregarFila() throws Exception{
    	Lista<Disciplina> disciplinas = carregarDisciplinas();
    	Fila<String> fila = new Fila<>();
    	int tamanho = disciplinas.size();
    	
    	for(int i = 0; i < tamanho; i++) {
    		fila.insert(disciplinas.get(i).toString());
    	}
    	return fila;
    }
    
    @Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Cadastrar")) {
			try {
				adicionarDisciplina();;
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
		}
		if(cmd.equals("Atualizar")) {
			try {
				atualizarDisciplina();;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(cmd.equals("Listar")) {
			try {
		        carregarDisciplinas();
		        Fila<String> fila = carregarFila();
		        
		        carregarTabela(fila);
		    } catch (Exception e1) {
		        e1.printStackTrace();
		    }
		}
		if(cmd.equals("Buscar")) {
			try {
				mostrarDisciplina(cmd);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(cmd.equals("PesquisarA")) {
			try {
				mostrarDisciplina(cmd);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(cmd.equals("PesquisarD")) {
			try {
				mostrarDisciplina(cmd);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(cmd.equals("Deletar")) {
			try {
				removerDisciplina();;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}
    
    public void mostrarDisciplina(String op) throws Exception{
		Disciplina dis = pesquisarDisciplina(op);
		if(op == "Buscar") {
				Fila<String> fila = new Fila<>();
				fila.insert(dis.toString());
				carregarTabela(fila);
		}
		else {
			String msg="";
			msg += "ID: " + dis.getId();
			msg += "\t\tNome: " + dis.getNome();
			msg += "\nDia da Semana: " + dis.getDiaSemana();
			msg += "\tHora Inicial: " + dis.getHoraInicial();
			msg += "\nHoras Diarias: " + dis.getHorasDiarias();
			msg += "\tID do Curso: " + dis.getidCurso();
			
			if(op == "PesquisarA") {
				taResultadoAtualizar.setText(msg);
			}
			else if(op == "PesquisarD") {
				taResultadoDeletar.setText(msg);
			}
		}
	}
    
}
