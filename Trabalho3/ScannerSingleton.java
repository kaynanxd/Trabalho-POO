import java.util.Scanner;

public class ScannerSingleton {
    private static Scanner instance;

    private ScannerSingleton() {
    }

    public static synchronized Scanner getInstance() {
        if (instance == null) {
            instance = new Scanner(System.in);
        }
        return instance;
    }

    public static synchronized void closeInstance() {
        if (instance != null) {
            instance.close();
            instance = null;
        }
    }
}