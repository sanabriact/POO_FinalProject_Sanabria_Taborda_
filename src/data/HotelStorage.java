package data;

import java.io.*;
import domain.*;
import java.io.FileWriter;
import java.io.IOException;

public class HotelStorage {

    public static void save(Hotel hotel, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(hotel); // Serialize hotel
            out.writeInt(Reservation.getNextNumber()); // Save reservation sequence
            out.writeInt(Employee.getNextNumber()); // Save employee sequence
            System.out.println("Hotel data saved successfully.");
        } catch (IOException exception) {
            System.err.println("An error occurred while saving hotel data."); // Error message
        }
    }

    public static Hotel load(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            Hotel hotel = (Hotel) in.readObject(); // Read hotel
            int nextNumRes = in.readInt(); // Load reservation counter
            int nextNumEmp = in.readInt(); // Load employee counter
            Reservation.setNextNumber(nextNumRes); // Restore reservation counter
            Employee.setNextNumber(nextNumEmp); // Restore employee counter
            return hotel; // Return loaded hotel
        } catch (IOException | ClassNotFoundException exception) {
            System.err.println("An error occurred while loading hotel data."); // Error message
            return null; // Return null if load fails
        }
    }

    public static void save(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content); // Write plain text content
            System.out.println("File saved successfully: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage()); // Error message
        }
    }
}
