import java.util.List;

public class AcaoSorte implements AcaoCasa {
    public void executarAcao(Jogador jogador, List<Jogador> jogadores) {
        if (!jogador.tipoJogador.equals("Azarado")) {
            int posicaoSorte = jogador.getPosicaoAtual() + 3;
            jogador.setPosicaoAtual(posicaoSorte);
            System.out.println(jogador.getNomeJogador() + " avançou 3 casas devido à sorte. Agora ele está na casa "
                    + jogador.getPosicaoAtual());
        }
    }
}
