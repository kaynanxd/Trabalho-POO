import java.util.List;

public class AcaoParalisar implements AcaoCasa {
    public void executarAcao(Jogador jogador, List<Jogador> jogadores) {
        System.out.print("\nEle foi paralisado.\n");
        jogador.setRodadasParalisado(1);
    }
}
