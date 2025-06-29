

public class CasaReversa extends Casas {
    public CasaReversa(int numero, String nomeCasa) {
        super(numero, nomeCasa, new AcaoReversa());
    }
}
