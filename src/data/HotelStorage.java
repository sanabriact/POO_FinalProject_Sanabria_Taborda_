package data;
import java.io.*;
import domain.*;

public class HotelStorage {
    
    public static void save(Hotel hotel, String filename){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))){
            out.writeObject(hotel);
            System.out.println("Hotel data saved succesfully.");
        }catch(IOException exception){
            System.err.println("an error occurred while saving hotel data.");
        }
    }

    public static Hotel load(String filename){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
            return (Hotel) in.readObject();
        }catch(IOException | ClassNotFoundException exception){
            System.err.println("an error occurred while loading hotel data.");
            return null;
        }
    }
}