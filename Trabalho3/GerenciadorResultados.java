import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GerenciadorResultados {

    public void calcularNotasAlunos(List<Aluno> listaAlunos, String gabarito) {
        if (gabarito == null || gabarito.length() != 10) {
            System.err.println("Gabarito inválido. As notas não podem ser calculadas.");
            return;
        }

        for (Aluno aluno : listaAlunos) {
            String respostasAlunos = aluno.getRespostas();

            if (respostasAlunos == null || respostasAlunos.length() != 10 || !respostasAlunos.matches("[VF]{10}")) {
                System.out.println("Atenção: As respostas do aluno '" + aluno.getNomeAluno() + "' são inválidas ('" + respostasAlunos + "'). Nota atribuída: 0.");
                aluno.setNota(0);
                continue;
            }

            int acertos = 0;
            for (int j = 0; j < respostasAlunos.length(); j++) {
                if (respostasAlunos.charAt(j) == gabarito.charAt(j)) {
                    acertos++;
                }
            }
            aluno.setNota(acertos);
        }
    }
}