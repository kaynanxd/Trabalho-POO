public class JogadorSortudo extends Jogador {
    public JogadorSortudo(String nome, Tabuleiro tabuleiro) {
        super(nome, tabuleiro, "Sortudo");
    }

    public int[] lancarDados() {
        int dado1, dado2, soma;
        do {
            dado1 = lancarDado();
            dado2 = lancarDado();
            soma = dado1 + dado2;
        } while (soma < 7);
        return new int[]{dado1, dado2};
    }
}