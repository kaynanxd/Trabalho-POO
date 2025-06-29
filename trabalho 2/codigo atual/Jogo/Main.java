public class Main {
    public static void main(String args[]){
        Jogo jogo = new Jogo();
        jogo.configurarCasasEJogadores();
        jogo.printTabuleiro();
        jogo.start();
    }
}