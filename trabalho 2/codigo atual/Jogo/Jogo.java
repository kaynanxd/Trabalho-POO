import java.util.*;

public class Jogo {

    private Tabuleiro tabuleiro;
    private boolean modoDebug = false;
    private InputGerenciador inputGerenciador;
    private GameConfigurador GameConfigurador;
    private TabuleiroRender tabuleiroRender;
    private int numeroRodada;

    public Jogo() {
        Scanner sc = new Scanner(System.in);
        this.inputGerenciador = new InputGerenciador(sc);
        tabuleiro = Tabuleiro.getInstancia();
        this.GameConfigurador = new GameConfigurador(inputGerenciador, tabuleiro);
        this.tabuleiroRender = new TabuleiroRender();
        this.numeroRodada = 0;
    }

    public void setupEiniciarJogo() {
        GameConfigurador.configureTabuleiro();
        this.modoDebug = GameConfigurador.configurePlayers();
        printTabuleiro();
        start();
        inputGerenciador.getScanner().close();
    }

    public boolean verificaFim() {
        for (Jogador jogador : tabuleiro.getJogadores()) {
            if (jogador.getPosicaoAtual() >= tabuleiro.getTamanhoTabuleiro() - 1) { // acessa tamanho do tabuleiro
                return true;
            }
        }
        return false;
    }

    public void printTabuleiro() {
        this.tabuleiroRender.mostrarEstado(tabuleiro.getCasas(), tabuleiro.getJogadores(), this.numeroRodada);
    }

    public void mostrarResultadoFinal() {
        int ultimaCasaDoTabuleiro = tabuleiro.getCasas().isEmpty() ? 0 : tabuleiro.getTamanhoTabuleiro() - 1;
        this.tabuleiroRender.mostrarResultadoFinal(tabuleiro.getJogadores(), ultimaCasaDoTabuleiro);
    }

    public void start() {
        inputGerenciador.pressionarEnter("Pressione ENTER para iniciar a primeira rodada...");
        while (!verificaFim()) {
            numeroRodada++;
            Jogador atual = tabuleiro.getProximoJogador();
            System.out.println("\n--- Vez de: " + atual.getNomeJogador() + " [" + atual.getCorJogador() + "] ---");

            if (atual.jogadorParalisado) { continue;}

            int casasAndadas;
            boolean jogarNovamente;
            do {
                jogarNovamente = false;

                if (modoDebug) {
                    casasAndadas = inputGerenciador.LerInteiro("Digite o número de casas para andar (modo debug): ", Integer.MIN_VALUE, Integer.MAX_VALUE,
                            "Entrada inválida. Digite um número inteiro."
                    );
                } else {
                    int[] resultadosDados = atual.lancarDados();
                    int dado1 = resultadosDados[0];
                    int dado2 = resultadosDados[1];
                    casasAndadas = dado1 + dado2;

                    System.out.println(atual.getNomeJogador() + " tirou " + dado1 + " e " + dado2 + " (Total: " + casasAndadas + ") nos dados.");

                    if (dado1 == dado2) {
                        System.out.println("🎲 Dados iguais! " + atual.getNomeJogador() + " joga novamente.");
                        jogarNovamente = true;
                    }
                }

                tabuleiro.moverJogador(atual, casasAndadas);
                printTabuleiro();

                if (atual.getJogadasExtras() > 0) {
                    System.out.println(atual.getNomeJogador() + " tem uma jogada extra!");
                    atual.setJogadasExtras(atual.getJogadasExtras() - 1);
                    jogarNovamente = true;
                }

            } while (jogarNovamente);

            if (!verificaFim()) {
                inputGerenciador.pressionarEnter("Pressione ENTER para a próxima rodada...");
            }

        }
        mostrarResultadoFinal();
    }
}