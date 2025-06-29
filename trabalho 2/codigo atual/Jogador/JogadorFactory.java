public class JogadorFactory {

    private JogadorFactory() {
        // para garantir que ninguém tente instanciar segundo sonarqube
    }
    public static Jogador criarJogador(String tipo, String nome, Tabuleiro tabuleiro) {
        return switch (tipo.toLowerCase()) {
            case "sortudo" -> new JogadorSortudo(nome, tabuleiro);
            case "azarado" -> new JogadorAzarado(nome, tabuleiro);
            default -> new JogadorNormal(nome, tabuleiro);
        };
    }
}