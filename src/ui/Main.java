package ui;

import java.io.Serializable;

import domain.Hotel;
import utils.IOConsoleUser;

public class Main {
    private static String FILE_NAME = "hotel.dat";
    private static IOConsoleUser keyboard = new IOConsoleUser();

    public static void main(String[] args) {
        boolean menu = true;
        int option;

        while (menu) {
            showHotelMenu();
            option = keyboard.inputInt("- Enter an option:");
            switch(option){
                case 1 -> {

                }
                case 2 ->{

                }
                default ->{
                    menu = false;
                }
            }
        }
    }

    private static void showHotelMenu() {
        System.out.println("- - - - - - - - HOTEL MENU - - - - - - - -");
        System.out.println("(1) Hotel Data.");
        System.out.println("(2) Administrator menu.");
        System.out.println("(3) Save and exit.");
    }
}
