package utils;

import java.util.Scanner;

public class IOConsoleUser implements IOUser{
    private static Scanner input = new Scanner(System.in);

    @Override
    public String inputText(String message){
        System.out.println(message);
        return input.nextLine();
    }

    @Override
    public void writeLine(String message){
        System.out.println(message);
    }

    @Override
    public int inputInt(String message){
        while (true) {
            try {
                return Integer.parseInt(inputText(message));
            } catch (Exception e) {
                writeLine("\n -You must enter an integer number.");
            }
        }

    }

    @Override
    public long inputLong(String message){
        while (true) {
            try {
                return Long.parseLong(inputText(message));
            } catch (Exception e) {
                writeLine("\n -You must enter an integer number.");
            }
        }
    }

    @Override
    public float inputFloat(String message){
        while (true) {
            try {
                return Float.parseFloat(inputText(message));
            } catch (Exception e) {
                writeLine("\n -You must enter an integer or decimal number.");
            }
        }
    }

    @Override
    public double inputDouble(String message){
        while (true) {
            try {
                return Double.parseDouble(inputText(message));
            } catch (Exception e) {
                writeLine("\n- You must enter an integer or decimal number.");
            }
        }
    }




}
