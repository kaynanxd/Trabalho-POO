import java.util.List;

public class AcaoReversa implements AcaoCasa {

    public void executarAcao(Jogador jogadorAtual, List<Jogador> jogadores) {
        Jogador menorPosJogador;
        int menorPosicao;
        menorPosJogador = null;
        menorPosicao = Integer.MAX_VALUE;

        for (Jogador outroJogador : jogadores) {
            if (outroJogador != jogadorAtual &&
                    outroJogador.getPosicaoAtual() < jogadorAtual.getPosicaoAtual() &&
                    outroJogador.getPosicaoAtual() < menorPosicao) {

                menorPosicao = outroJogador.getPosicaoAtual();
                menorPosJogador = outroJogador;
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
