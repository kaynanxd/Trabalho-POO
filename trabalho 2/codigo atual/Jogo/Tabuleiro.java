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
        int novaPosicao = jogador.getPosicaoAtual() + casasAndadas;

        if (novaPosicao >= casas.size()) {
            novaPosicao = casas.size() - 1;
        }
        if (novaPosicao < 0) {
            novaPosicao = 0;
        }

        jogador.setPosicaoAtual(novaPosicao);
        jogador.incrementarCasasAndadas(casasAndadas);

        System.out.println(jogador.getNomeJogador() + " foi para a casa " + novaPosicao);

        casas.get(novaPosicao).acao(jogador, jogadores);
    }

    public Jogador getProximoJogador() {
        Jogador jogador = jogadores.get(vezAtual);

        if (jogador.jogadorParalisado) {
            jogador.setRodadasParalisado(jogador.rodadasParalisado - 1);
            System.out.println(jogador.getNomeJogador() + " está paralisado nesta rodada. Faltam " + jogador.rodadasParalisado + " rodadas.");
            vezAtual = (vezAtual + 1) % jogadores.size();
            return getProximoJogador();
        }
        vezAtual = (vezAtual + 1) % jogadores.size();
        return jogador;
    }

    public List<Casas> getCasas() {
        return casas;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public int getTamanhoTabuleiro() {
        return casas.size();
    }
}