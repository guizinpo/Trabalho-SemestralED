package controller;

import util.arquivoUtil;
import model.Curso;
import br.edu.fateczl.listaSimples.Lista;

public class CursoController {

	// Rota
	private static final String CAMINHO = "resources/cursos.csv";

	// Construtor
	public CursoController() {
		super();
	}

	//CRUD
	
	// Este metodo cria um novo curso e já grava no arquivo.csv.
	public void inserirCurso(Curso curso) throws Exception {

		Lista<String> arquivo = arquivoUtil.lerArquivo(CAMINHO);
		
		//verifica se já existe um curso dentro do arquivo
		boolean verifica = false;
		String nome = curso.getNomeCurso();
		
		for(int i = 0; i < arquivo.size(); i++) {
			String line = arquivo.get(i);
			String[] dados = line.split(";");
			String nomeArquivo = dados[1];
			
			if(nome.equalsIgnoreCase(nomeArquivo)) {
				verifica = true;
				break;
			} 
		}
		
		if(!verifica) {
			String line = curso.toString();
	
			arquivoUtil.adicionarLinha(CAMINHO, line);
		} else {
			System.out.println("Já existe este curso");
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

				c.setIdCurso(info[0]);
				c.setNomeCurso(info[1]);
				c.setAreaCurso(info[2]);

				todos.add(c, i);
			} else {
				continue;
			}
		}

		return todos;
	}

	// Este metodo é somente para remoção de um curso indesejado.
	public void removerCurso(String nomeCurso) throws Exception {

		Lista<Curso> cursos = listarCursos();
		Lista<String> addLines = new Lista<>();
		
		for (int i = 0; i < cursos.size(); i++) {
			Curso c = cursos.get(i);
			
			if(!c.getNomeCurso().equals(nomeCurso)) {
				addLines.add(c.toString(), 0);
			}
		}

		arquivoUtil.gravarArquivo(CAMINHO, addLines);
	}

	// Este metodo faz a atualização de uma informação do curso.
	public void atualizarCurso(String nomeCurso, String novoNome, String novaArea) throws Exception {

		Lista<Curso> cursos = listarCursos();
		Lista<String> addLines = new Lista<>();
		
		for(int i = 0; i < cursos.size(); i++) {
			Curso c = cursos.get(i);
			
			if(c.getNomeCurso().equals(nomeCurso)) {
				c.setNomeCurso(novoNome);
				c.setAreaCurso(novaArea);
			}
			
			addLines.add(c.toString(), 0);
		}
		
		arquivoUtil.gravarArquivo(CAMINHO, addLines);
	}
	
	// Este metodo faz a procura de um curso especifico por nome.
	public Lista<Curso> buscarCurso(String nomeCurso) throws Exception {

		Lista<Curso> cursos = listarCursos();

		Lista<Curso> res = new Lista<>();

		for (int i = 0; i < cursos.size(); i++) {
			Curso c = cursos.get(i);

			if (c.getNomeCurso().equals(nomeCurso)) {
				res.add(c, 0);
			}
		}

		return res;
	}
}
