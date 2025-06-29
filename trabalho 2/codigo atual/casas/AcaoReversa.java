import java.util.List;

public class AcaoReversa implements AcaoCasa {
    private Jogador menorPosJogador;
    private int menorPosicao;

    public void executarAcao(Jogador jogadorAtual, List<Jogador> jogadores) {
        menorPosJogador = null;
        menorPosicao = Integer.MAX_VALUE;

        for (Jogador j : jogadores) {
            if (j != jogadorAtual && j.getPosicaoAtual() < jogadorAtual.getPosicaoAtual()) {
                if (j.getPosicaoAtual() < menorPosicao) {
                    menorPosicao = j.getPosicaoAtual();
                    menorPosJogador = j;
                }
            }
        }

        if (menorPosJogador != null) {
            int posTemp = jogadorAtual.getPosicaoAtual();
            jogadorAtual.setPosicaoAtual(menorPosJogador.getPosicaoAtual());
            menorPosJogador.setPosicaoAtual(posTemp);
            System.out.println(jogadorAtual.getNomeJogador() + " trocou de lugar com " +
                    menorPosJogador.getNomeJogador() + " e agora está na posição " + menorPosJogador.getPosicaoAtual());
        } else {
            System.out.println("Nenhum jogador está atrás de " + jogadorAtual.getNomeJogador());
        }
    }

}
