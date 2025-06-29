import java.util.Random;

public abstract class Jogador {
    protected String nomeJogador;
    protected String corJogador;
    protected int posicaoAtual;
    protected String tipoJogador;
    protected boolean jogadorParalisado = false;
    protected int rodadasParalisado = 0;
    protected int casasAndadas;
    protected Tabuleiro tabuleiro;
    protected final Random random = new Random();
    protected int jogadasExtras; // Novo atributo adicionado

    public Jogador(String nomeJogador, Tabuleiro tabuleiro, String tipoJogador) {
        this.nomeJogador = nomeJogador;
        this.posicaoAtual = 0;
        this.casasAndadas = 0;
        this.tabuleiro = tabuleiro;
        this.corJogador = "none";
        this.jogadasExtras = 0;
        this.tipoJogador = tipoJogador;
    }

    public abstract int[] lancarDados();

    protected int lancarDado() {
        return random.nextInt(6) + 1;  // Valor entre 1-6
    }

    // Getters
    public String getNomeJogador() { return nomeJogador; }
    public int getPosicaoAtual() { return posicaoAtual; }
    public String getTipoJogador() {return tipoJogador;}
    public String getCorJogador() {return corJogador;}
    public int getCasasAndadas(){return casasAndadas;}
    //Setters
    public void setCorJogador(String corJogador){this.corJogador = corJogador;}
    public void setTipoJogador(String tipoJogador) {this.tipoJogador = tipoJogador;}
    public void setPosicaoAtual(int posicao) { this.posicaoAtual = posicao; }
    public void setRodadasParalisado(int rodadas) {
        this.rodadasParalisado = rodadas;
        this.jogadorParalisado = rodadas > 0;
    }
    public int getJogadasExtras() {return jogadasExtras;}
    public void setJogadasExtras(int jogadasExtras) {this.jogadasExtras = jogadasExtras;}
    public void incrementarCasasAndadas(int quantidade) { this.casasAndadas += quantidade; }
}