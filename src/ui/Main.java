package ui;

import data.*;
import domain.*;
import utils.*;

public class Main {
    private static final String FILE_NAME = "hotel.dat";
    private static IOConsoleUser keyboard = new IOConsoleUser();

    /* PARA TESTEAR */
    private static Hotel hotel = new Hotel("PUTICLUB", "Manizales", "mario.bravoo@gmail.com", 3015326737L);
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
        /* PARA TESTEAR */saveHotel();

        boolean menu = true;
        int option;

        loadHotel();
        while (menu) {
            // HOTEL MENU
            showHotelMenu();
            option = keyboard.inputInt("\n- Enter an option: ");
            switch (option) {
                case 1 -> {
                    showHotelDataMenu();
                    option = keyboard.inputInt("\n- Enter a option: ");
                    switch (option) {
                        case 1 -> {
                            configHotel();
                        }

                        case 2 -> {
                            addRoom();
                        }

                        case 3 -> {
                            changeInfo();
                        }

                        case 4 -> {
                            keyboard.writeLine("\n Leaving...\n");
                        }
                    }

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
                                    // Show active reservations
                                    showActiveReservations();
                                }
                                case 3 -> {
                                    // Show available rooms
                                    showAvailableRooms();
                                }
                                case 4 -> {
                                    // Disable reservation
                                    disableReservation();
                                }
                                case 5 -> {
                                    // Change reservation data
                                    changeReservationData();
                                }
                                case 6 -> {
                                    keyboard.writeLine("\n- Leaving...\n");
                                }
                                default -> {

                                }
                            }
                        }
                        case 2 -> {
                            // Reservation record
                            showRecordMenu();
                            option = keyboard.inputInt("\n- Enter an option: ");
                            switch (option) {
                                case 1 -> {
                                    showReservationRecord();
                                }
                                case 2 -> {
                                    showGuestRecord();
                                }
                                case 3 -> {
                                    searchReservationInRecord();
                                }
                                case 4 -> {
                                    searchGuestInRecord();
                                }
                                case 5 -> {
                                    System.out.println("\n- Leaving...\n");
                                }
                                default -> {

                                }
                            }
                        }
                        // case 3 ->{//Employees}
                        default -> {
                            keyboard.writeLine("\n- Leaving...\n");
                        }
                    }
                }
                case 3 -> {
                    menu = false;
                }

                default -> {
                    keyboard.writeLine("\n- Invalid option. Try again.");
                }
            }
        }

        saveHotel();

    }

    private static void showHotelMenu() {
        keyboard.writeLine("\n- - - - - - - - - - - - - - - - HOTEL " + hotel.getHotelName().toUpperCase()
                + " MENU - - - - - - - - - - - - - - - -");
        keyboard.writeLine("(1) Hotel Data.");
        keyboard.writeLine("(2) Administrator menu.");
        keyboard.writeLine("(3) Save and exit.\n");
    }

    private static void showHotelDataMenu() {
        keyboard.writeLine("\n- - - - - - - - - - - - - - - - - - - - - - -");
        keyboard.writeLine("(1) Configure Hotel");
        keyboard.writeLine("(2) Add room");
        keyboard.writeLine("(3) Change info");
        keyboard.writeLine("(4) Go back.\n");
    }
    
        private static void RoomInfoMenu() {
            keyboard.writeLine("(1) Room number");
            keyboard.writeLine("(2) Cuantity of room beds");
            keyboard.writeLine("(3) Cuantity of room baths");
            keyboard.writeLine("(4) Room type");
            keyboard.writeLine("(5) Exit");
        }
    
        private static void showAdminMenu() {
            keyboard.writeLine("\n- - - - - - - - ADMINISTRATOR MENU - - - - - - - - ");
            keyboard.writeLine("(1) Reservations.");
            keyboard.writeLine("(2) Record.");
            keyboard.writeLine("(3) Exit.\n");
            // keyboard.writeLine("(4) Employees.");
    
        }
    
        private static void showReservationMenu() {
            keyboard.writeLine("\n- - - - - - - - RESERVATION MENU - - - - - - - - ");
            keyboard.writeLine("(1) Add reservation.");
            keyboard.writeLine("(2) Show active Reservations.");
            keyboard.writeLine("(3) Show available rooms.");
            keyboard.writeLine("(4) Disable reservation.");
            keyboard.writeLine("(5) Change reservation data.");
            keyboard.writeLine("(6) Exit.\n");
        }
    
        private static void showRecordMenu() {
            keyboard.writeLine("\n(1) Show reservation record.");
            keyboard.writeLine("(2) Show guest record.");
            keyboard.writeLine("(3) Search reservation in record.");
            keyboard.writeLine("(4) Search guest in guest record.");
            keyboard.writeLine("(5) Exit.");
        }
    
        private static void showChangeReservationMenu() {
            keyboard.writeLine("\n(1) Change guest name.");
            keyboard.writeLine("(2) Change guest email.");
            keyboard.writeLine("(3) Change guest phone number.");
            keyboard.writeLine("(4) Change guest ID.");
            keyboard.writeLine("(5) Change room.");
            keyboard.writeLine("(6) Change reservation initial date.");
            keyboard.writeLine("(7) Change reservation final date.");
            keyboard.writeLine("(8) Exit.\n");
        }
    
    private static void configHotel() {
        keyboard.writeLine("\n- - - - - - - - - - - - - - - - - - - - - - -");
        hotel = new Hotel(keyboard.inputText("Hotel name: "),
        keyboard.inputText("\nHotel adress: "), keyboard.inputText("\nHotel email: "),
                keyboard.inputLong("\nHotel number: "));
        saveHotel();
    }

    private static void addRoom() {
        hotel.addRoom(new Room(keyboard.inputInt("Room number: "),
                keyboard.inputInt("\nCuantity of beds: "),
                keyboard.inputInt("\nCuantity of baths: "), keyboard.inputText("\nRoom type: ")));
        keyboard.writeLine("Room added succesfully.\n");
    }

    private static void changeHotelInfoMenu() {
        keyboard.writeLine("(1) Change hotel info");
        keyboard.writeLine("(2) Change room info");
    }

    private static void showRoomList() {
        keyboard.writeLine("\n- - - - - - - - - - - - - - - - - - - - - - -");
        keyboard.writeLine("\n                 ROOM LIST\n");
        for (Room room : hotel.getRoomList()) {
            keyboard.writeLine(room.toString());
        }

        keyboard.writeLine("");
    }

    private static void changeInfo() {
        keyboard.writeLine("\n- - - - - - - - - - - - - - - - - - - - - - -");
        changeHotelInfoMenu();
        int option = keyboard.inputInt("What do you want to change?\n");

        switch (option) {
            case 1 -> {
                changeHotelInfo(option);
            }
            case 2 -> {
                changeRoomInfo(option);
            }
        }

    }

    private static void changeHotelInfo(int option) {
        keyboard.writeLine("\n- - - - - - - - - - - - - - - - - -");
        hotelInfo();
        boolean ds = true;

        option = keyboard.inputInt(
                "\nWhat do you want to change?\n(1) Name\n(2) Adress\n(3) Email\n(4) Telephone number\n(5) Leave\n-Enter an option: ");

        switch (option) {
            case 1 -> {
                hotel.setHotelName(keyboard.inputText("Enter new hotel name: "));
            }

            case 2 -> {
                hotel.setHotelAdress(keyboard.inputText("Enter new hotel adress: "));
            }

            case 3 -> {
                hotel.setHotelEmail(keyboard.inputText("Enter new hotel email: "));
            }

            case 4 -> {
                hotel.setHotelPhoneNum(keyboard.inputLong("Enter new hotel phone number: "));
            }

            case 5 -> {
                keyboard.writeLine("\n Leaving... \n");
                ds = false;
            }

            default -> {
                keyboard.writeLine("An error ocurred selecting the option.");
            }
        }

        if (ds) {
            keyboard.writeLine("\nInfo changed succesfully.");
        } else {
            keyboard.writeLine("No info was changed.");
        }
    }

    private static void hotelInfo() {
        keyboard.writeLine(hotel.toString());
    }

    private static void changeRoomInfo(int option) {
        showRoomList();
        option = keyboard.inputInt("Enter room number for changing its info: ");
        boolean ds = true;

        for (Room room : hotel.getRoomList()) {
            if (option == room.getRoomNum()) {
                keyboard.writeLine("- - - - - - - - - ");
                keyboard.writeLine("What do you want to change?\n");
                RoomInfoMenu();
                option = keyboard.inputInt("\n - Enter an option: ");

                switch (option) {
                    case 1 -> {
                        room.setRoomNum(keyboard.inputInt("Enter new room number for " + room.getRoomNum()));
                    }

                    case 2 -> {
                        room.setRoomBeds(
                                keyboard.inputInt("Enter new cuantity of beds for room " + room.getRoomBeds()));
                    }

                    case 3 -> {
                        room.setRoomBaths(keyboard
                                .inputInt("Enter new cuantity of beds for room " + room.getRoomBaths()));
                    }

                    case 4 -> {
                        room.setRoomType(keyboard.inputText("Enter new type for room " + room.getRoomType()));
                    }

                    case 5 -> {
                        keyboard.writeLine("Leaving...");
                        ds = false;
                    }

                    default -> {
                        keyboard.writeLine("An error ocurred while trying to change info");
                    }
                }
            }
        }

        if (ds) {
            keyboard.writeLine("Info changed succesfully.");
        } else {
            keyboard.writeLine("No info was changed.");
        }

    }

    private static void showActiveReservations() {
        keyboard.writeLine("\n- - - - - - - - ACTIVE RESERVATIONS - - - - - - - -");
        for (Reservation reservation : hotel.getReservationList()) {
            if (reservation.getStatus() == true) {
                keyboard.writeLine(reservation.toString());
            }
        }
    }

    private static void showAvailableRooms() {
        keyboard.writeLine("\n- - - - - - - - AVAILABLE ROOMS - - - - - - - -");
        for (Room room : hotel.getAvailableRooms()) {
            keyboard.writeLine(room.toString());
        }
        keyboard.writeLine("");
    }

    private static void showReservationRecord() {
        keyboard.writeLine("\n- - - - - - - - RESERVATION RECORD - - - - - - - -");
        for (Reservation reservation : hotel.getReservationList()) {
            keyboard.writeLine(reservation.toString());
        }
        keyboard.writeLine("");
    }

    private static void showGuestRecord() {
        keyboard.writeLine("\n- - - - - - - - GUEST RECORD - - - - - - - -");
        for (Guest guest : hotel.getGuestList()) {
            keyboard.writeLine(guest.toString());
        }
        keyboard.writeLine("");
    }

    private static void addReservation() {
        String guestName = keyboard.inputText("\n- Enter guest name: ");
        String guestEmail = keyboard.inputText("- Enter guest email adress: ");
        long phoneNumber = keyboard.inputLong("- Enter guest phone number: ");
        long id = keyboard.inputLong("- Enter guest ID: ");
        String initialDate = keyboard.inputText("- Enter reservation initial date (Day/Month/Year): ");
        String finalDate = keyboard.inputText("- Enter reservation final date (Day/Month/Year): ");
        Guest guest = new Guest(guestName, guestEmail, phoneNumber, id);

        showAvailableRooms();
        boolean condition = false;

        do {
            int roomNum = keyboard.inputInt("\n- Enter the room number that the customer requested: ");
            Room room = hotel.findRoomById(roomNum);

            if (room != null && room.getAvailability()) {
                hotel.addReservation(new Reservation(guest, room, initialDate, finalDate));
                keyboard.writeLine("\n- Reservation created successfully");
                condition = true;
            } else {
                keyboard.writeLine("\n- The room isn't available or doesn't exist. Try again.\n");
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
            keyboard.writeLine("\n- Reservation found.");
            showChangeReservationMenu();
            int option = keyboard.inputInt("\n- Enter an option: ");
            switch (option) {
                case 1 -> {
                    String guestName = keyboard.inputText("\n- Enter a new guest name: ");
                    hotel.findReservationById(reservationNumber).getGuest().setName(guestName);
                }
                case 2 -> {
                    String guestEmail = keyboard.inputText("\n- Enter a new guest email adress: ");
                    hotel.findReservationById(reservationNumber).getGuest().setEmail(guestEmail);
                }
                case 3 -> {
                    long phoneNumber = keyboard.inputLong("\n- Enter a new guest phone number: ");
                    hotel.findReservationById(reservationNumber).getGuest().setPhoneNumber(phoneNumber);
                }
                case 4 -> {
                    long id = keyboard.inputLong("\n- Enter a new guest ID: ");
                    hotel.findReservationById(reservationNumber).getGuest().setId(id);
                }
                case 5 -> {
                    boolean condition = false;
                    do {
                        int roomNumber = keyboard.inputInt("\n- Enter a new room number: ");
                        Room room = hotel.findRoomById(roomNumber);
                        if (room != null && room.getAvailability()) {
                            hotel.findReservationById(reservationNumber).setRoom(room);
                            condition = true;
                            keyboard.writeLine("\n- Room changed successfully.");
                        } else {
                            keyboard.writeLine("\n- The room isn't available or doesn't exist. Try again.\n");
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
                    keyboard.writeLine("\n- Leaving... ");
                }
                default -> {
                    keyboard.writeLine("\n- Invalid option. Try again.");
                }
            }
        } else {
            keyboard.writeLine("\n- The reservation doesn't exist, please try again");
        }

    }

    private static void searchReservationInRecord() {
        int reservationNumber = keyboard.inputInt("\n- Enter the reservation number that you want search: ");
        Reservation reservation = hotel.findReservationById(reservationNumber);
        if (reservation != null) {
            keyboard.writeLine("\n- Reservation found successfully.");
            reservation.toString();
        } else {
            keyboard.writeLine("\n- The reservation doesn't exist, please try again.");
        }
    }

    private static void searchGuestInRecord() {
        long guestId = keyboard.inputLong("\n- Enter the guest ID that you want search: ");
        Guest guest = hotel.findGuestById(guestId);
        if (guest != null) {
            keyboard.writeLine("\n- Guest found successfully.");
            keyboard.writeLine(guest.toString());
        } else {
            keyboard.writeLine("\n- The guest doesn't exist. Try again.");
        }
    }

    private static void loadHotel() {
        var loaded = HotelStorage.load(FILE_NAME);
        if (loaded != null) {
            hotel = loaded;
            keyboard.writeLine("\n- Hotel loaded successfully");
        }
    }

    private static void saveHotel() {
        HotelStorage.save(hotel, FILE_NAME);
    }
}