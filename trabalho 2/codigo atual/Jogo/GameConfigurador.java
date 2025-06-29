import java.util.Arrays;
import java.util.List;

public class GameConfigurador {
    private final InputGerenciador inputGerenciador;
    private final Tabuleiro tabuleiro;

    public GameConfigurador(InputGerenciador inputHandler, Tabuleiro tabuleiro) {
        this.inputGerenciador = inputHandler;
        this.tabuleiro = tabuleiro;
    }

    public int configureTabuleiro() {
        System.out.println("--- Configuração do Jogo ---");

        int numCasas = inputGerenciador.LerInteiro(
                "Digite o número de casas do tabuleiro (mínimo 20): ",
                20, Integer.MAX_VALUE,
                "O número de casas deve ser maior ou igual a 20."
        );

        // Inicializa todas as casas como Simples
        for (int i = 0; i < numCasas; i++) {
            Casas casa = CasaFactory.criarCasa(i, "simples");
            tabuleiro.adicionarCasa(casa);
        }
        System.out.println("\n--- Configuração das Casas Especiais ---");
        System.out.println("Para cada tipo de casa especial, digite os números das casas separados por vírgula (ex: 3,5,8).");
        System.out.println("Se não quiser nenhuma casa de um tipo, apenas pressione Enter.");
        System.out.println("As casas são numeradas de 0 a " + (numCasas - 1) + ".");

        String[] tiposCasasEspeciais = {"sorte", "azar","jogadenovo", "prisao", "reversa", "surpresa"};

        for (String tipo : tiposCasasEspeciais) {
            String prompt = "Casas do tipo '" + tipo + "': ";
            String entrada = inputGerenciador.lerLinhaVazia(prompt).trim();

            if (entrada.isEmpty()) {
                continue;
            }

            try {
                String[] numerosStr = entrada.split(",");
                for (String numStr : numerosStr) {
                    int numeroCasa = Integer.parseInt(numStr.trim());
                    if (numeroCasa >= 0 && numeroCasa < numCasas) {
                        Casas novaCasa = CasaFactory.criarCasa(numeroCasa, tipo);
                        tabuleiro.getCasas().set(numeroCasa, novaCasa);
                    } else {
                        System.out.println("Atenção: Casa " + numeroCasa + " fora do limite do tabuleiro. Ignorada para o tipo '" + tipo + "'.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida para o tipo '" + tipo + "'. Por favor, use números separados por vírgula.");
            }
        }
        System.out.println("Configuração do tabuleiro das casas concluída.");
        return numCasas;
    }

    public boolean configurePlayers() {
        int numJogadores = inputGerenciador.LerInteiro(
                "Digite o número de jogadores (até 6): ",
                1, 6,
                "O número de jogadores deve estar entre 1 e 6."
        );

        List<String> coresValidas = Arrays.asList("vermelho", "verde", "amarelo", "azul", "roxo", "ciano", "branco");
        List<String> tiposJogadoresValidos = Arrays.asList("normal", "sortudo", "azarado");

        for (int i = 0; i < numJogadores; i++) {
            System.out.println("\nJogador " + (i + 1));

            String nome = inputGerenciador.lerNomeVazio("Nome: ",
                    "O nome do jogador não pode ser vazio. Por favor, digite um nome.");

            String cor = inputGerenciador.lerListaInteiros("Cor", coresValidas,
                    "Cor inválida. Por favor, escolha uma das cores listadas.");

            String tipo = inputGerenciador.lerListaInteiros("Tipo de Jogador", tiposJogadoresValidos,
                    "Tipo inválido. Por favor, digite 'normal', 'sortudo' ou 'azarado'.");

            Jogador jogador = JogadorFactory.criarJogador(tipo, nome, this.tabuleiro);
            jogador.setCorJogador(cor);
            tabuleiro.adicionarJogador(jogador);
        }

        return inputGerenciador.LerSimOuNao("Deseja ativar o modo debug?");
    }
}