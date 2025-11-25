package domain;

import java.io.Serializable;

public class Reservation implements Serializable {
    // static int because all reservations share this attribute. For each new
    // reservation "resNumber" increases by one
    private static int nextNumber = 0;
    private Guest guest;
    private Room room;
    private int resNumber;
    private boolean status;
    private String initialDate;
    private String finalDate;

    public Reservation(Guest guest, Room room, String initialDate, String finalDate) {
        this.guest = guest;
        this.room = room;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        nextNumber++;
        this.resNumber = nextNumber;
        this.status = true;
        this.room.setAvailabilty(false);
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public boolean getStatus() {
        return status;
    }

    public int getReservationNumber() {
        return resNumber;
    }

    public static int getNextNumber() {
        return nextNumber;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }

    public static void setNextNumber(int number) {
        nextNumber = number;
    }

    @Override
    public String toString() {
        return "Reservation: {reservationNumber: " + resNumber + ", room: " + getRoom().getRoomNum() + ", roomType: "
                + getRoom().getRoomType() + ", guest: " + getGuest().getName() + ", status: " + status
                + ", initial date: " + initialDate
                + ", final date: " + finalDate + "}";
    }
}
