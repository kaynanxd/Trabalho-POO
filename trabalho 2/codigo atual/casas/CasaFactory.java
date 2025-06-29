public class CasaFactory {
    public static Casas criarCasa(int numero, String tipo) {
        switch (tipo.toLowerCase()) {
            case "sorte":
                return new CasaSorte(numero, "Sorte");
            case "azar":
                return new CasaAzar(numero, "Azar");
            case "prisao":
                return new CasaPrisao(numero, "Prisao");
            case "reversa":
                return new CasaReversa(numero, "Reversa");
            case "surpresa":
                return new CasaSupresa(numero, "Surpresa");
            case "jogardenovo":
                return new CasaJogarDeNovo(numero, "Jogardenovo");
            case "simples":
                return new CasaSimples(numero, "Simples");
            default:
                return new CasaSimples(numero, "Simples");
        }
    }
}