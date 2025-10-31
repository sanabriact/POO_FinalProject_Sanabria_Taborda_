package domain;

import java.io.Serializable;

public class Reservation implements Serializable{
     private Guest guest;
    private Room room;
    private int resNumber = 0;
    private boolean status;

    public Reservation(Guest guest ,Room room ){
        this.guest = guest;
        this.room = room;
        this.resNumber += 1;
        this.status = true;
    }

    public Guest getGuest(){
        return guest;
    }

    public Room getRoom(){
        return room;
    } 

    public boolean getStatus(){
        return status;
    }

    public int getReservationNumber(){
        return resNumber;
    }

    public void setGuest(Guest guest){
        this.guest = guest;
    }

    public void disableReservation(){
        this.status = false;
        room.setAvailabilty(true);
    }

    public void setRoom(Room room){
        this.room = room;    
    } 

    @Override
    public String toString() {

        return "Reservation: {Number: " + resNumber + ", status: " + status + "}";
    }
}
