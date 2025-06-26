import java.util.Random;
import java.util.Scanner;

import java.util.Random;

public class Dados {
    private int dado1, dado2, soma;
    private Random random;

    public Dados() {
        this.random = new Random();
    }

    public void rolar() {
        dado1 = random.nextInt(6) + 1;
        dado2 = random.nextInt(6) + 1;
        soma = dado1 + dado2;
    }

    public boolean saoIguais() {
        return dado1 == dado2;
    }

    public int getSoma() {
        return soma;
    }

    public int getDado1() {
        return dado1;
    }

    public int getDado2() {
        return dado2;
    }

}
