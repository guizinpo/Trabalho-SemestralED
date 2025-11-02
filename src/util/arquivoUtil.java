package util;
import java.io.*;
import java.util.*;

public class arquivoUtil {

	public static List<String> lerArquivo(String caminho) throws IOException {
        List<String> linhas = new ArrayList<>();
        File arquivo = new File(caminho);

        if (!arquivo.exists()) {
            arquivo.getParentFile().mkdirs();
            arquivo.createNewFile();
            return linhas; 
        }

        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        String linha;
        while ((linha = br.readLine()) != null) {
            linhas.add(linha);
        }
        br.close();

        return linhas;
    }

    public static void gravarArquivo(String caminho, List<String> linhas) throws IOException {
        File arquivo = new File(caminho);
        arquivo.getParentFile().mkdirs(); 

        BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, false));
        for (String linha : linhas) {
            bw.write(linha);
            bw.newLine();
        }
        bw.close();
    }

    public static void adicionarLinha(String caminho, String linha) throws IOException {
        File arquivo = new File(caminho);
        arquivo.getParentFile().mkdirs();

        BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true));
        bw.write(linha);
        bw.newLine();
        bw.close();
    }
}
