package utils;

import javax.swing.JOptionPane;

public class IODialogUser implements IOUser {
    @Override
    public String inputText(String message) {
        {
            return JOptionPane.showInputDialog(null, message);
        }
    }

    @Override
    public void writeLine(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public int inputInt(String message) {
        while (true) {
            try {
                return Integer.parseInt(inputText(message));
            } catch (Exception e) {
                writeLine("\n -You must enter an integer number.");
            }
        }
    }

    public long inputLong(String message) {
        while (true) {
            try {
                return Long.parseLong(inputText(message));
            } catch (Exception e) {
                writeLine("\n -You must enter an integer number.");
            }
        }
    }

    public float inputFloat(String message) {
        while (true) {
            try {
                return Float.parseFloat(inputText(message));
            } catch (Exception e) {
                writeLine("\n -You must enter an integer or decimal number.");
            }
        }
    }

    public double inputDouble(String message) {
        while (true) {
            try {
                return Double.parseDouble(inputText(message));
            } catch (Exception e) {
                writeLine("\n -You must enter an integer or decimal number.");
            }
        }
    }
}
