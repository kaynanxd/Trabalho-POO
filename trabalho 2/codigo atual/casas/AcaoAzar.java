import java.util.List;

public class AcaoAzar implements AcaoCasa {
    public void executarAcao(Jogador jogador, List<Jogador> jogadores) {
        if (!jogador.tipoJogador.equals("Azarado")) {
            int posicaoSorte = jogador.getPosicaoAtual() - 3;
            jogador.setPosicaoAtual(posicaoSorte);
            System.out.println(jogador.getNomeJogador() + " retornou 3 casas devido ao azar. Agora ele está na casa "
                    + jogador.getPosicaoAtual());
        }
    }
}
