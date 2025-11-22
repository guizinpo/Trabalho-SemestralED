package controller;

import util.arquivoUtil;
import model.Curso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.listaSimples.Lista;

public class CursoController implements ActionListener {

	private static final String CAMINHO = "src/resources/cursos.csv";
	
	//Cadastrar
	private JTextField tfCadastrarID;
	private JTextField tfCadastrarNome;
	private JTextField tfCadastrarArea;
	private JTextArea taResultadoCadastrar;
	
	//Atualizar
	private JTextField tfNomeDesejavel;
	private JTextField tfNovoNome;
	private JTextField tfNovaArea;
	private JTextArea taResultadoAtualizar;
	
	//Consultar
	private JTextField tfBuscar;
	private JTable tableConsultar;
	
	//Deletar
	private JTextField tfNomeDesejavel2;
	private JTextArea taResultadoDeletar;

	public CursoController(JTextField tfCadastrarID, JTextField tfCadastrarNome, JTextField tfCadastrarArea,
			JTextArea taResultadoCadastrar, JTextField tfNomeDesejavel, JTextField tfNovoNome, JTextField tfNovaArea,
			JTextArea taResultadoAtualizar, JTextField tfBuscar, JTable tableConsultar,
			JTextField tfNomeDesejavel2, JTextArea taResultadoDeletar) {
		super();
		this.tfCadastrarID = tfCadastrarID;
		this.tfCadastrarNome = tfCadastrarNome;
		this.tfCadastrarArea = tfCadastrarArea;
		this.taResultadoCadastrar = taResultadoCadastrar;
		this.tfNomeDesejavel = tfNomeDesejavel;
		this.tfNovoNome = tfNovoNome;
		this.tfNovaArea = tfNovaArea;
		this.taResultadoAtualizar = taResultadoAtualizar;
		this.tfBuscar = tfBuscar;
		this.tableConsultar = tableConsultar;
		this.tfNomeDesejavel2 = tfNomeDesejavel2;
		this.taResultadoDeletar = taResultadoDeletar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Cadastrar")) {
			try {
				inserirCurso();
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
		}
		if(cmd.equals("Atualizar")) {
			try {
				atualizarCurso();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(cmd.equals("Listar")) {
			try {
		        Lista<Curso> lista = listarCursos();
		        
		        atualizarTabela(lista);
		    } catch (Exception e1) {
		        e1.printStackTrace();
		    }
		}
		if(cmd.equals("Buscar")) {
			try {
				buscarCurso();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(cmd.equals("Deletar")) {
			try {
				removerCurso();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
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

					c.id = info[0];
					c.nome = info[1];
					c.area = info[2];

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
	    
	    curso.id = id;
	    curso.nome = nome;
	    curso.area = area;
	    
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

	                if (curso.id.equalsIgnoreCase(idArquivo)) {
	                    idExiste = true;
	                }

	                if (curso.nome.equalsIgnoreCase(nomeArquivo)) {
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

		String nome = tfNomeDesejavel2.getText().trim();
		
		Lista<Curso> cursos = listarCursos();
		Lista<String> addLines = new Lista<>();
		
	    boolean encontrado = false;
		
		for (int i = 0; i < cursos.size(); i++) {
			Curso c = cursos.get(i);
			
			if(!c.nome.trim().equalsIgnoreCase(nome.trim())) {
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

		String nome = tfNomeDesejavel.getText().trim();
		
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
			
			if(c.nome.trim().equalsIgnoreCase(nome.trim())) {
				c.nome = novoNome;
				c.area = novaArea;
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
	        dados[i][0] = c.id;
	        dados[i][1] = c.nome;
	        dados[i][2] = c.area;
	    }

	    tableConsultar.setModel(
	        new javax.swing.table.DefaultTableModel(dados, colunas)
	    );
	}
	
	// Este metodo faz a procura de um curso especifico por nome.
	public void buscarCurso() throws Exception {
		
		String buscar = tfBuscar.getText().trim();

	    Lista<Curso> cursos = listarCursos();
	    Lista<Curso> res = new Lista<>();

	    for (int i = 0; i < cursos.size(); i++) {
	        Curso c = cursos.get(i);
	        
	        if (c.nome.trim().equalsIgnoreCase(buscar)) {
	        	res.addLast(c);
	        }
	    }

	    atualizarTabela(res);

	}

}
