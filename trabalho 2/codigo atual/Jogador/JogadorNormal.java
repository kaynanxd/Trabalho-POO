
public class JogadorNormal extends Jogador {

    public JogadorNormal(String nome, Tabuleiro tabuleiro) {
        super(nome, tabuleiro, "Normal");
    }

    public int[] lancarDados() {
        int dado1 = lancarDado();
        int dado2 = lancarDado();
        return new int[]{dado1, dado2};
    }
}