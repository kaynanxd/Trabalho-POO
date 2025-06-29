import java.util.List;
public class AcaoJogarDeNovo implements AcaoCasa {
    public void executarAcao(Jogador jogador, List<Jogador> jogadores) {
        jogador.setJogadasExtras(jogador.getJogadasExtras() + 1);
        System.out.println(jogador.getNomeJogador() + " ganhou uma jogada extra!");
    }
}
