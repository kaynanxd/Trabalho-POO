public class OrdenacaoStrategyFactory {
    public static OrdenacaoStrategy getStrategy(String tipo) {
        if ("alfabetica".equalsIgnoreCase(tipo)) {
            return new OrdenacaoAlfabeticaStrategy();
        } else if ("porNota".equalsIgnoreCase(tipo)) {
            return new OrdenacaoPorNotaStrategy();
        } else {
            throw new IllegalArgumentException("Tipo de ordenação inválido: " + tipo);
        }
    }
}