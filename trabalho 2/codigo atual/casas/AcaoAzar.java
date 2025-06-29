

import java.util.List;

public class AcaoAzar implements AcaoCasa {
    public void executarAcao(Jogador jogador, List<Jogador> jogadores) {
        if (!jogador.tipoJogador.equals("Sortudo")) {
            int posicaoAzar = jogador.getPosicaoAtual() - 3;
            jogador.setPosicaoAtual(posicaoAzar);
            System.out.println(jogador.getNomeJogador() + " retornou 3 casas devido ao azar, Agora ele está na casa "
                    + jogador.getPosicaoAtual());
        }
    }
}
