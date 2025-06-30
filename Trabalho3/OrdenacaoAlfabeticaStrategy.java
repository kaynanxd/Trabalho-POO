import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrdenacaoAlfabeticaStrategy implements OrdenacaoStrategy {

    public void ordenarEsalvar(String nomeDisciplina, List<Aluno> listaAlunos) {
        List<Aluno> alunoOrdenadoNome = new ArrayList<>(listaAlunos);
        alunoOrdenadoNome.sort(Comparator.comparing(a -> a.getNomeAluno().toLowerCase()));

        String dirOrdemAlfabetica = "RespostasAlunos/Ordem Alfábetica";
        File diretorioAlfabetico = new File(dirOrdemAlfabetica);
        if (!diretorioAlfabetico.exists()) {
            diretorioAlfabetico.mkdirs();
        }
        String nomeArquivoAlfabetico = dirOrdemAlfabetica + "/" + nomeDisciplina + "_OrdemAlfabetica.txt";
        salvarArquivoNotas(nomeArquivoAlfabetico, alunoOrdenadoNome, false);
    }

    private void salvarArquivoNotas(String caminho, List<Aluno> lista, boolean mostrarMedia) {
        try (BufferedWriter arqNotas = new BufferedWriter(new FileWriter(caminho))) {
            int somaNotas = 0;
            for (Aluno aluno : lista) {
                arqNotas.write(aluno.getNomeAluno() + ": " + aluno.getNota());
                arqNotas.newLine();
                somaNotas += aluno.getNota();
            }
            if (mostrarMedia && !lista.isEmpty()) {
                double media = (double) somaNotas / lista.size();
                arqNotas.write("Média da turma: " + String.format("%.2f", media));
                arqNotas.newLine();
            }
            System.out.println("Arquivo de notas salvo: " + caminho);
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo de notas " + caminho + ": " + e.getMessage());
        }
    }
}