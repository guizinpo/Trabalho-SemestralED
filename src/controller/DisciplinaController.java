package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import model.Disciplina;
import model.Inscricao;
import model.Curso;
import util.arquivoUtil;
import br.edu.fateczl.listaSimples.Lista;
import br.edu.fateczl.fila.Fila;

public class DisciplinaController implements ActionListener{

	private static final String CAMINHODISCIPLINA = "resources/disciplina.csv";
    
    //Cadastrar
    private JTextField tfCadastrarNome;
	private JTextField tfCadastrarID;
	//private JTextField tfCadastrarIdCurso;
	private JTextField tfCadastroDiaSemana;
	private JTextField tfCadastrarHoraInicial;
	private JTextField tfCadastrarHorasDiarias;
	private JTextArea taResultadoCadastrar;
	private JComboBox<String> cbCadastroCurso;
	
	//Atualizar
	private JComboBox<String> cbAtualizarId;
	private JComboBox<String> cbNovoIdCurso;
	private JTextField tfNovoNome;
	private JTextField tfNovoDiaSemana;
	private JTextField tfNovoHoraInicial;
	private JTextField tfNovoHorasDiarias;
	private JTextArea taResultadoAtualizar;
	
	//Pesquisar
	private JComboBox<String> cbBuscar;
	private JTable tableConsultar;
	
	//Deletar
	private JComboBox<String> cbDeletarId;
	private JTextArea taResultadoDeletar;

	public DisciplinaController() {
		super();
	}
	
    public DisciplinaController(JTextField tfCadastrarNome, JTextField tfCadastrarID, JComboBox<String> cbCadastroCurso,
    		JTextField tfCadastroDiaSemana, JTextField tfCadastrarHoraInicial, JTextField tfCadastrarHorasDiarias,
    		JTextArea taResultadoCadastrar, JComboBox<String> cbAtualizarId, JTextField tfNovoNome, JComboBox<String> cbNovoIdCurso,
    		JTextField tfNovoDiaSemana, JTextField tfNovoHoraInicial, JTextField tfNovoHorasDiarias, JTextArea taResultadoAtualizar,
    		JComboBox<String> cbDeletarId, JTextArea taResultadoDeletar, JComboBox<String> cbBuscar, JTable tableConsultar){
        
        this.tfCadastrarNome = tfCadastrarNome;
        this.tfCadastrarID = tfCadastrarID;
        this.cbCadastroCurso = cbCadastroCurso;
        this.tfCadastroDiaSemana = tfCadastroDiaSemana;
        this.tfCadastrarHoraInicial = tfCadastrarHoraInicial;
        this.tfCadastrarHorasDiarias = tfCadastrarHorasDiarias;
        this.taResultadoCadastrar = taResultadoCadastrar;
        
        this.cbAtualizarId = cbAtualizarId;
        this.tfNovoNome = tfNovoNome;
        this.cbNovoIdCurso = cbNovoIdCurso;
        this.tfNovoDiaSemana = tfNovoDiaSemana;
        this.tfNovoHoraInicial = tfNovoHoraInicial;
        this.tfNovoHorasDiarias = tfNovoHorasDiarias;
        this.taResultadoAtualizar = taResultadoAtualizar;
        
        this.cbBuscar = cbBuscar;
        this.tableConsultar = tableConsultar;
        
        this.cbDeletarId = cbDeletarId;
        this.taResultadoDeletar = taResultadoDeletar;
        
        try {
        	carregarCbCurso();
        	carregarCbDisciplina();
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
        String idCurso = (String) cbCadastroCurso.getSelectedItem();
        
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
        Disciplina disciplina = new Disciplina(id, nome, diaSemana, horaInicial, horasDiarias, idCurso.trim());
        arquivoUtil.adicionarLinha(CAMINHODISCIPLINA, disciplina.toString());
        taResultadoCadastrar.setText("Adicionado com sucesso.");
    }

    public void removerDisciplina() throws Exception{
    	Lista<Disciplina> disciplinas = carregarDisciplinas();
    	Lista<Disciplina> linhas = new Lista<>();
    	boolean removido = false;
    	String id = (String) cbDeletarId.getSelectedItem();
    	int tamanho = disciplinas.size();
    	
    	for(int i = 0; i < tamanho; i++) {
    		Disciplina dis = disciplinas.get(i);
    		if(!dis.getId().equals(id.trim())) {
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
    			if(ins.getIdDisciplina().equals(id.trim())) {
    				ic.remover(ins.getCpfProfessor(), ins.getCodigoProcesso());
    			}
    		}
    	}
    
    }

    public void atualizarDisciplina() throws Exception{
    	Lista<Disciplina> disciplinas = carregarDisciplinas();
    	Lista<Disciplina> linhas = new Lista<>();
    	boolean atualizado = false;
    	String id = (String)cbAtualizarId.getSelectedItem();
        String nome = tfNovoNome.getText().trim();
        String diaSemana = tfNovoDiaSemana.getText().trim();
        String horaInicial = tfNovoHoraInicial.getText().trim();
        String horasDiarias = tfNovoHorasDiarias.getText().trim();
        String idCurso = (String) cbNovoIdCurso.getSelectedItem();
        
        int tamanho = disciplinas.size();

    	for(int i = 0; i < tamanho; i++) {
    		Disciplina dis = disciplinas.get(i);
    		if(dis.getId().equals(id.trim())) {
    			if(!nome.isBlank()) {dis.setNome(nome);}
    			if(!diaSemana.isBlank()) {dis.setDiaSemana(diaSemana);}
    			if(!horaInicial.isBlank()) {dis.setHoraInicial(horaInicial);}
    			if(!horasDiarias.isBlank()) {dis.setHorasDiarias(horasDiarias);}
    			if(!idCurso.isBlank()) {dis.setidCurso(idCurso.trim());}
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
        	id = (String) cbAtualizarId.getSelectedItem();
        }
        else if(operacao == "PesquisarD") {
        	id = (String) cbDeletarId.getSelectedItem();
        }
        else if(operacao == "Buscar") {
        	id = (String) cbBuscar.getSelectedItem();
        }
        else {
        	id = operacao;
        }

        Lista<Disciplina> disciplinas = carregarDisciplinas();
        int tamanho = disciplinas.size();
        
        for(int i = 0; i < tamanho; i++) {
        	Disciplina dis = disciplinas.get(i);
    		if(dis.getId().equals(id.trim())) {
    			return dis;
    		}
    	}
        return new Disciplina();
    }
    
    public void carregarTabela(Fila<String> fila) throws Exception {
    	String[] colunas = {"ID", "Nome", "DiaSemana", "HoraInicial", "HorasDiarias", "IdCurso"};
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
    
    private void carregarCbDisciplina() throws Exception {
    	Lista<Disciplina> disciplinas = carregarDisciplinas();
    	
    	int tamanho = disciplinas.size();
    	cbAtualizarId.addItem("");
    	cbBuscar.addItem("");
    	cbDeletarId.addItem("");
    	for(int i = 0; i < tamanho; i++) {
    		Disciplina disciplina = disciplinas.get(i);
    		cbAtualizarId.addItem(disciplina.getId());
    		cbBuscar.addItem(disciplina.getId());
    		cbDeletarId.addItem(disciplina.getId());
    	}
    }
    
    private void carregarCbCurso() throws Exception {
    	CursoController cc = new CursoController();
    	Lista<Curso> cursos = cc.listarCursos();
    	
    	int tamanho = cursos.size();
    	cbCadastroCurso.addItem("");
    	cbNovoIdCurso.addItem("");
    	for(int i = 0; i < tamanho; i++) {
    		Curso curso = cursos.get(i);
    		cbCadastroCurso.addItem(curso.getId());
    		cbNovoIdCurso.addItem(curso.getId());
    	}
    }
 
    @Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		try {
			if(cmd.equals("Cadastrar")) {
				adicionarDisciplina();
				carregarCbDisciplina(); 
			}
			if(cmd.equals("Atualizar")) {
				atualizarDisciplina();
			}
			if(cmd.equals("Listar")) {
		        carregarDisciplinas();
		        Fila<String> fila = carregarFila();
		        carregarTabela(fila);
			}
			if(cmd.equals("Buscar")) {
				mostrarDisciplina(cmd);
			}
			if(cmd.equals("PesquisarA")) {
				mostrarDisciplina(cmd);
			}
			if(cmd.equals("PesquisarD")) {
				mostrarDisciplina(cmd);
			}
			if(cmd.equals("Deletar")) {
				removerDisciplina();
				carregarCbDisciplina();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
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
