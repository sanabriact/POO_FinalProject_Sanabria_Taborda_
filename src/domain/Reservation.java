package domain;

import java.io.Serializable;

public class Reservation implements Serializable {

    // Static counter that assigns a unique sequential number to each reservation
    private static int nextNumber = 0;
    private Guest guest;
    private Room room;
    private int resNumber;
    private boolean status;
    private String initialDate;
    private String finalDate;

    // Constructor that creates a new reservation and assigns a unique number
    public Reservation(Guest guest, Room room, String initialDate, String finalDate) {
        this.setGuest(guest);                     
        this.setRoom(room);        
        this.setInitialDate(initialDate);         
        this.setFinalDate(finalDate);             
        nextNumber++;                           
        this.resNumber = nextNumber;            
        this.status = true;                     
        this.room.setAvailabilty(false);
    }

    // Returns the guest associated with the reservation
    public Guest getGuest() {
        return guest;
    }

    // Returns the room assigned to the reservation
    public Room getRoom() {
        return room;
    }

    // Returns the reservation status (true = active, false = inactive)
    public boolean getStatus() {
        return status;
    }

    // Returns the unique reservation number
    public int getReservationNumber() {
        return resNumber;
    }

    // Returns the next reservation number that will be assigned
    public static int getNextNumber() {
        return nextNumber;
    }

    // Returns the check-in date of the reservation
    public String getInitialDate() {
        return initialDate;
    }

    // Returns the check-out date of the reservation
    public String getFinalDate() {
        return finalDate;
    }

    // Sets the guest for the reservation (no validation required)
    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    // Sets the room for the reservation (no validation required)
    public void setRoom(Room room) {
        this.room = room;
    }

    // Sets the reservation status (true = active, false = inactive)
    public void setStatus(boolean status) {
        this.status = status;
    }

    // Sets the initial date; assigns null if the input is empty
    public void setInitialDate(String initialDate) {
        if (initialDate.isEmpty()) {
            this.initialDate = null;
        } else {
            this.initialDate = initialDate;
        }
    }

    // Sets the final date; assigns null if the input is empty
    public void setFinalDate(String finalDate) {
        if (finalDate.isEmpty()) {
            this.finalDate = null;
        } else {
            this.finalDate = finalDate;
        }
    }

    // Updates the static reservation counter
    public static void setNextNumber(int number) {
        nextNumber = number;
    }

    // Returns a text representation of the reservation with its key details
    @Override
    public String toString() {
        return "Reservation: {reservationNumber: " + resNumber +
               ", room: " + getRoom().getRoomNum() +
               ", roomType: " + getRoom().getRoomType() +
               ", guest: " + getGuest().getName() +
               ", status: " + status +
               ", initial date: " + initialDate +
               ", final date: " + finalDate + "}";
    }
}
