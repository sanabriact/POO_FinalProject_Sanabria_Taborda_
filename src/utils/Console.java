package utils;

import java.util.Scanner;

public class Console {
    
    private static final Scanner scanner = new Scanner(System.in);

    public static void writeLine(Object message) {
        System.out.println(message);
    }

    
    public static String readLine(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}

