package utils;

import javax.swing.JOptionPane;

public class IODialogUser implements IOUser{
    @Override
    public String inputText(String message){
        return JOptionPane.showInputDialog(null, message);
    }

    @Override
    public void writeLine(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    public int inputInt(String message){
        return Integer.parseInt(inputText(message));
    }

    public float inputFloat(String message){
        return Float.parseFloat(inputText(message));
    }

    public double inputDouble(String message){
        return Double.parseDouble(inputText(message));
    }
}
