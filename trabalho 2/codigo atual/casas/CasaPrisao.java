class CasaPrisao extends Casas {
    public CasaPrisao(int numero, String nomeCasa) {
        super(numero, nomeCasa, new AcaoParalisar());
    }
}