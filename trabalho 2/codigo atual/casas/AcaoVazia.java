import java.util.List;
public class AcaoVazia implements AcaoCasa {
    public void executarAcao(Jogador jogador, List<Jogador> jogadores) {
        jogador.incrementarMoedas(1);
    }
}
