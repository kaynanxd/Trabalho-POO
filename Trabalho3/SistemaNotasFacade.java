import java.util.Scanner;

public class SistemaNotasFacade {
    private CadastroRespostas cadastroRespostas;
    private Scanner scanner;

    public SistemaNotasFacade() {
        this.cadastroRespostas = new CadastroRespostas();
        this.scanner = ScannerSingleton.getInstance();
    }

    public void criarArquivoRespostasDisciplina() {
        cadastroRespostas.criarArquivoDisciplina(scanner);
    }

    public void gerarResultadosDisciplina() {
        cadastroRespostas.gerarResultado(scanner);
    }

    public void gerarGabaritoOficial(String nomeDisciplina) {
        cadastroRespostas.gerarGabaritoOficial(scanner, nomeDisciplina);
    }
    public void visualizarGabaritoOficial(String nomeArquivo) {
        cadastroRespostas.visualizarArquivo(nomeArquivo);
    }

    public void visualizarRespostasAlunos(String nomeArquivo) {
        cadastroRespostas.visualizarArquivo(nomeArquivo);
    }
}   