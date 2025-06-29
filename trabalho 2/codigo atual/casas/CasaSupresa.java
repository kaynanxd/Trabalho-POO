
class CasaSupresa extends Casas {

    public CasaSupresa(int numero, String nomeCasa) {
        super(numero, nomeCasa, new AcaoTrocaTipo());
    }
}