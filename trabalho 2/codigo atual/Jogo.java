import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogo {

    private Tabuleiro tabuleiro;
    private boolean modoDebug = false;
    private Scanner sc = new Scanner(System.in);

    public Jogo() {
        tabuleiro = Tabuleiro.getInstancia();
    }

    public int lerNumeroCasas() {
        System.out.print("Digite o número de casas do tabuleiro: ");
        return sc.nextInt();
    }

    public int lerNumeroJogadores() {
        System.out.print("Digite o número de jogadores (até 6): ");
        return sc.nextInt();
    }

    public void configTabuleiro(int numCasas) {
        for (int i = 0; i < numCasas; i++) {
            System.out.println("Casa " + i + " - Tipo (simples, sorte, azar, prisao, reversa, surpresa, jogadenovo): ");
            String tipo = sc.next();
            Casas casa = CasaFactory.criarCasa(i, tipo);
            tabuleiro.adicionarCasa(casa);
        }
    }

    public void config(int numJogadores) {
        for (int i = 0; i < numJogadores; i++) {
            System.out.println("Jogador " + (i + 1));
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("Cor: ");
            String cor = sc.nextLine();
            System.out.print("Tipo (normal/sortudo/azarado) = 1 ,2 ,3 : ");
            String tipo = sc.nextLine();

            Jogador jogador = JogadorFactory.criarJogador(nome, cor, tipo);
            tabuleiro.adicionarJogador(jogador);
        }
        System.out.print("Deseja ativar o modo debug? (s/n): ");
        modoDebug = sc.nextLine().equalsIgnoreCase("s");
    }

    public void printTabuleiro() {
        tabuleiro.mostrarEstado();
    }

    public void start() {
        while (!tabuleiro.verificaFim()) {
            Jogador atual = tabuleiro.getProximoJogador();
            System.out.println("\nVez de: " + atual.getNome() + " [" + atual.getCor() + "]");

            int casas;
            if (modoDebug) {
                System.out.print("Digite a casa de destino: ");
                casas = sc.nextInt() - atual.getPosicao();
            } else {
                Dados dados = new Dados();
                dados.rolar();
                dados.exibir();

                casas = dados.getSoma();

                while (dados.saoIguais()) {
                    System.out.println("🎲 Dados iguais! " + atual.getNome() + " joga novamente.");
                    dados.rolar();
                    dados.exibir();
                    casas += dados.getSoma();
                }
            }
            tabuleiro.moverJogador(atual, casas);
        }
        tabuleiro.mostrarResultadoFinal();
    }
}
