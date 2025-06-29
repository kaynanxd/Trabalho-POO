public class CasaJogarDeNovo extends Casas {
    public CasaJogarDeNovo(int numero, String nomeCasa) {
        super(numero, nomeCasa, new AcaoJogarDeNovo());
    }
}
