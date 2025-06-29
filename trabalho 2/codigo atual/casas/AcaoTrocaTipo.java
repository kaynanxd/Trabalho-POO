import java.util.List;
import java.util.Random;

public class AcaoTrocaTipo implements AcaoCasa {
    public void executarAcao(Jogador jogador, List<Jogador> jogadores) {
        String tipoJogadorAtual = jogador.getTipoJogador();
        String[] tipos = {"Sortudo", "Azarado", "Normal"};
        String novoTipoJogador = tipoJogadorAtual;

        while (novoTipoJogador.equals(tipoJogadorAtual)) {
            novoTipoJogador = tipos[new Random().nextInt(tipos.length)];
        }

        jogador.setTipoJogador(novoTipoJogador);
        System.out.println("\n" + jogador.getNomeJogador() + " trocou de tipo: " + tipoJogadorAtual + " -> " + novoTipoJogador);

    }
}
