public class AlunoFactory {
    public static Aluno criarAluno(String nome, String respostas) {
        return new Aluno(nome, respostas, 0);
    }
}