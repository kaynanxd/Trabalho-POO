import java.util.Scanner;

public class Menu {
    private SistemaNotasFacade facade;
    private Scanner scanner;

    public Menu(SistemaNotasFacade facade, Scanner scanner) {
        this.facade = facade;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Criar Arquivo de Respostas Dos Alunos da Disciplina");
            System.out.println("2. Visualizar Respostas dos Alunos");
            System.out.println("3. Criar Gabarito Oficial De Resultados da disciplina");
            System.out.println("4. Visualizar Gabarito Oficial De Resultados da disciplina");
            System.out.println("5. Gerar Resultados da Disciplina");
            System.out.println("0. Sair");
            System.out.print("Digite a opcao: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();
                System.out.print("Escolha uma opção: ");
            }
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    facade.criarArquivoRespostasDisciplina();
                    break;
                case 2:
                    System.out.print("Digite o nome da disciplina para visualizar (ex: Matematica): ");
                    String nomeArqVisualizar = scanner.nextLine();
                    facade.visualizarRespostasAlunos("RespostasAlunos/" + nomeArqVisualizar + ".txt");
                    break;
                case 3:
                    System.out.print("Digite o nome da disciplina para o gabarito oficial (ex: Matematica): ");
                    String nomeDisciplinaGabarito = scanner.nextLine();
                    facade.gerarGabaritoOficial(nomeDisciplinaGabarito);
                    facade.visualizarGabaritoOficial("GabaritoOficial/" + nomeDisciplinaGabarito + ".txt");
                    break;
                case 4:
                    System.out.print("Digite o nome da disciplina para visualizar (ex: Matematica): ");
                    String nomeArqVisualizargabarito = scanner.nextLine();
                    facade.visualizarGabaritoOficial("GabaritoOficial/" + nomeArqVisualizargabarito + ".txt");
                    break;
                case 5:
                    facade.gerarResultadosDisciplina();
                    break;
                case 0:
                    System.out.println("Saindo do programa. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }
}