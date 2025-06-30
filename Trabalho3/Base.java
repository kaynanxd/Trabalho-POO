import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public abstract class Base {
    protected List<Aluno> listaAlunos = new ArrayList<>();

    public void salvarArquivo(String nomeArquivo) {
        File diretorio = new File("RespostasAlunos");
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        try (BufferedWriter arqRespostas = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (int i = 0; i < listaAlunos.size(); i++) {
                Aluno aluno = listaAlunos.get(i);
                arqRespostas.write(aluno.getRespostas() + "  " + aluno.getNomeAluno());
                arqRespostas.newLine();
            }
            System.out.println("\nArquivo salvo com sucesso: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
    public void salvarGabarito(String nomeArquivo, String gabarito) {
        File diretorio = new File("GabaritoOficial");
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        try (BufferedWriter arqGabarito = new BufferedWriter(new FileWriter(nomeArquivo))) {
            arqGabarito.write(gabarito);
            arqGabarito.newLine();
            System.out.println("\nGabarito oficial salvo com sucesso: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o gabarito: " + e.getMessage());
        }
    }
    public void lerArquivo(String nomeArquivo) {
        listaAlunos.clear();
        try (BufferedReader arqLeitura = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = arqLeitura.readLine()) != null) {
                if (nomeArquivo.contains("GabaritoOficial") && linha.trim().matches("[VF]{10}")) {
                    listaAlunos.add(new Aluno("Gabarito Oficial", linha.trim(), 0));
                }
                else {
                    String[] partes = linha.split("  ");
                    if (partes.length == 2) {
                        String respostas = partes[0].trim();
                        String nomeAluno = partes[1].trim();
                        listaAlunos.add(new Aluno(nomeAluno, respostas, 0));
                    } else {
                        System.out.println("Linha inválida: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

    public void visualizarArquivo(String nomeArquivo) {
        lerArquivo(nomeArquivo);
        System.out.println("\n=== Respostas Cadastradas -  ===");
        for (int i = 0; i < listaAlunos.size(); i++) {
            Aluno alunos = listaAlunos.get(i);
            System.out.println(alunos.getRespostas() + "  " + alunos.getNomeAluno());
            System.out.print("\n");
        }
    }

}