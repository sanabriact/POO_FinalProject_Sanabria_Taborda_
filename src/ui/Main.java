package ui;

import data.HotelStorage;
import domain.*;
import utils.IOConsoleUser;

public class Main {
    private static String FILE_NAME = "hotel.dat";
    private static IOConsoleUser keyboard = new IOConsoleUser();

    /* PARA TESTEAR */static Hotel hotel = new Hotel("PUTICLUB", "Cali", "mario.bravoo@gmail.com", 3015326737L);
    
    public static void main(String[] args) {
        /* PARA TESTEAR */hotel.addRoom(new Room(101, 2, 1, "Basic"));
        /* PARA TESTEAR */hotel.addRoom(new Room(102, 2, 1, "Medium"));
        /* PARA TESTEAR */hotel.addRoom(new Room(104, 2, 1, "Suit"));
        /* PARA TESTEAR */
        boolean menu = true;
        int option;

        HotelStorage.load(FILE_NAME);
        while (menu) {
            // HOTEL MENU
            showHotelMenu();
            option = keyboard.inputInt("- Enter an option: ");
            switch (option) {
                case 1 -> {

                }
                case 2 -> {
                    // Administrator menu
                    showAdminMenu();
                    option = keyboard.inputInt("- Enter an option: ");
                    switch (option) {
                        case 1 -> {
                            // Reservations
                            showReservationMenu();
                            option = keyboard.inputInt(" - Enter an option: ");
                            switch (option) {
                                case 1 -> {
                                    // Add reservation
                                    addReservation();
                                }
                                case 2 -> {
                                    //Show available rooms
                                    showAvailableRooms();
                                }

                                case 3 -> {
                                    //Disable reservation
                                    disableReservation();
                                }
                                case 4 -> {

                                }

                                default -> {

                                }
                            }
                        }
                        case 2 -> {
                            // Reservation record
                        }
                        // case 3 ->{//Employees}
                        default -> {
                        }
                    }
                }
                default -> {
                    menu = false;
                }
            }
        }

        HotelStorage.save(hotel, FILE_NAME);

    }

    private static void showHotelMenu() {
        System.out.println("- - - - - - - - HOTEL MENU - - - - - - - -");
        System.out.println("(1) Hotel Data.");
        System.out.println("(2) Administrator menu.");
        System.out.println("(3) Save and exit.");
    }

    private static void showAdminMenu() {
        System.out.println("- - - - - - - - ADMINISTRATOR MENU - - - - - - - - ");
        System.out.println("(1) Reservations.");
        System.out.println("(2) Reservation record.");
        System.out.println("(3) Go back.");
        // System.out.println("(4) Employees.");

    }

    private static void showReservationMenu() {
        System.out.println("- - - - - - - - RESERVATION MENU - - - - - - - - ");
        System.out.println("(1) Add reservation.");
        System.out.println("(2) Show active Reservations.");
        System.out.println("(3) Disable reservation.");
        System.out.println("(4) Change reservation data.");
        System.out.println("(5) Go back.");
    }

    private static void addReservation() {
        String guestName = keyboard.inputText("- Enter guest name: ");
        String guestEmail = keyboard.inputText("- Enter guest's email adress: ");
        long phoneNumber = keyboard.inputLong("- Enter guest's phone number: ");
        long id = keyboard.inputLong("- Enter guest ID: ");
        Guest guest = new Guest(guestName, guestEmail, phoneNumber, id);

        // Mostrar lista de habitaciones disponibles
        showAvailableRooms();
        boolean condition = false;

        // Validar que la habitacion este disponible, si no dar un mensaje de que está
        // ocupada.
        do {
            int roomNum = keyboard.inputInt("- Enter the room number that the customer requested: ");

            for (Room room : hotel.getAvailableRooms()) {
                if (roomNum == room.getRoomNum()) {
                    condition = true;
                }
            }

            if (condition == true) {
                Room room = hotel.findRoomById(roomNum);
                Reservation reservation = new Reservation(guest, room);
                hotel.addReservation(reservation);
                System.out.println("- Reservation created successfully");
            } else {
                System.out.println("The room isn't available. Try again.");
            }

        } while (condition == false);
    }

    private static void showAvailableRooms() {
        System.out.println("- - - - - - - - AVAILABLE ROOMS - - - - - - - -");
        for (Room room : hotel.getAvailableRooms()) {
            System.out.println(room.toString());
        }

    }
    private static void disableReservation(){
        long id =keyboard.inputLong("- Enter guest's ID: ");
        for(Reservation reservation: hotel.getReservationList()){
            if(id == reservation.getGuest().getGuestId()){
                hotel.disableReservation(reservation);
            }
        }
    }    

}
 