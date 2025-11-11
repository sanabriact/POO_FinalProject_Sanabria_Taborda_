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
        return Integer.parseInt(inputText(message));
    }

    @Override
    public long inputLong(String message){
        return Long.parseLong(inputText(message));
    }

    @Override
    public float inputFloat(String message){
        return Float.parseFloat(inputText(message));
    }

    @Override
    public double inputDouble(String message){
        return Double.parseDouble(inputText(message));
    }




}
