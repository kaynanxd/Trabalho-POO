import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class TabuleiroRender {

    private static final Map<String, String> COLOR_MAP_JOGADORES = new HashMap<>();
    private static final String COR_PADRAO = "\u001B[0m";
    static {
        COLOR_MAP_JOGADORES.put("vermelho", "\u001B[31m");
        COLOR_MAP_JOGADORES.put("verde", "\u001B[32m");
        COLOR_MAP_JOGADORES.put("amarelo", "\u001B[33m");
        COLOR_MAP_JOGADORES.put("azul", "\u001B[34m");
        COLOR_MAP_JOGADORES.put("roxo", "\u001B[35m");
        COLOR_MAP_JOGADORES.put("ciano", "\u001B[36m");
    }

    private static final Map<String, String> COLOR_MAP_CASAS = new HashMap<>();
    static {
        COLOR_MAP_CASAS.put("simples", COR_PADRAO);      // Branco
        COLOR_MAP_CASAS.put("sorte", "\u001B[33m");      // Amarelo
        COLOR_MAP_CASAS.put("azar", "\u001B[31m");       // Vermelho
        COLOR_MAP_CASAS.put("prisao", "\u001B[34m");     // Azul
        COLOR_MAP_CASAS.put("reversa", "\u001B[35m");    // Roxo
        COLOR_MAP_CASAS.put("surpresa", "\u001B[36m");   // Ciano
        COLOR_MAP_CASAS.put("jogardenovo", "\u001B[32m"); // Verde
    }

    public void mostrarEstado(List<Casas> casas, List<Jogador> jogadores, int numeroRodada) {
        System.out.println("\n----- ESTADO DO TABULEIRO (Rodada " + numeroRodada + ") -----");

        int casasPorLinha = (int) Math.ceil(Math.sqrt(casas.size()));
        if (casasPorLinha == 0) casasPorLinha = 1;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < casas.size(); i++) {
            Casas casaAtual = casas.get(i);
            String numeroCasaStr = String.format("%02d", i);

            String corCasa = COLOR_MAP_CASAS.getOrDefault(casaAtual.getNomeCasa().toLowerCase(), COR_PADRAO);

            List<String> jogadoresNaCasa = new ArrayList<>();
            for (Jogador jogador : jogadores) {
                if (jogador.getPosicaoAtual() == i) {
                    String corJogador = COLOR_MAP_JOGADORES.getOrDefault(jogador.getCorJogador().toLowerCase(), COR_PADRAO);
                    String token = corJogador + jogador.getNomeJogador().substring(0, 1).toUpperCase() + COR_PADRAO;
                    jogadoresNaCasa.add(token);
                }
            }

            String casaDisplay;
            if (!jogadoresNaCasa.isEmpty()) {
                casaDisplay = "[" + corCasa + numeroCasaStr + COR_PADRAO + String.join("", jogadoresNaCasa) + "]";
            } else {
                casaDisplay = "[" + corCasa + numeroCasaStr + COR_PADRAO + "  ]";
            }

            sb.append(casaDisplay);

            if ((i + 1) % casasPorLinha == 0 || i == casas.size() - 1) {
                sb.append("\n");
            } else {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());

        System.out.println("\nJogadores:");
        for (Jogador jogador : jogadores) {
            String corJogador = COLOR_MAP_JOGADORES.getOrDefault(jogador.getCorJogador().toLowerCase(), COR_PADRAO);
            System.out.println("  " + corJogador + jogador.getNomeJogador().substring(0, 1).toUpperCase() + COR_PADRAO + " = " +
                    corJogador + jogador.getNomeJogador() + COR_PADRAO +
                    " (" + jogador.getCorJogador() + " )" + " (" + jogador.getTipoJogador() + ")" + " na casa " + jogador.getPosicaoAtual());
        }

        System.out.println("-------------------------");
        System.out.print(COR_PADRAO);
    }

    public void mostrarResultadoFinal(List<Jogador> jogadores, int ultimaCasaDoTabuleiro) {
        System.out.println("----- FIM DE JOGO -----");
        for (Jogador jogador : jogadores) {
            System.out.println(jogador.getNomeJogador() + " (" + jogador.getCorJogador() + ") andou " +
                    jogador.getCasasAndadas() + " casas no total. Casa final: " + jogador.getPosicaoAtual());
        }

        for (Jogador jogador : jogadores) {
            if (jogador.getPosicaoAtual() >= ultimaCasaDoTabuleiro) {
                System.out.println("🎉 Vencedor: " + jogador.getNomeJogador() + " (" + jogador.getCorJogador() + ")" + " ( Total Moedas :" + jogador.getTotalMoedas()+ ")");
                break;
            }
        }
    }
    public void mostrarVezDoJogador(Jogador jogador) {
        String cor = COLOR_MAP_JOGADORES.getOrDefault(jogador.getCorJogador().toLowerCase(), "");
        System.out.println("\n--- Vez de: " + cor + jogador.getNomeJogador() + "[" + jogador.getCorJogador() + "] ---"+"\u001B[0m");
    }
}