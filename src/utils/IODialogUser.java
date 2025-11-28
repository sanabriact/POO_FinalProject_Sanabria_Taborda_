package utils;

import javax.swing.JOptionPane;

public class IODialogUser implements IOUser {

    @Override
    public String inputText(String message) { // gets text input through dialog
        return JOptionPane.showInputDialog(null, message);
    }

    @Override
    public void writeLine(String message) { // shows a message dialog
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public int inputInt(String message) { // reads an int with validation using dialogs
        while (true) {
            try {
                return Integer.parseInt(inputText(message));
            } catch (Exception e) {
                writeLine("\n -You must enter an integer number.");
            }
        }
    }

    @Override
    public long inputLong(String message) { // reads a long with validation using dialogs
        while (true) {
            try {
                return Long.parseLong(inputText(message));
            } catch (Exception e) {
                writeLine("\n -You must enter an integer number.");
            }
        }
    }

    @Override
    public float inputFloat(String message) { // reads a float with validation using dialogs
        while (true) {
            try {
                return Float.parseFloat(inputText(message));
            } catch (Exception e) {
                writeLine("\n -You must enter an integer or decimal number.");
            }
        }
    }

    @Override
    public double inputDouble(String message) { // reads a double with validation using dialogs
        while (true) {
            try {
                return Double.parseDouble(inputText(message));
            } catch (Exception e) {
                writeLine("\n -You must enter an integer or decimal number.");
            }
        }
    }
}
