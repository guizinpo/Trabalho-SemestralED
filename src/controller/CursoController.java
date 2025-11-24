package controller;

import util.arquivoUtil;
import model.Curso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.listaSimples.Lista;

public class CursoController implements ActionListener {

	private static final String CAMINHO = "resources/cursos.csv";
	
	//Cadastrar
	private JTextField tfCadastrarID;
	private JTextField tfCadastrarNome;
	private JTextField tfCadastrarArea;
	private JTextArea taResultadoCadastrar;
	
	//Atualizar
	private JComboBox<String> cbNomeDesejavel;
	private JTextField tfNovoNome;
	private JTextField tfNovaArea;
	private JTextArea taResultadoAtualizar;
	
	//Consultar
	private JComboBox<String> cbBuscar;
	private JTable tableConsultar;
	
	//Deletar
	private JComboBox<String> cbNomeDesejavel2;
	private JTextArea taResultadoDeletar;

	public CursoController() {
		super();
	}
	
	public CursoController(JTextField tfCadastrarID, JTextField tfCadastrarNome, JTextField tfCadastrarArea,
			JTextArea taResultadoCadastrar, JComboBox<String> cbNomeDesejavel, JTextField tfNovoNome, JTextField tfNovaArea,
			JTextArea taResultadoAtualizar, JComboBox<String> cbBuscar, JTable tableConsultar,
			JComboBox<String> cbNomeDesejavel2, JTextArea taResultadoDeletar) {
		super();
		this.tfCadastrarID = tfCadastrarID;
		this.tfCadastrarNome = tfCadastrarNome;
		this.tfCadastrarArea = tfCadastrarArea;
		this.taResultadoCadastrar = taResultadoCadastrar;
		this.cbNomeDesejavel = cbNomeDesejavel;
		this.tfNovoNome = tfNovoNome;
		this.tfNovaArea = tfNovaArea;
		this.taResultadoAtualizar = taResultadoAtualizar;
		this.cbBuscar = cbBuscar;
		this.tableConsultar = tableConsultar;
		this.cbNomeDesejavel2 = cbNomeDesejavel2;
		this.taResultadoDeletar = taResultadoDeletar;
		
		try {
			carregarCbCurso();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		try {
			if(cmd.equals("Cadastrar")) {
				inserirCurso();
				carregarCbCurso();
			}
			if(cmd.equals("Atualizar")) {
				atualizarCurso();
			}
			if(cmd.equals("Listar")) {
		        Lista<Curso> lista = listarCursos();    
		        atualizarTabela(lista);
			}
			if(cmd.equals("Buscar")) {
				buscarCurso();
			}
			if(cmd.equals("Deletar")) {
				removerCurso();
				carregarCbCurso();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	// Este metodo ira fazer a leitura de todos os cursos dentro do arquivo.
		public Lista<Curso> listarCursos() throws Exception {

			Lista<String> arquivo = arquivoUtil.lerArquivo(CAMINHO);
			Lista<Curso> todos = new Lista<>();

			for (int i = 0; i < arquivo.size(); i++) {
				String line = arquivo.get(i);

				if (line != null && !line.isEmpty()) {
					Curso c = new Curso();
					String[] info = line.split(";");

					c.setId(info[0]);
					c.setNome(info[1]);
					c.setArea(info[2]);

					todos.addLast(c);
				} else {
					continue;
				}
			}

			return todos;
		}
	
	// Este metodo cria um novo curso e já grava no arquivo.csv.
	public void inserirCurso() throws Exception {
		
	    String id = tfCadastrarID.getText().trim();
	    String nome = tfCadastrarNome.getText().trim();
	    String area = tfCadastrarArea.getText().trim();

	    if (id.isEmpty() || nome.isEmpty() || area.isEmpty()) {
	        taResultadoCadastrar.setText("Preencha todos os campos! Pode haver algum vazio.");
	        
	        return;
	    }
	    
	    Curso curso = new Curso();
	    
	    curso.setId(id);
	    curso.setNome(nome);
	    curso.setArea(area);
	    
	    Lista<String> arquivo = arquivoUtil.lerArquivo(CAMINHO);

	    boolean idExiste = false;
	    boolean nomeExiste = false;

	    for (int i = 0; i < arquivo.size(); i++) {
	        String line = arquivo.get(i);

	        if (line != null && !line.isEmpty()) {

	            String[] dados = line.split(";");

	            if (dados.length >= 3) {
	            	String idArquivo = dados[0].trim();
	            	String nomeArquivo = dados[1].trim();

	                if (curso.getId().equalsIgnoreCase(idArquivo)) {
	                    idExiste = true;
	                }

	                if (curso.getNome().equalsIgnoreCase(nomeArquivo)) {
	                    nomeExiste = true;
	                }
	            }
	        }
	    }

	    if (idExiste || nomeExiste) {
	        taResultadoCadastrar.setText("Já existe um curso com este ID ou Nome.");
	        
	        return;
	    }

	    // Se passou por todas as validações, o codigo irá gravar
	    
	    arquivoUtil.adicionarLinha(CAMINHO, curso.toString());
	    
	    taResultadoCadastrar.setText("Curso cadastrado com sucesso!");
	}

	// Este metodo é somente para remoção de um curso indesejado.
	public void removerCurso() throws Exception {

		String nome = (String) cbNomeDesejavel2.getSelectedItem();
		
		Lista<Curso> cursos = listarCursos();
		Lista<String> addLines = new Lista<>();
		
	    boolean encontrado = false;
		
		for (int i = 0; i < cursos.size(); i++) {
			Curso c = cursos.get(i);
			
			if(!c.getNome().trim().equalsIgnoreCase(nome.trim())) {
			    addLines.add(c.toString(), addLines.size());
			} else {
	            encontrado = true;
	        }

		}

		arquivoUtil.gravarArquivo(CAMINHO, addLines);
		
		if(encontrado) {
	        taResultadoDeletar.setText("Curso removido com sucesso!");
	    } else {
	        taResultadoDeletar.setText("Curso não encontrado.");
	    }
	}

	// Este metodo faz a atualização de uma informação do curso.
	public void atualizarCurso() throws Exception {

		String nome = (String) cbNomeDesejavel.getSelectedItem();
		
		String novoNome = tfNovoNome.getText().trim();
		String novaArea = tfNovaArea.getText().trim();
		
		if (nome.isEmpty() || novoNome.isEmpty() || novaArea.isEmpty()) {
		        taResultadoAtualizar.setText("Preencha todos os campos! Pode haver algum vazio.");
		        
		        return;
		}
		
		Lista<Curso> cursos = listarCursos();
		Lista<String> addLines = new Lista<>();
		
		boolean encontrado = false;
		
		for(int i = 0; i < cursos.size(); i++) {
			Curso c = cursos.get(i);
			
			if(c.getNome().trim().equalsIgnoreCase(nome.trim())) {
				c.setNome(novoNome);
				c.setArea(novaArea);;
			    encontrado = true;
			}
			addLines.add(c.toString(), addLines.size());
		}
		
		arquivoUtil.gravarArquivo(CAMINHO, addLines);
		
		if(encontrado) {
	        taResultadoAtualizar.setText("Curso atualizado com sucesso!");
	    } else {
	        taResultadoAtualizar.setText("Curso não encontrado.");
	    }
	}
	
	//Este metodo serve somente para a exibição dos dados na area de consulta
	private void atualizarTabela(Lista<Curso> cursos) throws Exception {
		
	    String[] colunas = { "ID", "Nome", "Área" };

	    Object[][] dados = new Object[cursos.size()][3];

	    for (int i = 0; i < cursos.size(); i++) {
	        Curso c = cursos.get(i);
	        dados[i][0] = c.getId();
	        dados[i][1] = c.getNome();
	        dados[i][2] = c.getArea();
	    }

	    tableConsultar.setModel(
	        new javax.swing.table.DefaultTableModel(dados, colunas)
	    );
	}
	
	// Este metodo faz a procura de um curso especifico por nome.
	public void buscarCurso() throws Exception {
		
		String buscar = (String) cbBuscar.getSelectedItem();

	    Lista<Curso> cursos = listarCursos();
	    Lista<Curso> res = new Lista<>();

	    for (int i = 0; i < cursos.size(); i++) {
	        Curso c = cursos.get(i);
	        
	        if (c.getNome().trim().equalsIgnoreCase(buscar)) {
	        	res.addLast(c);
	        }
	    }

	    atualizarTabela(res);

	}
	
	private void carregarCbCurso() throws Exception {
    	Lista<Curso> cursos = listarCursos();
    	
    	int tamanho = cursos.size();
    	cbBuscar.addItem("");
    	cbNomeDesejavel.addItem("");
    	cbNomeDesejavel2.addItem("");
    	for(int i = 0; i < tamanho; i++) {
    		Curso curso = cursos.get(i);
    		cbBuscar.addItem(curso.getNome());
        	cbNomeDesejavel.addItem(curso.getNome());
        	cbNomeDesejavel2.addItem(curso.getNome());
    	}
    }

}
