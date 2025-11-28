package ui;

import data.*;
import domain.*;
import utils.*;

public class Main {

    private static final String FILE_NAME = "Hotel.dat"; // File used to save serialized hotel data
    private static IOConsoleUser keyboard = new IOConsoleUser(); // Console input/output handler

    private static Hotel hotel = new Hotel(); // Main hotel object containing all data

    public static void main(String[] args) {

        // CSV encoder for Room objects
        CSVEncoder<Room> roomEncoder = new CSVEncoder<Room>() {
            @Override
            public String[] getFieldNames() {
                // Column headers for the CSV
                return new String[] {
                        "roomNumber", "roomType", "price", "beds", "baths",
                };
            }

            @Override
            public String[] getValues(Room room) {
                // Extract room attributes into string array
                return new String[] {
                        String.valueOf(room.getRoomNum()), room.getRoomType(), String.valueOf(room.getPrice()),
                        String.valueOf(room.getRoomBeds()), String.valueOf(room.getRoomBaths())
                };
            }
        };

        // CSV encoder for Reservation objects
        CSVEncoder<Reservation> reservationEncoder = new CSVEncoder<Reservation>() {
            @Override
            public String[] getFieldNames() {
                // Column headers for reservations
                return new String[] {
                        "reservationNumber", "roomNumber", "roomType", "guestName", "status", "initialDate", "finalDate"
                };
            }

            @Override
            public String[] getValues(Reservation reservation) {
                // Extract reservation data into string array
                return new String[] {
                        String.valueOf(reservation.getReservationNumber()),
                        String.valueOf(reservation.getRoom().getRoomNum()), reservation.getRoom().getRoomType(),
                        reservation.getGuest().getName(), String.valueOf(reservation.getStatus()),
                        reservation.getInitialDate(), reservation.getFinalDate()
                };
            }
        };

        // CSV encoder for Guest objects
        CSVEncoder<Guest> guestEncoder = new CSVEncoder<Guest>() {
            @Override
            public String[] getFieldNames() {
                return new String[] {
                        "id", "name", "email", "phoneNumber"
                };
            }

            @Override
            public String[] getValues(Guest guest) {
                return new String[] {
                        String.valueOf(guest.getId()), guest.getName(), guest.getEmail(),
                        String.valueOf(guest.getPhoneNum())
                };
            }
        };

        // CSV encoder for Employee objects
        CSVEncoder<Employee> employeeEncoder = new CSVEncoder<Employee>() {
            @Override
            public String[] getFieldNames() {
                return new String[] {
                        "employeeNumber", "id", "name", "email", "phoneNumber", "position", "salary", "active"
                };
            }

            @Override
            public String[] getValues(Employee employee) {
                return new String[] {
                        String.valueOf(employee.getEmployeeNumber()), String.valueOf(employee.getId()),
                        employee.getName(), employee.getEmail(), String.valueOf(employee.getPhoneNum()),
                        employee.getPosition(), String.valueOf(employee.getSalary()),
                        String.valueOf(employee.getActive())
                };
            }
        };

        boolean menu = true; // Main menu loop control
        int option;

        loadHotel(); // Load serialized hotel data at the start

        while (menu) {
            // Display main hotel menu
            showHotelMenu();
            option = keyboard.inputInt("\n- Enter an option: ");

            switch (option) {

                case 1 -> {  
                    // HOTEL DATA CONFIGURATION MENU
                    boolean condition = true;
                    while (condition) {
                        showHotelDataMenu();
                        option = keyboard.inputInt("\n- Enter a option: ");
                        switch (option) {
                            case 1 -> { // Change hotel info
                                configHotel();
                                saveHotel();
                            }

                            case 2 -> { // Add new room
                                addRoom();
                                saveHotel();
                            }

                            case 3 -> { // Change room/hotel info
                                changeInfo();
                                saveHotel();
                            }

                            case 4 -> { // Exit to main menu
                                keyboard.writeLine("\n Leaving...\n");
                                condition = false;
                            }

                            default -> keyboard.writeLine("\n- Invalid option. Try again.");
                        }
                    }
                }

                case 2 -> {
                    // ADMINISTRATOR MENU
                    boolean condition2 = true;
                    while (condition2) {
                        showAdminMenu();
                        option = keyboard.inputInt("\n- Enter an option: ");
                        switch (option) {

                            case 1 -> { 
                                // RESERVATION MANAGEMENT
                                boolean condition3 = true;
                                while (condition3) {
                                    showReservationMenu();
                                    option = keyboard.inputInt("\n- Enter an option: ");
                                    switch (option) {
                                        case 1 -> { // Add reservation
                                            addReservation();
                                            saveHotel();
                                        }
                                        case 2 -> showActiveReservations(); // Show active reservations
                                        case 3 -> showAvailableRooms();     // Show available rooms
                                        case 4 -> { // Disable reservation
                                            disableReservation();
                                            saveHotel();
                                        }
                                        case 5 -> { // Modify reservation
                                            changeReservationData();
                                            saveHotel();
                                        }
                                        case 6 -> { // Exit
                                            keyboard.writeLine("\n- Leaving...\n");
                                            condition3 = false;
                                        }
                                        default -> keyboard.writeLine("\n- Invalid option. Try again.");
                                    }
                                }
                            }

                            case 2 -> {
                                // HISTORICAL RECORD SECTION
                                boolean condition4 = true;
                                while (condition4) {
                                    showRecordMenu();
                                    option = keyboard.inputInt("\n- Enter an option: ");
                                    switch (option) {
                                        case 1 -> showReservationRecord();
                                        case 2 -> showGuestRecord();
                                        case 3 -> showEmployeeRecord();
                                        case 4 -> searchReservationInRecord();
                                        case 5 -> searchGuestInRecord();
                                        case 6 -> {
                                            keyboard.writeLine("\n- Leaving...\n");
                                            condition4 = false;
                                        }
                                        default -> keyboard.writeLine("\n- Invalid option. Try again.");
                                    }
                                }
                            }

                            case 3 -> {
                                // EMPLOYEE MANAGEMENT
                                boolean condition5 = true;
                                while (condition5) {
                                    showEmployeeMenu();
                                    option = keyboard.inputInt("\n- Enter an option: ");
                                    switch (option) {
                                        case 1 -> { // Add employee
                                            addEmployee();
                                            saveHotel();
                                        }
                                        case 2 -> showActiveEmployees(); // Show employees
                                        case 3 -> { // Modify employee info
                                            changeEmployeeInfo();
                                            saveHotel();
                                        }
                                        case 4 -> searchEmployeeByNumber(); // Search employee
                                        case 5 -> { // Dismiss employee
                                            dismissEmployee();
                                            saveHotel();
                                        }
                                        case 6 -> { // Exit
                                            keyboard.writeLine("\n- Leaving...\n");
                                            condition5 = false;
                                        }
                                        default -> keyboard.writeLine("\n- Invalid option. Try again.");
                                    }
                                }
                            }

                            case 4 -> { // Exit admin menu
                                keyboard.writeLine("\n- Leaving...");
                                condition2 = false;
                            }

                            default -> keyboard.writeLine("\n- Invalid option. Try again.");
                        }
                    }
                }

                case 3 -> menu = false; // Exit program

                default -> keyboard.writeLine("\n- Invalid option. Try again.");
            }
        }

        // Save all data and export CSV files when program ends
        saveHotel();

        String roomContent = roomEncoder.encode(hotel.getRoomList());
        saveToCsvFile("Rooms.csv", roomContent);

        String resContent = reservationEncoder.encode(hotel.getReservationList());
        saveToCsvFile("Reservations.csv", resContent);

        String guestContent = guestEncoder.encode(hotel.getGuestList());
        saveToCsvFile("Guests.csv", guestContent);

        String employeeContent = employeeEncoder.encode(hotel.getEmployeeList());
        saveToCsvFile("Employees.csv", employeeContent);
    }

    /*SHOW MENU METHODS:
     * They show menu options, in addition to printing some hotel class lists on the screen*/
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
                + "\n(5) Room price"
                + "\n(6) Go back.\n");
    }

    private static void showAdminMenu() {
        keyboard.writeLine("\n- - - - - - - - ADMINISTRATOR MENU - - - - - - - -"
                + "\n(1) Reservations."
                + "\n(2) Record."
                + "\n(3) Employees."
                + "\n(4) Go back.\n");

    }

    private static void showReservationMenu() {
        keyboard.writeLine("\n- - - - - - - - RESERVATION MENU - - - - - - - - "
                + "\n(1) Add reservation."
                + "\n(2) Show active Reservations."
                + "\n(3) Show available rooms."
                + "\n(4) Disable reservation."
                + "\n(5) Change reservation data."
                + "\n(6) Go back.\n");
    }

    private static void showRecordMenu() {
        keyboard.writeLine("\n(1) Show reservation record."
                + "\n(2) Show guest record."
                + "\n(3) Show employee record."
                + "\n(4) Search reservation in record."
                + "\n(5) Search guest in guest record."
                + "\n(6) Go back.\n");
    }
    
    private static void showChangeReservationMenu() {
        keyboard.writeLine("\n(1) Change guest name."
                + "\n(2) Change guest email."
                + "\n(3) Change guest phone number."
                + "\n(4) Change guest ID."
                + "\n(5) Change room."
                + "\n(6) Change reservation initial date."
                + "\n(7) Change reservation final date."
                + "\n(8) Go back.\n");
    }

    private static void showEmployeeMenu() {
        keyboard.writeLine("\n(1) Add employee."
                + "\n(2) Show employee list."
                + "\n(3) Change employee information."
                + "\n(4) Search employee."
                + "\n(5) Dismiss employeee."
                + "\n(6) Go back.\n");
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
                + "\n(7) Go back.\n");
    }
    // ------------------------------------------------------------------------------------------------

    //The method showHotelInfo() shows on the screen the attributes of hotel (name, adress, email, phoneNumber)
    private static void showHotelInfo() {
        keyboard.writeLine(hotel.toString());
    }

    // The method showActiveReservations() shows the reservations that are actives at the moment
    private static void showActiveReservations() {

        if (hotel.getReservationList().isEmpty()) {
            keyboard.writeLine("- - - - - - - - -\nNo active reservations found.\n - - - - - - - - -");
        } else {
            keyboard.writeLine("\n- - - - - - - - ACTIVE RESERVATIONS - - - - - - - -");
            for (Reservation reservation : hotel.getReservationList()) {
                if (reservation.getStatus() == true) {
                    keyboard.writeLine(reservation.toString());
                }
            }
        }
    }

    // The method showAvailableRooms() print the rooms that are available at the time of a reservation
    private static void showAvailableRooms() {

        if (hotel.getAvailableRooms().isEmpty()) {
            keyboard.writeLine("- - - - - - - - -\nNo available rooms found.\n - - - - - - - - -");
        } else {
            keyboard.writeLine("\n- - - - - - - - AVAILABLE ROOMS - - - - - - - -");
            for (Room room : hotel.getAvailableRooms()) {
                keyboard.writeLine(room.toString());
            }
            keyboard.writeLine("");
        }
    }

    // The method showReservationRecord() shows the entire historical reservation record of the hotel
    private static void showReservationRecord() {

        if (hotel.getReservationList().isEmpty()) {
            keyboard.writeLine("- - - - - - - - -\nNo reservations found.\n - - - - - - - - -");
        } else {
            keyboard.writeLine("\n- - - - - - - - RESERVATION RECORD - - - - - - - -");
            for (Reservation reservation : hotel.getReservationList()) {
                keyboard.writeLine(reservation.toString());
            }
            keyboard.writeLine("");
        }

    }

    // The method showGuestRecord() shows the entire historical guest record of the hotel
    private static void showGuestRecord() {

        if (hotel.getGuestList().isEmpty()) {
            keyboard.writeLine("- - - - - - - - -\nNo guests found.\n - - - - - - - - -");
        } else {
            keyboard.writeLine("\n- - - - - - - - GUEST RECORD - - - - - - - -");
            for (Guest guest : hotel.getGuestList()) {
                keyboard.writeLine(guest.toString());
            }
            keyboard.writeLine("");
        }
    }

    // The method showEmployeeList() print the entire record of hotel employees on the screen
    private static void showEmployeeRecord() {

        if (hotel.getEmployeeList().isEmpty()) {
            keyboard.writeLine("- - - - - - - - -\nNo employees found.\n - - - - - - - - -");
        } else {
            keyboard.writeLine("\n- - - - - - - - EMPLOYEE RECORD - - - - - - - -\n");
            for (Employee employee : hotel.getEmployeeList()) {
                keyboard.writeLine(employee.toString());
            }
        }
    }

    //The method showActiveEmployees() shows on screen the active employees of the hotel 
    private static void showActiveEmployees() {
        if (hotel.getEmployeeList().isEmpty()) {
            keyboard.writeLine("- - - - - - - - -\nNo employees found.\n - - - - - - - - -");
        } else {
            keyboard.writeLine("\n- - - - - - - - ACTIVE EMPLOYEES - - - - - - - -\n");
            for (Employee employee : hotel.getEmployeeList()) {
                if (employee.getActive() == true) {
                    keyboard.writeLine(employee.toString());
                }
            }
        }
    }

    // The method showRoomList() print the entire list of hotel rooms on the screen
    private static void showRoomList() {

        if (hotel.getRoomList().isEmpty()) {
            keyboard.writeLine("- - - - - - - - -\nNo rooms found.\n - - - - - - - - -");
        }
        keyboard.writeLine("\n- - - - - - - - ROOM LIST - - - - - - - -\n");
        for (Room room : hotel.getRoomList()) {
            keyboard.writeLine(room.toString());
        }

        keyboard.writeLine("");
    }

    // ------------------------------------------------------------------------------------------------

    // The method configHitel() configures the hotel, sending it its attributes through setters
    private static void configHotel() {
        boolean catcher = true;
        keyboard.writeLine("\n              CONFIGURE HOTEL");

        String name = keyboard.inputText("\n- Hotel name: ");

        while (catcher) {
            try {
                Double.parseDouble(name);
                name = keyboard.inputText("Error. Email can't be numbers.\n- Enter hotel's name: ");
            } catch (NumberFormatException e) {
                catcher = false;
            }
        }

        while (name.equals(hotel.getHotelName())) {
            name = keyboard.inputText("\nName entered is the actual hotel name.\n- Enter another name: ");
        }

        String adress = keyboard.inputText("\n- Hotel adress: ");

        catcher = true;
        while (catcher) {
            try {
                Double.parseDouble(adress);
                adress = keyboard.inputText("Error. Email can't be numbers.\n- Enter hotel's adress: ");
            } catch (NumberFormatException e) {
                catcher = false;
            }
        }
        
        while (adress.equals(hotel.getHotelAdress())) {
            adress = keyboard.inputText("\nAdress entered is the actual hotel adress.\n- Enter another adress: ");
        }

        String email = keyboard.inputText("\n- Hotel email: ");
        
        catcher = true;
        while (catcher) {
            try {
                Double.parseDouble(email);
                email = keyboard.inputText("Error. Email can't be numbers.\n- Enter hotel's email: ");
            } catch (NumberFormatException e) {
                catcher = false;
            }
        }

        while (email.equals(hotel.getHotelEmail())) {
            email = keyboard.inputText("\nEmail entered is the actual hotel email.\n- Enter another email: ");
        }

        long phoneNumber = keyboard.inputLong("\n- Hotel number: ");
        while (phoneNumber == hotel.getHotelPhoneNum()) {
            phoneNumber = keyboard.inputLong(
                    "\nPhone number entered is the actual hotel phone number.\n- Enter another phone number: ");
                }

        hotel.setHotelName(name);
        hotel.setHotelAdress(adress);
        hotel.setHotelEmail(email);
        hotel.setHotelPhoneNum(phoneNumber);
    }

    // The method changeInfo() is used to change hotel attributes like name, adress, phoneNumber and email
    private static void changeInfo() {
        keyboard.writeLine("\n- - - - - - - - - - - - - - - - - - - - - - -");
        showChangeHotelInfoMenu();
        int option = keyboard.inputInt("\n- What do you want to change?\n");

        switch (option) {
            case 1 -> {
                changeHotelInfo();
            }
            case 2 -> {
                changeRoomInfo();
            }
        }
    }

    // The method changeRoomInfo() is used to change rooms attributes like number, cuantity of beds and baths and price
    private static void changeRoomInfo() {
        showRoomList();
        int option = keyboard.inputInt("\n- Enter room number for changing its info: ");
        boolean condition = true;

        for (Room room : hotel.getRoomList()) {
            if (option == room.getRoomNum()) {
                keyboard.writeLine("\n- What do you want to change?\n");
                showRoomInfoMenu();
                option = keyboard.inputInt("\n- Enter an option: ");

                switch (option) {
                    case 1 -> {
                        int roomNumber = keyboard.inputInt("\n- Enter new room number for " + room.getRoomNum());
                        room.setRoomNum(roomNumber);
                    }
                    case 2 -> {
                        int beds = keyboard.inputInt("\n- Enter new cuantity of beds for room " + room.getRoomNum());
                        room.setRoomBeds(beds);
                    }
                    case 3 -> {
                        int baths = keyboard.inputInt("\n- Enter new cuantity of beds for room " + room.getRoomNum());
                        room.setRoomBaths(baths);
                    }
                    case 4 -> {
                        String roomType = keyboard.inputText("\n- Enter new type for room " + room.getRoomNum());
                        room.setRoomType(roomType);
                    }
                    case 5 -> {
                        double price = keyboard.inputDouble("\n- Enter new price for room " + room.getRoomNum());
                        room.setPrice(price);
                    }
                    case 6 -> {
                        keyboard.writeLine("\nLeaving...");
                        condition = false;
                    }
                    default -> {
                        keyboard.writeLine("\n- An error ocurred while trying to change info");
                    }
                }
            }
        }

        if (condition) {
            keyboard.writeLine("\n- Info changed succesfully.");
        } else {
            keyboard.writeLine("\n- No info was changed.");
        }

    }

    // The method changeEmployeeInfo() is useful to change employee attributes like name, salary and others
    private static void changeEmployeeInfo() {
        if (hotel.getEmployeeList().isEmpty()) {
            keyboard.writeLine("- - - - - - - - -\nNo employees found.\n - - - - - - - - -");
        } else {
            int employeeNumber = keyboard.inputInt("\n- Enter the Employee number that you want to change: ");
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

    }

    // The method changeReservationData() is useful to change employee attributes like room,  and others
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
                    boolean condition = true;
                    String initialDate = "";
                    do {
                        int day = keyboard.inputInt("\n- Enter reservation initial day: ");
                        int month = keyboard.inputInt("\n- Enter reservation initial month: ");
                        int year = keyboard.inputInt("\n- Enter reservation year: ");
                        if (dateValidation(day, month, year) == true) {
                            initialDate = day + "/" + month + "/" + year;
                            condition = false;
                        } else {
                            keyboard.writeLine("\n- The date that you entered doesn't exist.");
                        }

                    } while (condition);
                    hotel.findReservationByNumber(reservationNumber).setInitialDate(initialDate);
                }
                case 7 -> {
                    boolean condition = true;
                    String finalDate = "";
                    do {
                        int day = keyboard.inputInt("\n- Enter reservation final day: ");
                        int month = keyboard.inputInt("\n- Enter reservation final month: ");
                        int year = keyboard.inputInt("\n- Enter reservation final year: ");
                        if (dateValidation(day, month, year) == true) {
                            finalDate = day + "/" + month + "/" + year;
                            condition = false;
                        } else {
                            keyboard.writeLine("\n- The date that you entered doesn't exist.");
                        }

                    } while (condition);
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

    // The method changeHotelInfo() is useful to change employee attributes like name, adress, phone number and email
    private static void changeHotelInfo() {
        keyboard.writeLine("\n- - - - - - - - - - - - - - - - - -");
        showHotelInfo();
        boolean condition = true;

        int option = keyboard.inputInt(
                "\n- What do you want to change?\n(1) Change name\n(2) Change adress\n(3) Change email\n(4) Change telephone number\n(5) Leave\n-Enter an option: ");

        switch (option) {
            case 1 -> {
                String name = keyboard.inputText("\n- Enter new hotel name: ");
                hotel.setHotelName(name);
            }

            case 2 -> {
                String adress = keyboard.inputText("\n- Enter new hotel adress: ");
                hotel.setHotelAdress(adress);
            }
            
            case 3 -> {
                String email = keyboard.inputText("\n- Enter new hotel email: ");
                hotel.setHotelEmail(email);
            }

            case 4 -> {
                long phoneNumber = keyboard.inputLong("\n- Enter new hotel phone number: ");
                hotel.setHotelPhoneNum(phoneNumber);
            }

            case 5 -> {
                keyboard.writeLine("\n Leaving... \n");
                condition = false;
            }

            default -> {
                keyboard.writeLine("\n- An error ocurred selecting the option.");
            }
        }

        if (condition) {
            keyboard.writeLine("\n- Info changed succesfully.");
        } else {
            keyboard.writeLine("\n- No info was changed.");
        }
    }

    // ------------------------------------------------------------------------------------------------

    // The method addReservation() is used to create a reservation and add it to hotel's reservationList
    private static void addReservation() {
        if (hotel.getAvailableRooms().isEmpty()) {
            keyboard.writeLine(
                    "- - - - - - - - -\nNo available room at this time. Reservation can't be done.\n - - - - - - - - -");
        } else {
            String guestName = keyboard.inputText("\n- Enter guest name: ");
            String guestEmail = keyboard.inputText("\n- Enter guest email adress: ");
            long phoneNumber = keyboard.inputLong("\n- Enter guest phone number: ");
            long id = keyboard.inputLong("\n- Enter guest ID: ");

            Guest guest = new Guest(guestName, guestEmail, phoneNumber, id);

            boolean condition = true;
            String initialDate = "";
            do {
                int day = keyboard.inputInt("\n- Enter reservation initial day: ");
                int month = keyboard.inputInt("\n- Enter reservation initial month: ");
                int year = keyboard.inputInt("\n- Enter reservation year: ");

                if (dateValidation(day, month, year) == true) {
                    initialDate = day + "/" + month + "/" + year;
                    condition = false;
                } else {
                    keyboard.writeLine("\n- The date that you entered doesn't exist.");
                }
                
            } while (condition);

            condition = true;
            String finalDate = "";
            do {
                int day = keyboard.inputInt("\n- Enter reservation final day: ");
                int month = keyboard.inputInt("\n- Enter reservation final month: ");
                int year = keyboard.inputInt("\n- Enter reservation final year: ");

                if (dateValidation(day, month, year) == true) {
                    finalDate = day + "/" + month + "/" + year;
                    condition = false;
                } else {
                    keyboard.writeLine("\n- The date that you entered doesn't exist.");
                }
            } while (condition);

            showAvailableRooms();
            condition = false;

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
    }
    
    // The method addRoom() is used to create a room and add it to hotel's roomList
    private static void addRoom() {
        keyboard.writeLine("\n              ADD ROOM");

        int roomNumber = keyboard.inputInt("\n- Room number: ");
        for (Room room : hotel.getRoomList()) {
            while (roomNumber == room.getRoomNum()) {
                roomNumber = keyboard.inputInt("\n- Room number already exists.\n- Room number: ");
            }
        }

        int beds = keyboard.inputInt("\n- Cuantity of beds: ");
        int baths = keyboard.inputInt("\n- Cuantity of baths: ");
        String type = keyboard.inputText("\n- Room type (Basic/Medium/Suit): ");
        double price = keyboard.inputDouble("\n- Room price per night: ");
        hotel.addRoom(new Room(roomNumber, beds, baths, type.toLowerCase(), price));
        keyboard.writeLine("Room added succesfully.\n");
    }

    // The method addEmployee() is used to create a employee and add it to hotel's
    // employeeList
    private static void addEmployee() {
        boolean catcher = true;
        keyboard.writeLine("\n              ADD EMPLOYEE");

        String name = keyboard.inputText("\n- Enter employee's name: ");

        while (catcher) {
            try {
                Double.parseDouble(name);
                name = keyboard.inputText("Error. Name can't be numbers.\n- Enter employee's name: ");
            } catch (NumberFormatException e) {
                catcher = false;
            }
        }

        for (Employee employee : hotel.getEmployeeList()) {
            while (name.equals(employee.getName())) {
                name = keyboard.inputText("Employee already registered.\n- Enter employee's name: ");
            }
        }

        catcher = true;

        String email = keyboard.inputText("\n- Enter employee's email adress: ");

        while (catcher) {
            try {
                Double.parseDouble(email);
                email = keyboard.inputText("Error. Email can't be numbers.\n- Enter employee's name: ");
            } catch (NumberFormatException e) {
                catcher = false;
            }
        }

        catcher = true;

        for (Employee employee : hotel.getEmployeeList()) {
            while (email.equals(employee.getEmail())) {
                email = keyboard.inputText("Employee already registered.\n- Enter employee's email: ");
            }
        }
        
        long phoneNumber = keyboard.inputLong("\n- Enter employee's phone number: ");
        for (Employee employee : hotel.getEmployeeList()) {
            while (phoneNumber == employee.getId()) {
                phoneNumber = keyboard
                        .inputLong("Employee phone number already registered.\n- Enter employee's phone number: ");
                        
                    }
        }
        
        long id = keyboard.inputLong("\n- Enter employee's ID:");
        for (Employee employee : hotel.getEmployeeList()) {
            while (id == employee.getId()) {
                id = keyboard.inputLong("Employee's ID already used.\n- Enter employee's ID: ");
            }
            
        }

        String position = keyboard.inputText("\n- Enter employee's position: ");

        while (catcher) {
            try {
                Double.parseDouble(position);
                position = keyboard.inputText("Error. Position can't be numbers.\n- Enter employee's position: ");
            } catch (NumberFormatException e) {
                catcher = false;
            }
        }

        double salary = keyboard.inputDouble("\n- Enter employee's salary: ");
        hotel.addEmployee(new Employee(name, email, phoneNumber, id, position, salary));
        keyboard.writeLine("\n- Employee added succesfully.");
    }

    // The method is useful to disable a employee because he has beeen fired
    private static void dismissEmployee() {
        int employeeNumber = keyboard.inputInt("\n- Enter the employee number that you want to dismiss: ");
        if (employeeNumber > 0 && employeeNumber <= hotel.getEmployeeList().size()) {
            hotel.findEmployeeByNumber(employeeNumber).dismissEmployee();
            keyboard.writeLine("\n- Employee dismissed successfully.");
        } else {
            keyboard.writeLine("\n- The employee doesn't exist, please try again.");
        }
    }

    // The method disableReservation() it is used to disable a reservation that has already ended
    private static void disableReservation() {
        boolean condition = false;
        long id = keyboard.inputLong("\n- Enter reservation number: ");
        for (Reservation reservation : hotel.getReservationList()) {
            if (id == reservation.getReservationNumber()) {
                hotel.disableReservation(reservation);
                keyboard.writeLine("\n- The reservation has been successfully disabled.");
                condition = true;
            }
        }
        if (!condition) {
            keyboard.writeLine("\n- The reservation doesn't exist. Try again");
        }
    }

    // ------------------------------------------------------------------------------------------------

    // The method searchReservationInRecord() print a reservation from record which is searched by the reservation number assigned to it
    private static void searchReservationInRecord() {

        if (hotel.getReservationList().isEmpty()) {
            keyboard.writeLine(
                    "- - - - - - - - -\nNo reservations registered. Operation can't be finished.\n - - - - - - - - -");
        } else {
            int reservationNumber = keyboard.inputInt("\n- Enter the reservation number that you want search: ");
            Reservation reservation = hotel.findReservationByNumber(reservationNumber);
            if (reservation != null) {
                keyboard.writeLine("\n- Reservation found successfully.");
                keyboard.writeLine(reservation.toString());
            } else {
                keyboard.writeLine("\n- The reservation doesn't exist, please try again.");
            }
        }
    }

    // The method searchGuestInRecord() print a guest from record which is searched by guest's id
    private static void searchGuestInRecord() {

        if (hotel.getGuestList().isEmpty()) {
            keyboard.writeLine(
                    "- - - - - - - - -\nNo guests registered. Operation can't be finished.\n - - - - - - - - -");
        } else {
            long guestId = keyboard.inputLong("\n- Enter the guest ID that you want search: ");
            Guest guest = hotel.findGuestById(guestId);
            if (guest != null) {
                keyboard.writeLine("\n- Guest found successfully.");
                keyboard.writeLine(guest.toString());
            } else {
                keyboard.writeLine("\n- The guest doesn't exist. Try again.");
            }
        }
    }

    // The method searchEmployeeByNumber() print an employee from record which is searched by the employee number assigned to it
    private static void searchEmployeeByNumber() {

        if (hotel.getEmployeeList().isEmpty()) {
            keyboard.writeLine(
                    "- - - - - - - - -\nNo employees registered. Operation can't be finished.\n - - - - - - - - -");
        } else {
            long employeeId = keyboard.inputLong("\n- Enter the employee number that you want search: ");
            Employee employee = hotel.findEmployeeByNumber(employeeId);
            if (employee != null) {
                keyboard.writeLine("\n- Employee found successfully.");
                keyboard.writeLine(employee.toString());
            } else {
                keyboard.writeLine("\n- The employee doesn't exist, please try again.");
            }
        }
    }
    // ------------------------------------------------------------------------------------------------

    // The method dateValidation() does a validation of a date and return true if is correct or false if the date doesn't exist
    private static boolean dateValidation(int day, int month, int year) {
        if ((year > 2025 || year <= 9999) && (month > 0 || month < 13) && (day > 0 && day < 32)) {
            if (month == 2 && day < 29) {
                return true;
            }
            if ((month == 4 || month == 6 || month == 9 || month == 11) && day < 31) {
                return true;
            }
            if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
                    || month == 12) && day <= 31) {
                return true;
            }
        }
        return false;
    }

    // The method loadHotel() upload the hotel information that has been previously saved
    private static void loadHotel() {
        var loaded = HotelStorage.load(FILE_NAME);
        if (loaded != null) {
            hotel = loaded;
            keyboard.writeLine("\n- Hotel loaded successfully");
        }
    }

    // The method saveHotel() saves changes that have been made during program runtime
    private static void saveHotel() {
        HotelStorage.save(hotel, FILE_NAME);
    }

    // The method saveToCsvFile() saves the information sent to you as an argument to a csv file
    private static void saveToCsvFile(String file_name, String content) {
        HotelStorage.save(file_name, content);
    }
}