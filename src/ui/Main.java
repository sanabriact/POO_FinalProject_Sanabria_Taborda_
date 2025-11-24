package ui;

import data.*;
import domain.*;
import utils.*;

public class Main {
    private static final String FILE_NAME = "hotel.dat";
    private static IOConsoleUser keyboard = new IOConsoleUser();

    /* PARA TESTEAR */
    private static Hotel hotel = new Hotel();
    static int reservationNumber = 0;

    public static void main(String[] args) {

        CSVEncoder<Room> roomEncoder = new CSVEncoder<Room>() {
            @Override
            public String[] getFieldNames() {
                return new String[] {
                        "roomNumber", "roomType", "price", "beds", "baths",
                };
            }

            @Override
            public String[] getValues(Room room) {
                return new String[] {
                        String.valueOf(room.getRoomNum()), room.getRoomType(), String.valueOf(room.getPrice()),
                        String.valueOf(room.getRoomBeds()), String.valueOf(room.getRoomBaths())
                };
            }
        };

        CSVEncoder<Reservation> reservationEncoder = new CSVEncoder<Reservation>() {
            @Override
            public String[] getFieldNames() {
                return new String[] {
                        "resNumber", "status", "initialDate", "finalDate"
                };
            }

            @Override
            public String[] getValues(Reservation reservation) {
                return new String[] {
                        String.valueOf(reservation.getReservationNumber()), String.valueOf(reservation.getStatus()),
                        reservation.getInitialDate(), reservation.getFinalDate()
                };
            }
        };

        /* PARA TESTEAR */hotel.addRoom(new Room(101, 2, 1, "basic"));
        /* PARA TESTEAR */hotel.addRoom(new Room(102, 2, 1, "medium"));
        /* PARA TESTEAR */hotel.addRoom(new Room(103, 2, 1, "suit"));
        /* PARA TESTEAR */hotel.addRoom(new Room(104, 2, 1, "medium"));
        /* PARA TESTEAR */hotel.addRoom(new Room(105, 2, 1, "medium"));
        /* PARA TESTEAR */hotel.addRoom(new Room(201, 2, 1, "medium"));
        /* PARA TESTEAR */hotel.addRoom(new Room(202, 2, 1, "medium"));
        /* PARA TESTEAR */hotel.addRoom(new Room(203, 2, 1, "medium"));
        /* PARA TESTEAR */hotel.addRoom(new Room(204, 2, 1, "medium"));

        boolean menu = true;
        int option;

        loadHotel();
        while (menu) {
            // HOTEL MENU
            showHotelMenu();
            option = keyboard.inputInt("\n- Enter an option: ");
            switch (option) {
                case 1 -> {
                    boolean condition = true;
                    while (condition) {
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
                                condition = false;
                            }
                            default -> {
                                keyboard.writeLine("\n- Invalid option. Try again.");
                            }
                        }

                    }

                }
                case 2 -> {
                    // Administrator menu
                    boolean condition2 = true;
                    while (condition2) {
                        showAdminMenu();
                        option = keyboard.inputInt("\n- Enter an option: ");
                        switch (option) {
                            case 1 -> {
                                // Reservations
                                boolean condition3 = true;
                                while (condition3) {
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
                                            condition3 = false;
                                        }
                                        default -> {
                                            keyboard.writeLine("\n- Invalid option. Try again.");
                                        }
                                    }
                                }
                            }
                            case 2 -> {
                                // Reservation record
                                boolean condition4 = true;
                                while (condition4) {
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
                                            keyboard.writeLine("\n- Leaving...\n");
                                            condition4 = false;
                                        }
                                        default -> {
                                            keyboard.writeLine("\n- Invalid option. Try again.");
                                        }
                                    }
                                }
                            }
                            case 3 -> {// Employee Menu
                                boolean condition5 = true;
                                while (condition5) {
                                    showEmployeeMenu();
                                    option = keyboard.inputInt("\n- Enter an option: ");
                                    switch (option) {
                                        case 1 -> {
                                            // Add employee
                                            addEmployee();
                                        }
                                        case 2 -> {
                                            // Show employee list
                                            showEmployeeList();
                                        }
                                        case 3 -> {
                                            // Change employee info
                                            changeEmployeeInfo();
                                        }
                                        case 4 -> {
                                            // search employee
                                            searchEmployeeByNumber();
                                        }
                                        case 5 -> {
                                            // Dismiss employee
                                            dismissEmployee();
                                        }
                                        case 6 -> {
                                            // Exit
                                            keyboard.writeLine("\n- Leaving...\n");
                                            condition5 = false;
                                        }
                                        default -> {
                                            keyboard.writeLine("\n- Invalid option. Try again.");
                                        }
                                    }
                                }
                            }
                            case 4 -> {
                                keyboard.writeLine("\n- Leaving");
                                condition2 = false;
                            }
                            default -> {
                                keyboard.writeLine("\n- Invalid option. Try again.");
                            }
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
        String roomContent = roomEncoder.encode(hotel.getRoomList());
        saveToCsvFile("Rooms.csv", roomContent);
        String resContent = reservationEncoder.encode(hotel.getReservationList());
        saveToCsvFile("Reservations.csv", resContent);
    }

    private static void showHotelMenu() {
        keyboard.writeLine("\n- - - - - - - - - - - - - - - - HOTEL " + hotel.getHotelName().toUpperCase()
                + " MENU - - - - - - - - - - - - - - - -"
                + "\n(1) Hotel Data."
                + "\n(2) Administrator Menu."
                + "\n(3) Save and exit.\n");
    }

    private static void showHotelDataMenu() {
        keyboard.writeLine("\n- - - - - - - - HOTEL DATA MENU - - - - - - - - "
                + "\n(1) Configure Hotel"
                + "\n(2) Add room"
                + "\n(3) Change info"
                + "\n(4) Go back.\n");
    }

    private static void showRoomInfoMenu() {
        keyboard.writeLine("(1) Room number"
                + "\n(2) Cuantity of room beds"
                + "\n(3) Cuantity of room baths"
                + "\n(4) Room type"
                + "\n(5) Exit.\n");
    }

    private static void showAdminMenu() {
        keyboard.writeLine("\n- - - - - - - - ADMINISTRATOR MENU - - - - - - - -"
                + "\n(1) Reservations."
                + "\n(2) Record."
                + "\n(3) Employees."
                + "\n(4) Exit.\n");

    }

    private static void showReservationMenu() {
        keyboard.writeLine("\n- - - - - - - - RESERVATION MENU - - - - - - - - "
                + "\n(1) Add reservation."
                + "\n(2) Show active Reservations."
                + "\n(3) Show available rooms."
                + "\n(4) Disable reservation."
                + "\n(5) Change reservation data."
                + "\n(6) Exit.\n");
    }

    private static void showRecordMenu() {
        keyboard.writeLine("\n(1) Show reservation record."
                + "\n(2) Show guest record."
                + "\n(3) Search reservation in record."
                + "\n(4) Search guest in guest record."
                + "\n(5) Exit.\n");
    }

    private static void showChangeReservationMenu() {
        keyboard.writeLine("\n(1) Change guest name."
                + "\n(2) Change guest email."
                + "\n(3) Change guest phone number."
                + "\n(4) Change guest ID."
                + "\n(5) Change room."
                + "\n(6) Change reservation initial date."
                + "\n(7) Change reservation final date."
                + "\n(8) Exit.\n");
    }

    private static void showEmployeeMenu() {
        keyboard.writeLine("\n(1) Add employee."
                + "\n(2) Show employee list."
                + "\n(3) Change employee information."
                + "\n(4) Search employee."
                + "\n(5) Dismiss employeee."
                + "\n(6) Exit.\n");
    }

    private static void showChangeHotelInfoMenu() {
        keyboard.writeLine("(1) Change hotel info."
                + "\n(2) Change room info.\n");
    }

    private static void showChangeEmployeeInfoMenu() {
        keyboard.writeLine("\n(1) Change name."
                + "\n(2) Change email."
                + "\n(3) Change phone number."
                + "\n(4) Change ID."
                + "\n(5) Change position."
                + "\n(6) Change salary."
                + "\n(7) Exit.\n");
    }

    private static void configHotel() {
        keyboard.writeLine("\n- - - - - - - - - - - - - - - - - - - - - - -");
        String name = keyboard.inputText("\nHotel name: ");
        String adress = keyboard.inputText("\nHotel adress: ");
        String email = keyboard.inputText("\nHotel email: ");
        long phoneNumber = keyboard.inputLong("\nHotel number: ");
        hotel.setHotelName(name);
        hotel.setHotelAdress(adress);
        hotel.setHotelEmail(email);
        hotel.setHotelPhoneNum(phoneNumber);
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

    private static void showEmployeeList() {
        keyboard.writeLine("\n- - - - - - - - EMPLOYEE LIST - - - - - - - -\n");
        for (Employee employee : hotel.getEmployeeList()) {
            keyboard.writeLine(employee.toString());
        }
    }

    private static void showRoomList() {
        keyboard.writeLine("\n- - - - - - - - ROOM LIST - - - - - - - -\n");
        for (Room room : hotel.getRoomList()) {
            keyboard.writeLine(room.toString());
        }

        keyboard.writeLine("");
    }

    private static void changeInfo() {
        keyboard.writeLine("\n- - - - - - - - - - - - - - - - - - - - - - -");
        showChangeHotelInfoMenu();
        int option = keyboard.inputInt("What do you want to change?\n");

        switch (option) {
            case 1 -> {
                changeHotelInfo();
            }
            case 2 -> {
                changeRoomInfo();
            }
        }
    }

    private static void changeEmployeeInfo() {
        int employeeNumber = keyboard.inputInt("\n- Enter the Employee number that yoy want to change: ");
        if (employeeNumber > 0) {
            showChangeEmployeeInfoMenu();
            int option = keyboard.inputInt("\n- Enter an option: ");
            switch (option) {
                case 1 -> {
                    // name
                    String name = keyboard.inputText("\n- Enter a new employee name: ");
                    hotel.findEmployeeByNumber(employeeNumber).setName(name);
                }
                case 2 -> {
                    // email
                    String email = keyboard.inputText("\n- Enter a new employee email: ");
                    hotel.findEmployeeByNumber(employeeNumber).setEmail(email);
                }
                case 3 -> {
                    // phone number
                    long phoneNumber = keyboard.inputLong("\n- Enter a new employee phone number ");
                    hotel.findEmployeeByNumber(employeeNumber).setPhoneNumber(phoneNumber);
                }
                case 4 -> {
                    // ID
                    long id = keyboard.inputLong("\n- Enter a new employee ID: ");
                    hotel.findEmployeeByNumber(employeeNumber).setId(id);
                }
                case 5 -> {
                    // position
                    String position = keyboard.inputText("\n- Enter a new employee position: ");
                    hotel.findEmployeeByNumber(employeeNumber).setPosition(position);
                }
                case 6 -> {
                    // salary
                    double salary = keyboard.inputDouble("\n- Enter a new employee salary: ");
                    hotel.findEmployeeByNumber(employeeNumber).setSalary(salary);
                }
                case 7 -> {
                    // exit
                    keyboard.writeLine("\n- Leaving...");
                }
                default -> {
                    keyboard.writeLine("\n- Enter a valid option.");
                }
            }
        } else {
            keyboard.writeLine("\n- Employee doesn't exist. Try again.");
        }

    }

    private static void changeHotelInfo() {
        keyboard.writeLine("\n- - - - - - - - - - - - - - - - - -");
        hotelInfo();
        boolean condition = true;

        int option = keyboard.inputInt(
                "\n- What do you want to change?\n(1) Name\n(2) Adress\n(3) Email\n(4) Telephone number\n(5) Leave\n-Enter an option: ");

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
                condition = false;
            }

            default -> {
                keyboard.writeLine("An error ocurred selecting the option.");
            }
        }

        if (condition) {
            keyboard.writeLine("\nInfo changed succesfully.");
        } else {
            keyboard.writeLine("No info was changed.");
        }
    }

    private static void hotelInfo() {
        keyboard.writeLine(hotel.toString());
    }

    private static void changeRoomInfo() {
        showRoomList();
        int option = keyboard.inputInt("Enter room number for changing its info: ");
        boolean ds = true;

        for (Room room : hotel.getRoomList()) {
            if (option == room.getRoomNum()) {
                keyboard.writeLine("- - - - - - - - - ");
                keyboard.writeLine("What do you want to change?\n");
                showRoomInfoMenu();
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

    private static void addRoom() {
        int roomNumber = keyboard.inputInt("\n- Room number: ");
        int beds = keyboard.inputInt("\n- Cuantity of beds: ");
        int baths = keyboard.inputInt("\n- Cuantity of baths: ");
        String type = keyboard.inputText("\n- Room type (Basic/Medium/Suit): ");
        hotel.addRoom(new Room(roomNumber, beds, baths, type.toLowerCase()));
        keyboard.writeLine("Room added succesfully.\n");
    }

    private static void addEmployee() {
        String name = keyboard.inputText("\n- Enter employee's name: ");
        String email = keyboard.inputText("\n- Enter employee's email adress: ");
        long phoneNumber = keyboard.inputLong("\n- Enter employee's phone number: ");
        long id = keyboard.inputLong("\n- Enter employee's id:");
        String position = keyboard.inputText("\n- Enter employee's position: ");
        double salary = keyboard.inputDouble("\n- Enter employee's salary: ");
        hotel.addEmployee(new Employee(name, email, phoneNumber, id, position, salary));
        keyboard.writeLine("\n- Employee added succesfully.");
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
                    hotel.findReservationByNumber(reservationNumber).getGuest().setName(guestName);
                }
                case 2 -> {
                    String guestEmail = keyboard.inputText("\n- Enter a new guest email adress: ");
                    hotel.findReservationByNumber(reservationNumber).getGuest().setEmail(guestEmail);
                }
                case 3 -> {
                    long phoneNumber = keyboard.inputLong("\n- Enter a new guest phone number: ");
                    hotel.findReservationByNumber(reservationNumber).getGuest().setPhoneNumber(phoneNumber);
                }
                case 4 -> {
                    long id = keyboard.inputLong("\n- Enter a new guest ID: ");
                    hotel.findReservationByNumber(reservationNumber).getGuest().setId(id);
                }
                case 5 -> {
                    boolean condition = false;
                    do {
                        int roomNumber = keyboard.inputInt("\n- Enter a new room number: ");
                        Room room = hotel.findRoomById(roomNumber);
                        if (room != null && room.getAvailability()) {
                            hotel.findReservationByNumber(reservationNumber).setRoom(room);
                            condition = true;
                            keyboard.writeLine("\n- Room changed successfully.");
                        } else {
                            keyboard.writeLine("\n- The room isn't available or doesn't exist. Try again.\n");
                        }
                    } while (!condition);
                }
                case 6 -> {
                    String initialDate = keyboard.inputText("\n- Enter a new reservation initial date: ");
                    hotel.findReservationByNumber(reservationNumber).setInitialDate(initialDate);
                }
                case 7 -> {
                    String finalDate = keyboard.inputText("\n- Enter a new reservation final date: ");
                    hotel.findReservationByNumber(reservationNumber).setFinalDate(finalDate);
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
        Reservation reservation = hotel.findReservationByNumber(reservationNumber);
        if (reservation != null) {
            keyboard.writeLine("\n- Reservation found successfully.");
            keyboard.writeLine(reservation.toString());
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

    private static void searchEmployeeByNumber() {
        long employeeId = keyboard.inputLong("\n- Enter the employee number that yoy want search: ");
        Employee employee = hotel.findEmployeeByNumber(employeeId);
        if (employee != null) {
            keyboard.writeLine("\n- Employee found successfully.");
            keyboard.writeLine(employee.toString());
        } else {
            keyboard.writeLine("\n- The employee doesn't exist, please try again.");
        }
    }

    private static void dismissEmployee() {
        int employeeNumber = keyboard.inputInt("\n- Enter the employee number that you want to dismiss: ");
        if (employeeNumber > 0 && employeeNumber <= hotel.getEmployeeList().size()) {
            hotel.findEmployeeByNumber(employeeNumber).dismissEmployee();
            keyboard.writeLine("\n- Employee dismissed successfully.");
        } else {
            keyboard.writeLine("\n- The employee doesn't exist, please try again.");
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

    private static void saveToCsvFile(String file_name, String content) {
        HotelStorage.save(file_name, content);
    }
}