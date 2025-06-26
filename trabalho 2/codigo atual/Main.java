
public class Main {
    public static void main(String args[]){
        Jogo jogo = new Jogo();
        int numCasas = jogo.lerNumeroCasas();;
        int numJogadores = jogo.lerNumeroJogadores();
        jogo.configTabuleiro(numCasas);
        jogo.config(numJogadores);
        jogo.printTabuleiro();
        jogo.start();
    }
}