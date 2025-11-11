package ui;

import data.*;
import domain.*;
import utils.*;

public class Main {
    private static String FILE_NAME = "hotel.dat";
    private static IOConsoleUser keyboard = new IOConsoleUser();

    /* PARA TESTEAR */static Hotel hotel = new Hotel("PUTICLUB", "Cali", "mario.bravoo@gmail.com", 3015326737L);

    static int reservationNumber = 0;

    public static void main(String[] args) {
        /* PARA TESTEAR */hotel.addRoom(new Room(101, 2, 1, "Basic"));
        /* PARA TESTEAR */hotel.addRoom(new Room(102, 2, 1, "Medium"));
        /* PARA TESTEAR */hotel.addRoom(new Room(103, 2, 1, "Suit"));
        /* PARA TESTEAR */hotel.addRoom(new Room(104, 2, 1, "Medium"));
        /* PARA TESTEAR */hotel.addRoom(new Room(105, 2, 1, "Medium"));
        /* PARA TESTEAR */hotel.addRoom(new Room(201, 2, 1, "Medium"));
        /* PARA TESTEAR */hotel.addRoom(new Room(202, 2, 1, "Medium"));
        /* PARA TESTEAR */hotel.addRoom(new Room(203, 2, 1, "Medium"));
        /* PARA TESTEAR */hotel.addRoom(new Room(204, 2, 1, "Medium"));

        boolean menu = true;
        int option;

        loadHotel();
        while (menu) {
            // HOTEL MENU
            showHotelMenu();
            option = keyboard.inputInt("\n- Enter an option: ");
            switch (option) {
                case 1 -> {

                }
                case 2 -> {
                    // Administrator menu
                    showAdminMenu();
                    option = keyboard.inputInt("\n- Enter an option: ");
                    switch (option) {
                        case 1 -> {
                            // Reservations
                            showReservationMenu();
                            option = keyboard.inputInt("\n- Enter an option: ");
                            switch (option) {
                                case 1 -> {
                                    // Add reservation
                                    addReservation();
                                }
                                case 2 -> {
                                    // Show available rooms
                                    showAvailableRooms();
                                }

                                case 3 -> {
                                    // Disable reservation
                                    disableReservation();
                                }
                                case 4 -> {
                                    // Change reservation data
                                    changeReservationData();
                                }

                                default -> {
                                    System.out.println("\n- Leaving...");
                                }
                            }
                        }
                        case 2 -> {
                            // Reservation record
                            showReservationRecordMenu();
                            option = keyboard.inputInt("\n- Enter an option: ");
                            switch (option) {
                                case 1 -> {
                                    showReservationRecord();
                                }
                                case 2 ->{
                                    showGuestRecord();
                                }
                                case 3 ->{
                                    searchReservationInRecord();                      
                                }
                                case 4 ->{

                                }
                                case 5 ->{

                                }
                                default ->{

                                }
                            }
                        }
                        // case 3 ->{//Employees}
                        default -> {
                            System.out.println("\n- Leaving...");
                        }
                    }
                }
                case 3 -> {
                    menu = false;
                }

                default -> {
                    System.out.println("\n- Invalid option. Try again.");
                }
            }
        }

        saveHotel();

    }

    private static void showHotelMenu() {
        System.out.println("\n- - - - - - - - HOTEL " + hotel.getHotelName().toUpperCase() + " MENU - - - - - - - -");
        System.out.println("(1) Hotel Data.");
        System.out.println("(2) Administrator menu.");
        System.out.println("(3) Save and exit.\n");
    }

    private static void showAdminMenu() {
        System.out.println("\n- - - - - - - - ADMINISTRATOR MENU - - - - - - - - ");
        System.out.println("(1) Reservations.");
        System.out.println("(2) Reservation record.");
        System.out.println("(3) Exit.\n");
        // System.out.println("(4) Employees.");

    }

    private static void showReservationMenu() {
        System.out.println("\n- - - - - - - - RESERVATION MENU - - - - - - - - ");
        System.out.println("(1) Add reservation.");
        System.out.println("(2) Show active Reservations.");
        System.out.println("(3) Disable reservation.");
        System.out.println("(4) Change reservation data.");
        System.out.println("(5) Exit.\n");
    }

    private static void showReservationRecordMenu(){
        System.out.println("\n(1) Show reservation record.");
        System.out.println("(2) Show guest record.");
        System.out.println("(3) Search reservation in record.");
        System.out.println("(4) Search guest in guest record.");
        System.out.println("(5) Exit.");
    }
    
    private static void showChangeReservationMenu() {
        System.out.println("\n(1) Change guest name.");
        System.out.println("(2) Change guest email.");
        System.out.println("(3) Change guest phone number.");
        System.out.println("(4) Change guest ID.");
        System.out.println("(5) Change room.");
        System.out.println("(6) Change reservation initial date.");
        System.out.println("(7) Change reservation final date.");
        System.out.println("(8) Exit.\n");
    }
    
    private static void showAvailableRooms() {
        System.out.println("\n- - - - - - - - AVAILABLE ROOMS - - - - - - - -");
        for (Room room : hotel.getAvailableRooms()) {
            System.out.println(room.toString());
        }
        System.out.println();
    }

    private static void showReservationRecord() {
        System.out.println("\n- - - - - - - - RESERVATION RECORD - - - - - - - -");
        for (Reservation reservation : hotel.getReservationList()) {
            System.out.println(reservation.toString());
        }
        System.out.println();
    }

    private static void showGuestRecord() {
        System.out.println("\n- - - - - - - - GUEST RECORD - - - - - - - -");
        for (Guest guest : hotel.getGuestList()) {
            System.out.println(guest.toString());
        }
        System.out.println();
    }
    
    private static void addReservation() {
        String guestName = keyboard.inputText("\n- Enter guest name: ");
        String guestEmail = keyboard.inputText("- Enter guest email adress: ");
        long phoneNumber = keyboard.inputLong("- Enter guest phone number: ");
        long id = keyboard.inputLong("- Enter guest ID: ");
        String initialDate = keyboard.inputText("- Enter reservation initial date (Day/Month/Year): ");
        String finalDate = keyboard.inputText("- Enter reservation final date (Day/Month/Year): ");
        Guest guest = new Guest(guestName, guestEmail, phoneNumber, id);
        
        // Mostrar lista de habitaciones disponibles
        showAvailableRooms();
        boolean condition = false;

        // Validar que la habitacion este disponible, si no dar un mensaje de que está
        // ocupada.
        do {
            int roomNum = keyboard.inputInt("\n- Enter the room number that the customer requested: ");
            Room room = hotel.findRoomById(roomNum);
            
            if (room != null && room.getAvailability()) {
                hotel.addReservation(new Reservation(guest, room, initialDate, finalDate));
                System.out.println("\n- Reservation created successfully");
                condition = true;
            } else {
                System.out.println("\n- The room isn't available or doesn't exist. Try again.\n");
            }
        } while (!condition);
    }
    
    private static void disableReservation() {
        long id = keyboard.inputLong("\n- Enter reservation number: ");
        for (Reservation reservation : hotel.getReservationList()) {
            if (id == reservation.getReservationNumber()) {
                hotel.disableReservation(reservation);
            }
        }
    }

    private static void changeReservationData() {

        int reservationNumber = keyboard
                .inputInt("\n- Enter the reservation number for which you are going to change the data: ");
        if (reservationNumber > 0) {
            System.out.println("\n- Reservation found.");
            showChangeReservationMenu();
            int option = keyboard.inputInt("\n- Enter an option: ");
            switch (option) {
                case 1 -> {
                    String guestName = keyboard.inputText("\n- Enter a new guest name: ");
                    hotel.findReservationById(reservationNumber).getGuest().setGuestName(guestName);
                }
                case 2 -> {
                    String guestEmail = keyboard.inputText("\n- Enter a new guest email adress: ");
                    hotel.findReservationById(reservationNumber).getGuest().setGuestEmail(guestEmail);
                }
                case 3 -> {
                    long phoneNumber = keyboard.inputLong("\n- Enter a new guest phone number: ");
                    hotel.findReservationById(reservationNumber).getGuest().setPhoneNumber(phoneNumber);
                }
                case 4 -> {
                    long id = keyboard.inputLong("\n- Enter a new guest ID: ");
                    hotel.findReservationById(reservationNumber).getGuest().setGuestId(id);
                }
                case 5 -> {
                    boolean condition = false;
                    do {
                        int roomNumber = keyboard.inputInt("\n- Enter a new room number: ");
                        Room room = hotel.findRoomById(roomNumber);
                        if (room != null && room.getAvailability()) {
                            hotel.findReservationById(reservationNumber).setRoom(room);
                            condition = true;
                            System.out.println("\n- Room changed successfully.");
                        } else {
                            System.out.println("\n- The room isn't available or doesn't exist. Try again.\n");
                        }
                    } while (!condition);
                }
                case 6 -> {
                    String initialDate = keyboard.inputText("\n- Enter a new reservation initial date: ");
                    hotel.findReservationById(reservationNumber).setInitialDate(initialDate);
                }
                case 7 -> {
                    String finalDate = keyboard.inputText("\n- Enter a new reservation final date: ");
                    hotel.findReservationById(reservationNumber).setFinalDate(finalDate);
                }
                case 8 -> {
                    System.out.println("\n- Leaving... ");
                }
                default -> {
                    System.out.println("\n- Invalid option. Try again.");
                }
            }
        } else {
            System.out.println("\n- The reservation doesn't exist, please try again");
        }

    }

    private static void searchReservationInRecord(){
        int reservationNumber = keyboard.inputInt("\n- Enter the reservation number that you want search: ");
        Reservation reservation = hotel.findReservationById(reservationNumber);
        if(reservation!= null){
            reservation.toString();
        }else{
            System.out.println("\n- The reservation doesn´t exist, please try again.");
        }
    }

    private static void loadHotel() {
        var loaded = HotelStorage.load(FILE_NAME);
        if (loaded != null) {
            hotel = loaded;
            keyboard.inputText("\n- Hotel loaded successfully");
        }

    }

    private static void saveHotel() {
        HotelStorage.save(hotel, FILE_NAME);
    }
}