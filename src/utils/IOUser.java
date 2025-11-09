package utils;

public interface IOUser {
    public abstract String inputText(String title);
    public abstract void writeLine(String message);
    public abstract int inputInt(String title);
    public abstract long inputLong(String title);
    public abstract float inputFloat(String title);
    public abstract double inputDouble(String title);

}
