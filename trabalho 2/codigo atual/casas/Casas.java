import java.util.List;

public abstract class Casas {
    protected int numero;
    private String nomeCasa;
    private AcaoCasa acao;

    public Casas (int numero, String nomeCasa, AcaoCasa acao) {
        this.numero = numero;
        this.nomeCasa = nomeCasa;
        this.acao = acao;
    }

    public int getNumero() { return numero; }
    public String getNomeCasa() { return nomeCasa; }

    public void acao(Jogador jogador, List<Jogador> jogadores) {
        acao.executarAcao(jogador, jogadores);
    }

}
