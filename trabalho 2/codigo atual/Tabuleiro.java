import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private static Tabuleiro instancia;

    private List<Casas> casas;
    private List<Jogador> jogadores;
    private int vezAtual;

    private Tabuleiro() {
        casas = new ArrayList<>();
        jogadores = new ArrayList<>();
        vezAtual = 0;
    }

    public static Tabuleiro getInstancia() {
        if (instancia == null) {
            instancia = new Tabuleiro();
        }
        return instancia;
    }

    public void adicionarCasa(Casas casa) {
        casas.add(casa);
    }

    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public void moverJogador(Jogador jogador, int casasAndadas) {
        int novaPosicao = jogador.getPosicao() + casasAndadas;

        if (novaPosicao >= casas.size()) {
            novaPosicao = casas.size() - 1;
        }

        jogador.setPosicao(novaPosicao);
        jogador.incrementarJogadas();

        System.out.println(jogador.getNome() + " foi para a casa " + novaPosicao);

        casas.get(novaPosicao).aplicarRegra(jogador, jogadores);
    }

    public Jogador getProximoJogador() {
        Jogador jogador = jogadores.get(vezAtual);
        vezAtual = (vezAtual + 1) % jogadores.size();
        return jogador;
    }

    public boolean verificaFim() {
        for (Jogador jogador : jogadores) {
            if (jogador.getPosicao() >= casas.size() - 1) {
                return true;
            }
        }
        return false;
    }

    public void mostrarEstado() {
        System.out.println("----- ESTADO DO TABULEIRO -----");
        for (Jogador jogador : jogadores) {
            System.out.println(jogador.getCor() + " na casa " + jogador.getPosicao());
        }
    }

    public void mostrarResultadoFinal() {
        System.out.println("----- FIM DE JOGO -----");
        for (Jogador jogador : jogadores) {
            System.out.println(jogador.getNome() + " (" + jogador.getCor() + ") fez " +
                    jogador.getNumJogadas() + " jogadas. Casa final: " + jogador.getPosicao());
        }

        for (Jogador jogador : jogadores) {
            if (jogador.getPosicao() >= casas.size() - 1) {
                System.out.println("🎉 Vencedor: " + jogador.getNome() + " (" + jogador.getCor() + ")");
                break;
            }
        }
    }
}