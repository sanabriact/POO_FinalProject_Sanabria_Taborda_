package utils;

import java.util.Scanner;

public class IOConsoleUser implements IOUser {

    private static Scanner input = new Scanner(System.in); // shared scanner for console input

    @Override
    public String inputText(String message) { // reads text input
        System.out.println(message);
        return input.nextLine();
    }

    @Override
    public void writeLine(String message) { // prints a line to console
        System.out.println(message);
    }

    @Override
    public int inputInt(String message) { // reads an int with validation
        while (true) {
            try {
                return Integer.parseInt(inputText(message));
            } catch (Exception e) {
                writeLine("\n -You must enter an integer number.");
            }
        }
    }

    @Override
    public long inputLong(String message) { // reads a long with validation
        while (true) {
            try {
                return Long.parseLong(inputText(message));
            } catch (Exception e) {
                writeLine("\n -You must enter an integer number.");
            }
        }
    }

    @Override
    public float inputFloat(String message) { // reads a float with validation
        while (true) {
            try {
                return Float.parseFloat(inputText(message));
            } catch (Exception e) {
                writeLine("\n -You must enter an integer or decimal number.");
            }
        }
    }

    @Override
    public double inputDouble(String message) { // reads a double with validation
        while (true) {
            try {
                return Double.parseDouble(inputText(message));
            } catch (Exception e) {
                writeLine("\n- You must enter an integer or decimal number.");
            }
        }
    }
}
