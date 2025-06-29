import java.util.List;
import java.util.Random;

public class AcaoTrocaTipo implements AcaoCasa {

    private final Random random = new Random();

    public void executarAcao(Jogador jogador, List<Jogador> jogadores) {
        String tipoJogadorAtual = jogador.getTipoJogador();
        String[] tipos = {"Sortudo", "Azarado", "Normal"};
        String novoTipoJogador = tipoJogadorAtual;

        while (novoTipoJogador.equals(tipoJogadorAtual)) {
            novoTipoJogador = tipos[random.nextInt(tipos.length)];
        }

        jogador.setTipoJogador(novoTipoJogador);
        System.out.println("\n" + jogador.getNomeJogador() + " trocou de tipo: " + tipoJogadorAtual + " -> " + novoTipoJogador);

    }
}
