public class JogadorAzarado extends Jogador {
    public JogadorAzarado(String nome, Tabuleiro tabuleiro) {
        super(nome, tabuleiro, "Azarado");

    }

    public int[] lancarDados() {
        int dado1, dado2, soma;
        do {
            dado1 = lancarDado();
            dado2 = lancarDado();
            soma = dado1 + dado2;
        } while (soma > 6 );
        return new int[]{dado1, dado2};
    }
}