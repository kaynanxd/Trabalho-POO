import java.util.*;

public class Jogo {

    private Tabuleiro tabuleiro;
    private boolean modoDebug = false;
    private InputGerenciador inputGerenciador;
    private GameConfigurador gameConfigurador;
    private TabuleiroRender tabuleiroRender;
    private int numeroRodada;

    public Jogo() {
        Scanner sc = new Scanner(System.in);
        this.inputGerenciador = new InputGerenciador(sc);
        tabuleiro = Tabuleiro.getInstancia();
        this.gameConfigurador = new GameConfigurador(inputGerenciador, tabuleiro);
        this.tabuleiroRender = new TabuleiroRender();
        this.numeroRodada = 0;
    }

    public void gameSetup() {
        gameConfigurador.configureTabuleiro();
        this.modoDebug = gameConfigurador.configureJogadores();
    }

    public boolean verificaFim() {
        for (Jogador jogador : tabuleiro.getJogadores()) {
            if (jogador.getPosicaoAtual() >= tabuleiro.getTamanhoTabuleiro() - 1) {
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

    private void processarRodadaExibirTabuleiro(Jogador jogadorAtual) {
        int casasAndadas;
        boolean jogarNovamente;

        do {
            jogarNovamente = false;

            if (modoDebug) {
                casasAndadas = inputGerenciador.lerInteiro(
                        "Digite o número de casas para andar (modo debug): ",
                        Integer.MIN_VALUE, Integer.MAX_VALUE,
                        "Entrada inválida. Digite um número inteiro."
                );
            } else {
                int[] resultadosDados = jogadorAtual.lancarDados();
                int dado1 = resultadosDados[0];
                int dado2 = resultadosDados[1];
                casasAndadas = dado1 + dado2;

                System.out.println(jogadorAtual.getNomeJogador() + " tirou " + dado1 + " e " + dado2 + " (Total: " + casasAndadas + ") nos dados.");

                if (dado1 == dado2) {
                    System.out.println(" Dados iguais! " + jogadorAtual.getNomeJogador() + " joga novamente.");
                    jogarNovamente = true;
                }
            }

            int posicaoAtual = jogadorAtual.getPosicaoAtual();
            int casasRestantes = tabuleiro.getTamanhoTabuleiro() - 1 - posicaoAtual;

            if (casasAndadas > casasRestantes) {
                casasAndadas = casasRestantes;
            }

            tabuleiro.moverJogador(jogadorAtual, casasAndadas);
            printTabuleiro();

            if (jogadorAtual.getJogadasExtras() > 0) {
                System.out.println(jogadorAtual.getNomeJogador() + " tem uma jogada extra!");
                jogadorAtual.setJogadasExtras(jogadorAtual.getJogadasExtras() - 1);
                jogarNovamente = true;
            }

        } while (jogarNovamente);
    }

    private void pausaEntreRodadas() {
        if (!verificaFim()) {
            inputGerenciador.pressionarEnter("Pressione ENTER para a próxima rodada...");
        }
    }

    public void start() {
        inputGerenciador.pressionarEnter("Pressione ENTER para iniciar a primeira rodada...");

        while (!verificaFim()) {
            numeroRodada++;

            Jogador atual = tabuleiro.getProximoJogador();
            tabuleiroRender.mostrarVezDoJogador(atual);

            if (atual.jogadorParalisado) {
                pausaEntreRodadas();
                continue;
            }
            processarRodadaExibirTabuleiro(atual);
            pausaEntreRodadas();
        }
        mostrarResultadoFinal();
    }
}