package data;
import java.io.*;
import domain.*;
import java.io.FileWriter;
import java.io.IOException;


public class HotelStorage {
    
    public static void save(Hotel hotel, String filename){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))){
            out.writeObject(hotel);
            out.writeInt(Reservation.getNextNumber());
            out.writeInt(Employee.getNextNumber());
            System.out.println("Hotel data saved succesfully.");
        }catch(IOException exception){
            System.err.println("an error occurred while saving hotel data.");
        }
    }

    public static Hotel load(String filename){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
            Hotel hotel = (Hotel) in.readObject();
            int nextNumRes = in.readInt();
            int nextNumEmp = in.readInt();
            Reservation.setNextNumber(nextNumRes);
            Employee.setNextNumber(nextNumEmp);
            return hotel;
        }catch(IOException | ClassNotFoundException exception){
            System.err.println("an error occurred while loading hotel data.");
            return null;
        }
    }

    public static void save(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
            System.out.println("File saved successfully: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}