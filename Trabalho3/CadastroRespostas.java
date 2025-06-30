import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;

public class CadastroRespostas extends Base {
    private String nomeDisciplina;
    private GerenciadorResultados gerenciadorResultados;

    public CadastroRespostas() {
        super();
        this.gerenciadorResultados = new GerenciadorResultados();
    }

    public void criarArquivoDisciplina(Scanner sc) {
        System.out.print("\n=== Criar Arquivo da Disciplina ===\n");
        System.out.print("Digite o nome da disciplina: ");
        nomeDisciplina = sc.nextLine();

        String nomeArquivo = "RespostasAlunos/" + nomeDisciplina + ".txt";

        boolean adicionarAluno = true;

        while (adicionarAluno) {
            System.out.print("\nNome do aluno: ");
            String nomeAluno = sc.nextLine();

            char[] respostas = new char[10];
            System.out.print("\n-- Preencha as respostas do aluno (V/F) --\n");
            for (int i = 0; i < 10; i++) {
                String respostaQ;
                do {
                    System.out.print("Q" + (i + 1) + ": ");
                    respostaQ = sc.nextLine().trim().toUpperCase();
                    if (respostaQ.length() == 1 && (respostaQ.charAt(0) == 'V' || respostaQ.charAt(0) == 'F')) {
                        respostas[i] = respostaQ.charAt(0);
                        break;
                    } else {
                        System.out.println("Entrada inválida. Digite 'V' para Verdadeiro ou 'F' para Falso.");
                    }
                } while (true);
            }

            String respostasAluno = new String(respostas);
            listaAlunos.add(new Aluno(nomeAluno, respostasAluno, 0));

            System.out.print("\nDeseja adicionar outro aluno? (S/N): ");
            String opcaoAdicionar = sc.nextLine();
            if (opcaoAdicionar.equalsIgnoreCase("N")) {
                adicionarAluno = false;
            }
        }

        salvarArquivo(nomeArquivo);
        visualizarArquivo(nomeArquivo);
    }

    public void gerarGabaritoOficial(Scanner sc, String nomeDisciplinaGabarito) {
        System.out.print("\n=== Criar Gabarito Oficial para " + nomeDisciplinaGabarito + " ===\n");
        String nomeArquivo = "GabaritoOficial/" + nomeDisciplinaGabarito + ".txt";

        char[] respostasGabarito = new char[10];
        System.out.print("\n-- Preencha as 10 respostas oficiais (V/F) --\n");
        for (int i = 0; i < 10; i++) {
            String respostaQ;
            do {
                System.out.print("Q" + (i + 1) + ": ");
                respostaQ = sc.nextLine().trim().toUpperCase();
                if (respostaQ.length() == 1 && (respostaQ.charAt(0) == 'V' || respostaQ.charAt(0) == 'F')) {
                    respostasGabarito[i] = respostaQ.charAt(0);
                    break;
                } else {
                    System.out.println("Entrada inválida. Digite 'V' para Verdadeiro ou 'F' para Falso.");
                }
            } while (true);
        }

        String gabaritoOficialString = new String(respostasGabarito);
        salvarGabarito(nomeArquivo, gabaritoOficialString);
    }

    public void gerarResultado(Scanner sc) {
        System.out.print("\n=== Gerar Resultado da Disciplina ===\n");
        System.out.print("Digite o nome da disciplina para gerar resultados: ");
        nomeDisciplina = sc.nextLine();

        String nomeArquivoDisciplina = "RespostasAlunos/" + nomeDisciplina + ".txt";
        lerArquivo(nomeArquivoDisciplina);

        if (listaAlunos.isEmpty()) {
            System.out.println("Nenhum aluno encontrado para a disciplina " + nomeDisciplina + ". Verifique o nome do arquivo.");
            return;
        }

        String caminhoGabarito = "GabaritoOficial/" + nomeDisciplina + ".txt";
        System.out.println("Procurando gabarito oficial em: " + caminhoGabarito);

        String gabarito = lerGabarito(caminhoGabarito);

        if (gabarito == null || gabarito.isEmpty()) {
            System.err.println("Gabarito não pôde ser lido ou está vazio. Certifique-se de que o gabarito oficial para '" + nomeDisciplina + "' exista em 'GabaritoOficial/' e contenha 10 respostas (V/F). Não é possível calcular as notas.");
            return;
        }

        if (gabarito.length() != 10) {
            System.err.println("Gabarito inválido. O gabarito deve conter exatamente 10 caracteres (V ou F).");
            return;
        }

        gerenciadorResultados.calcularNotasAlunos(listaAlunos, gabarito);

        OrdenacaoStrategy ordenacaoAlfabetica = OrdenacaoStrategyFactory.getStrategy("alfabetica");
        ordenacaoAlfabetica.ordenarEsalvar(nomeDisciplina, listaAlunos);

        OrdenacaoStrategy ordenacaoPorNota = OrdenacaoStrategyFactory.getStrategy("porNota");
        ordenacaoPorNota.ordenarEsalvar(nomeDisciplina, listaAlunos);

        System.out.println("\nResultados gerados com sucesso para a disciplina: " + nomeDisciplina);
    }

    private String lerGabarito(String caminho) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha = br.readLine();
            if (linha != null) {
                String gabaritoProcessado = linha.trim().toUpperCase();
                if (gabaritoProcessado.matches("[VF]{10}")) {
                    return gabaritoProcessado;
                } else {
                    System.err.println("Conteúdo do gabarito inválido: '" + linha + "'. Deve conter apenas 'V' ou 'F' por 10 questões.");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler gabarito do caminho " + caminho + ": " + e.getMessage());
        }
        return null;
    }
}