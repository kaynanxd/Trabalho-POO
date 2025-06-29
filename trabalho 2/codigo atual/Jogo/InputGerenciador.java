import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputGerenciador {
    private final Scanner scanner;

    public InputGerenciador(Scanner scanner) {
        this.scanner = scanner;
    }


    public int lerInteiro(String prompt, int min, int max, String errorMessage) {
        int value = 0;
        while (true) {
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.println(errorMessage);
                }
            } catch (InputMismatchException _) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                scanner.next();
            }
        }
        scanner.nextLine();
        return value;
    }

    public String lerNomeVazio(String prompt, String errorMessage) {
        String input = "";
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                break;
            } else {
                System.out.println(errorMessage);
            }
        }
        return input;
    }

    public String lerListaInteiros(String prompt, List<String> validOptions, String errorMessage) {
        String input = "";
        while (true) {
            System.out.print(prompt +
                    " (" + String.join(", ", validOptions) + "): ");
            input = scanner.nextLine().trim().toLowerCase();
            if (validOptions.contains(input)) {
                break;
            } else {
                System.out.println(errorMessage);
            }
        }
        return input;
    }

    public void pressionarEnter(String prompt) {
        System.out.println(prompt);
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        scanner.nextLine();
    }

    public boolean lerSimOuNao(String prompt) {
        String input = "";
        while (true) {
            System.out.print(prompt + " (s/n): ");
            input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("s")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println("Resposta inválida. Por favor, digite 's' para sim ou 'n' para não.");
            }
        }
    }
    public String lerLinhaVazia(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    public Scanner getScanner() {
        return scanner;
    }
}