package ui;

import java.io.Serializable;

import domain.Guest;
import domain.Hotel;
import utils.IOConsoleUser;

public class Main {
    private static String FILE_NAME = "hotel.dat";
    private static IOConsoleUser keyboard = new IOConsoleUser();
    /*PARA TESTEAR*/static Hotel hotel = new Hotel("PUTICLUB", "Cali", "mario.bravoo@gmail.com", 3015326737L);

    public static void main(String[] args) {
        boolean menu = true;
        int option;
        
        while (menu) {
            // HOTEL MENU
            showHotelMenu();
            option = keyboard.inputInt("- Enter an option: ");
            switch(option){
                case 1 -> {

                }
                case 2 ->{
                    //Administrator menu
                    showAdminMenu();
                    option = keyboard.inputInt("- Enter an option: ");
                    switch(option){
                        case 1 -> {
                            //Reservations
                            showReservationMenu();
                            option = keyboard.inputInt(" - Enter an option: ");
                            switch (option) {
                                case 1 -> {
                                    //Add reservation

                                }
                                case 2 -> {

                                }
                            
                                default ->{

                                }
                            }
                        }
                        case 2 -> {
                            //Reservation record
                        }
                        //case 3 ->{//Employees}
                        default ->{}
                    }
                }
                default ->{
                    menu = false;
                }
            }
        }
    }

    private static void showHotelMenu() {
        System.out.println("- - - - - - - - HOTEL " + hotel.getHotelName().toUpperCase()+ " MENU - - - - - - - -");
        System.out.println("(1) Hotel Data.");
        System.out.println("(2) Administrator menu.");
        System.out.println("(3) Save and exit.");
    }

    private static void showAdminMenu(){
        System.out.println("- - - - - - - - ADMINISTRATOR MENU - - - - - - - - ");
        System.out.println("(1) Reservations.");
        System.out.println("(2) Reservation record.");
        System.out.println("(3) Go back.");
        //System.out.println("(4) Employees.");

    }

    private static void showReservationMenu(){
        System.out.println("- - - - - - - - RESERVATION MENU - - - - - - - - ");
        System.out.println("(1) Add reservation.");
        System.out.println("(2) Show active Reservations.");
        System.out.println("(3) Change reservation data.");
        System.out.println("(4) Go back.");
    }

    private static void addReservation(){
        String guestName = keyboard.inputText("- Enter guest name: ");
        String guestEmail = keyboard.inputText("- Enter guest's email adress: ");
        long phoneNumber = keyboard.inputLong("- Enter guest's phone number: ");
        int id = keyboard.inputInt("- Enter guest ID: ");
        Guest guest = new Guest(guestName, guestEmail, phoneNumber, id);
    }

    
}
