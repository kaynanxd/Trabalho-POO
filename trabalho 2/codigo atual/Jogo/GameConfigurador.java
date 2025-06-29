import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameConfigurador {
    private final InputGerenciador inputGerenciador;
    private final Tabuleiro tabuleiro;

    public GameConfigurador(InputGerenciador inputGerenciador, Tabuleiro tabuleiro) {
        this.inputGerenciador = inputGerenciador;
        this.tabuleiro = tabuleiro;
    }

    public int configureTabuleiro() {
        System.out.println("--- Configuração do Jogo ---");

        int numCasas = inputGerenciador.lerInteiro(
                "Digite o número de casas do tabuleiro (mínimo 20): ",
                20, Integer.MAX_VALUE,
                "O número de casas deve ser maior ou igual a 20."
        );

        // Inicializa todas as casas como Simples e depois vai mudando os tipos
        for (int i = 0; i < numCasas; i++) {
            Casas casa = CasaFactory.criarCasa(i, "simples");
            tabuleiro.adicionarCasa(casa);
        }
        System.out.println("\n--- Configuração das Casas Especiais ---");
        System.out.println("Para cada tipo de casa especial, digite os números das casas separados por vírgula ex: 3,5,8");
        System.out.println("Se não quiser nenhuma casa de um tipo, apenas aperte Enter.");
        System.out.println("\nAs casas são numeradas de 0 a " + (numCasas - 1) + ".\n");

        String[] tiposCasasEspeciais = {"sorte", "azar","jogardenovo", "prisao", "reversa", "surpresa"};

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
                        System.out.println("Atenção: Casa " + numeroCasa + " fora do limite do tabuleiro.");
                    }
                }
            } catch (NumberFormatException erro) {
                System.out.println("Entrada invalida, use números separados por vírgula.");
            }
        }
        System.out.println("\nConfiguração do tabuleiro das casas concluída.\n");
        return numCasas;
    }

    public boolean configureJogadores() {
        boolean tipoJogadorValido = false;

        while (!tipoJogadorValido) {
            tabuleiro.getJogadores().clear();

            int numJogadores = inputGerenciador.lerInteiro(
                    "Digite o número de jogadores (até 6): ",
                    1, 6,
                    "O número de jogadores deve estar entre 1 e 6."
            );

            List<String> coresValidas = Arrays.asList("vermelho", "verde", "amarelo", "azul", "roxo", "ciano", "azul");
            List<String> tiposJogadoresValidos = Arrays.asList("normal", "sortudo", "azarado");

            Map<String, Integer> contagemTipos = new HashMap<>();
            Map<String, Boolean> tiposDistintos = new HashMap<>();

            for (int i = 0; i < numJogadores; i++) {
                System.out.println("\nJogador " + (i + 1));

                String nome = inputGerenciador.lerNomeVazio("Nome: ", "O nome do jogador não pode ser vazio. Digite um nome.");

                String cor = inputGerenciador.lerListaInteiros("Cor", coresValidas, "Cor inválida. Escolha uma das cores listadas.");

                String tipo = inputGerenciador.lerListaInteiros("Tipo de Jogador", tiposJogadoresValidos, "Tipo inválido. Por favor, digite 'normal', 'sortudo' ou 'azarado'.");

                Jogador jogador = JogadorFactory.criarJogador(tipo, nome, this.tabuleiro);
                jogador.setCorJogador(cor);
                tabuleiro.adicionarJogador(jogador);

                contagemTipos.put(tipo, contagemTipos.getOrDefault(tipo, 0) + 1);
                tiposDistintos.put(tipo, true);
            }
            if (tiposDistintos.size() < 2) { // Usa o tamanho do mapa de tipos distintos
                System.out.println("\n É necessário ter pelo menos dois tipos diferentes de jogadores para iniciar o jogo.");
                System.out.println("Por favor, configure os jogadores novamente.");
                tipoJogadorValido = false;
            } else {
                System.out.println("\nConfiguração de jogadores concluida com sucesso!\n");
                tipoJogadorValido = true;
            }
        }
        return inputGerenciador.lerSimOuNao("Deseja ativar o modo debug?");
    }

}