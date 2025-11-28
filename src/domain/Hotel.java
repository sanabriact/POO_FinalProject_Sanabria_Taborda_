package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hotel implements Serializable {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private List<Guest> guests;
    private List<Employee> employees;
    private String hotelName = "", hotelAdress = "", hotelEmail = "";
    private long hotelPhoneNum;

    public Hotel() {
        // Initializes the lists where rooms, reservations, guests, and employees will be stored.
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        guests = new ArrayList<>();
        employees = new ArrayList<>();
    }

    // Returns the hotel’s name.
    public String getHotelName() {
        return this.hotelName;
    }

    // Returns the hotel’s address.
    public String getHotelAdress() {
        return this.hotelAdress;
    }

    // Returns the hotel’s email.
    public String getHotelEmail() {
        return this.hotelEmail;
    }

    // Returns the hotel’s phone number.
    public Long getHotelPhoneNum() {
        return this.hotelPhoneNum;
    }

    // Returns the list of all rooms in the hotel.
    public List<Room> getRoomList() {
        return rooms;
    }

    // Returns the list of all reservations in the hotel.
    public List<Reservation> getReservationList() {
        return reservations;
    }

    // Returns the list of all guests registered through reservations.
    public List<Guest> getGuestList() {
        return guests;
    }

    // Returns the list of all hotel employees.
    public List<Employee> getEmployeeList() {
        return employees;
    }

    // Sets the name of the hotel.
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    // Sets the address of the hotel.
    public void setHotelAdress(String hotelAdress) {
        this.hotelAdress = hotelAdress;
    }

    // Sets the hotel’s email.
    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    // Sets the hotel's phone number.
    public void setHotelPhoneNum(long hotelPhoneNum) {
        this.hotelPhoneNum = hotelPhoneNum;
    }

    // Adds a room to the hotel’s room list.
    public void addRoom(Room room) {
        rooms.add(room);
    }

    // Adds an employee to the hotel's employee list.
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Adds a reservation, and automatically registers its guest in the guest list.
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        Guest guest = reservation.getGuest();
        guests.add(guest);
    }

    // Removes a room from the hotel’s room list.
    public void deleteRoom(Room room) {
        rooms.remove(room);
    }

    // Removes a reservation and also removes the associated guest from the guest list.
    public void deleteReservation(Reservation reservation) {
        reservations.remove(reservation);
        Guest guest = reservation.getGuest();
        guests.remove(guest);
    }

    // Searches for a reservation by its reservation number. Returns null if not found.
    public Reservation findReservationByNumber(long resNumber) {
        for (Reservation reservation : reservations) {
            if (resNumber == (reservation.getReservationNumber())) {
                return reservation;
            }
        }
        return null;
    }

    // Searches for a guest by ID. Returns the matching guest or null if none is found.
    public Guest findGuestById(long id) {
        for (Guest guest : guests) {
            if (id == (guest.getId())) {
                return guest;
            }
        }
        return null;
    }

    // Searches for a room by room number. Returns the room or null if not found.
    public Room findRoomById(int id) {
        for (Room room : rooms) {
            if (id == room.getRoomNum()) {
                return room;
            }
        }
        return null;
    }

    // Searches for an employee by employee number.
    public Employee findEmployeeByNumber(long employeeNumber){
        for(Employee employee : employees){
            if(employeeNumber == employee.getEmployeeNumber()){
                return employee;
            }
        }
        return null;
    }

    // Returns a list of all rooms that are currently available.
    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getAvailability() == true) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    // Disables a reservation (sets status to false) and marks its room as available again.
    public void disableReservation(Reservation reservation) {
        reservation.setStatus(false);
        reservation.getRoom().setAvailabilty(true);
    }

    // Returns a string representation of the hotel’s main information.
    public String toString() {
        return "Hotel info\n-Name: " + this.hotelName + "\n-Adress: " + this.hotelAdress + "\n-Email: " + hotelEmail
                + "\n-Number: " + hotelPhoneNum;
    }

}
