package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;

import br.edu.fateczl.listaSimples.Lista;
import br.edu.fateczl.quick.quick;
import model.Disciplina;
import model.Inscricao;
import model.Professor;
import model.InscritoCompleto;
import model.Processo;
import util.arquivoUtil;

public class InscricaoController implements ActionListener {

    private static final String CAMINHO = "resources/inscricoes.csv";

    private JComboBox<String> cbCpfCadastrar;
    private JComboBox<String> cbIdDisciplinaCadastrar;
    private JComboBox<String> cbCodigoProcessoCadastrar;
    private JTextArea taCadastrar;

    private JComboBox<String> cbCpfAtualizar;
    private JComboBox<String> cbNovoCodigoProcesso;
    private JTextArea taAtualizar;

    private JComboBox<String> cbBuscarDisciplina;
    private JTable tabelaConsulta;

    private JComboBox<String> cbCpfDeletar;
    private JComboBox<String> cbCodigoProcessoDeletar;
    private JTextArea taDeletar;

    private ProfessorController professorController = new ProfessorController();
    
    public InscricaoController() {
    	super();
    }

    public InscricaoController(
    		JComboBox<String> cbCpfCadastrar,
            JComboBox<String> cbIdDisciplinaCadastrar,
            JComboBox<String> cbCodigoProcessoCadastrar,
            JTextArea taCadastrar,

            JComboBox<String> cbCpfAtualizar,
            JComboBox<String> cbNovoCodigoProcesso,
            JTextArea taAtualizar,

            JComboBox<String> cbBuscarDisciplina,
            JTable tabelaConsulta,

            JComboBox<String> cbCpfDeletar,
            JComboBox<String> cbCodigoProcessoDeletar,
            JTextArea taDeletar
    ) {

        this.cbCpfCadastrar = cbCpfCadastrar;
        this.cbIdDisciplinaCadastrar = cbIdDisciplinaCadastrar;
        this.cbCodigoProcessoCadastrar = cbCodigoProcessoCadastrar;
        this.taCadastrar = taCadastrar;

        this.cbCpfAtualizar = cbCpfAtualizar;
        this.cbNovoCodigoProcesso = cbNovoCodigoProcesso;
        this.taAtualizar = taAtualizar;

        this.cbBuscarDisciplina = cbBuscarDisciplina;
        this.tabelaConsulta = tabelaConsulta;

        this.cbCpfDeletar = cbCpfDeletar;
        this.cbCodigoProcessoDeletar = cbCodigoProcessoDeletar;
        this.taDeletar = taDeletar;
        
        try {
        	carregarCbDisciplina();
        	carregarCbInscricao();
        	carregarCbProcesso();
        	carregarCbProfessor();
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        try {
            switch (cmd) {

                case "Cadastrar":
                    cadastrarInscricao();
                    carregarCbInscricao();
                    break;

                case "Atualizar":
                    atualizarInscricao();
                    break;

                case "Consultar":
                    consultarInscritos();
                    break;

                case "Deletar":
                    deletarInscricao();
                    carregarCbInscricao();
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void cadastrarInscricao() throws Exception {
        String cpf = (String) cbCpfCadastrar.getSelectedItem();
        String disciplina = (String) cbIdDisciplinaCadastrar.getSelectedItem();
        String processo = (String) cbCodigoProcessoCadastrar.getSelectedItem();

        if (cpf.isEmpty() || disciplina.isEmpty() || processo.isEmpty()) {
            taCadastrar.setText("Preencha todos os campos!");
            return;
        }

        Professor p = professorController.buscarPorCPF(cpf.trim());
        if (p == null) {
            taCadastrar.setText("Professor não encontrado no sistema.");
            return;
        }

        Inscricao ins = new Inscricao(cpf.trim(), disciplina.trim(), processo.trim());
        arquivoUtil.adicionarLinha(CAMINHO, ins.toString());

        taCadastrar.setText("Inscrição cadastrada com sucesso!");
        
    }

    private void atualizarInscricao() throws Exception {
        String cpf = (String) cbCpfAtualizar.getSelectedItem();
        String novoProcesso = (String) cbNovoCodigoProcesso.getSelectedItem();

        if (cpf.isEmpty() || novoProcesso.isEmpty()) {
            taAtualizar.setText("Preencha todos os campos!");
            return;
        }

        Lista<Inscricao> inscricoes = listar();
        Lista<String> novasLinhas = new Lista<>();

        boolean atualizado = false;

        for (int i = 0; i < inscricoes.size(); i++) {
            Inscricao ins = inscricoes.get(i);

            if (ins.getCpfProfessor().equals(cpf.trim())) {
                ins.setCodigoProcesso(novoProcesso.trim());
                atualizado = true;
            }

            novasLinhas.add(ins.toString(), novasLinhas.size());
        }

        arquivoUtil.gravarArquivo(CAMINHO, novasLinhas);

        if (atualizado) {
            taAtualizar.setText("Inscrição atualizada com sucesso!");
        } else {
            taAtualizar.setText("Nenhuma inscrição encontrada para este CPF.");
        }
    }

    private void consultarInscritos() throws Exception {
        String idDisciplina = (String) cbBuscarDisciplina.getSelectedItem();

        Lista<Inscricao> todas = listar();
        Lista<InscritoCompleto> lista = new Lista<>();

        // Montar estrutura completa
        for (int i = 0; i < todas.size(); i++) {
            Inscricao ins = todas.get(i);

            if (ins.getIdDisciplina().equals(idDisciplina.trim())) {

                Professor p = professorController.buscarPorCPF(ins.getCpfProfessor());

                if (p != null) {
                    lista.add(new InscritoCompleto(
                            p.getNome(),
                            p.getCpf(),
                            p.getArea(),
                            p.getPontos(),
                            ins.getIdDisciplina(),
                            ins.getCodigoProcesso()
                    ), 0);
                }
            }
        }

        ordenarListaPorPontos(lista);
        atualizarTabela(lista);
    }

    private void deletarInscricao() throws Exception {
        String cpf = (String) cbCpfDeletar.getSelectedItem();
        String processo = (String) cbCodigoProcessoDeletar.getActionCommand();

        Lista<Inscricao> todas = listar();
        Lista<String> novasLinhas = new Lista<>();

        boolean removido = false;

        for (int i = 0; i < todas.size(); i++) {
            Inscricao ins = todas.get(i);

            if (ins.getCpfProfessor().equals(cpf.trim())
                    && ins.getCodigoProcesso().equals(processo.trim())) {
                removido = true;
            } else {
                novasLinhas.add(ins.toString(), novasLinhas.size());
            }
        }

        arquivoUtil.gravarArquivo(CAMINHO, novasLinhas);

        if (removido) taDeletar.setText("Inscrição removida com sucesso!");
        else taDeletar.setText("Inscrição não encontrada.");
        
    }
    
    public void remover(String cpf, String processo) throws Exception {
        Lista<Inscricao> todas = listar();
        Lista<String> novasLinhas = new Lista<>();

        boolean removido = false;

        for (int i = 0; i < todas.size(); i++) {
            Inscricao ins = todas.get(i);

            if (ins.getCpfProfessor().equals(cpf) &&
                ins.getCodigoProcesso().equals(processo)) {
                removido = true;
            } else {
                novasLinhas.add(ins.toString(), novasLinhas.size());
            }
        }

        arquivoUtil.gravarArquivo(CAMINHO, novasLinhas);
    }


    Lista<Inscricao> listar() throws Exception{
        Lista<String> linhas = arquivoUtil.lerArquivo(CAMINHO);
        Lista<Inscricao> lista = new Lista<>();

        for (int i = 0; i < linhas.size(); i++) {
            String linha = linhas.get(i);
            if (linha == null || linha.isEmpty()) continue;

            String[] d = linha.split(";");

            lista.add(new Inscricao(d[0], d[1], d[2]), lista.size());
        }

        return lista;
    }

    private void ordenarListaPorPontos(Lista<InscritoCompleto> lista) {
        try {
            int n = lista.size();
            int[] pontos = new int[n];

            for (int i = 0; i < n; i++) {
                pontos[i] = lista.get(i).getPontos();
            }

            quick q = new quick();
            q.quickSort(pontos, 0, n - 1);

            Lista<InscritoCompleto> ordenada = new Lista<>();

            for (int i = 0; i < n; i++) {
                int pAtual = pontos[i];

                for (int j = 0; j < lista.size(); j++) {
                    if (lista.get(j).getPontos() == pAtual) {
                        ordenada.add(lista.get(j), 0);
                        break;
                    }
                }
            }

            lista = ordenada;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void atualizarTabela(Lista<InscritoCompleto> lista) throws Exception {
        String[] colunas = {"Nome", "CPF", "Área", "Pontos", "Disciplina", "Processo"};
        Object[][] dados = new Object[lista.size()][6];

        for (int i = 0; i < lista.size(); i++) {
            InscritoCompleto ic = lista.get(i);

            dados[i][0] = ic.getNome();
            dados[i][1] = ic.getCpf();
            dados[i][2] = ic.getArea();
            dados[i][3] = ic.getPontos();
            dados[i][4] = ic.getDisciplina();
            dados[i][5] = ic.getProcesso();
        }

        tabelaConsulta.setModel(new javax.swing.table.DefaultTableModel(dados, colunas));
    }
    
    private void carregarCbDisciplina() throws Exception {
    	DisciplinaController dc = new DisciplinaController();
    	Lista<Disciplina> disciplinas = dc.carregarDisciplinas();
    	
    	int tamanho = disciplinas.size();
    	cbBuscarDisciplina.addItem("");
    	cbIdDisciplinaCadastrar.addItem("");
    	for(int i = 0; i < tamanho; i++) {
    		Disciplina disciplina = disciplinas.get(i);
    		cbBuscarDisciplina.addItem(disciplina.getId());
        	cbIdDisciplinaCadastrar.addItem(disciplina.getId());
    	}
    }
    
    private void carregarCbProcesso() throws Exception {
    	cbCodigoProcessoCadastrar.removeAllItems();
    	cbCodigoProcessoDeletar.removeAllItems();
    	cbNovoCodigoProcesso.removeAllItems();
    	
    	ProcessoController pc = new ProcessoController();
    	Lista<Processo> processos = pc.carregarProcessos();
    	
    	int tamanho = processos.size();
    	cbCodigoProcessoCadastrar.addItem("");
    	cbCodigoProcessoDeletar.addItem("");
    	cbNovoCodigoProcesso.addItem("");
    	for(int i = 0; i < tamanho; i++) {
    		Processo processo = processos.get(i);
    		cbCodigoProcessoCadastrar.addItem(processo.getCodigoProcesso());
        	cbCodigoProcessoDeletar.addItem(processo.getCodigoProcesso());
        	cbNovoCodigoProcesso.addItem(processo.getCodigoProcesso());
    	}
    }
    
    private void carregarCbInscricao() throws Exception {
    	cbCpfAtualizar.removeAllItems();
    	cbCpfDeletar.removeAllItems();
    	
    	Lista<Inscricao> inscricoes = listar();
    	
    	int tamanho = inscricoes.size();
    	cbCpfAtualizar.addItem("");
    	cbCpfDeletar.addItem("");
    	for(int i = 0; i < tamanho; i++) {
    		Inscricao inscricao = inscricoes.get(i);
    		cbCpfAtualizar.addItem(inscricao.getCpfProfessor());
    		cbCpfDeletar.addItem(inscricao.getCpfProfessor());
    	}
    }
    
    private void carregarCbProfessor() throws Exception{
    	cbCpfCadastrar.removeAllItems();
    	
    	ProfessorController pc = new ProfessorController();
    	Lista<Professor> professores = pc.listar();
    	
    	int tamanho = professores.size();
    	cbCpfCadastrar.addItem("");
    	
    	for(int i = 0; i < tamanho; i++) {
    		Professor professor = professores.get(i);
    		cbCpfCadastrar.addItem(professor.getCpf());
    	}
    }
}
