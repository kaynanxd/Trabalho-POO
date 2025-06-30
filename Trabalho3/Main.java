import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = ScannerSingleton.getInstance();
        SistemaNotasFacade sistemaFacade = new SistemaNotasFacade();
        Menu menu = new Menu(sistemaFacade, scanner);
        menu.exibirMenu();
        ScannerSingleton.closeInstance();
    }
}