package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hotel implements Serializable{
    private List<Room> rooms;
    private List<Reservation> reservations;
    private List<Guest> guests;
    private String hotelName = "", hotelAdress = "", hotelEmail = "";
    private long hotelPhoneNum;

    public Hotel(String hotelName, String hotelAdress, String hotelEmail, long hotelPhoneNum) {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        guests = new ArrayList<>();

        this.hotelName = hotelName;
        this.hotelAdress = hotelAdress;
        this.hotelPhoneNum = hotelPhoneNum;
        this.hotelEmail = hotelEmail;

    }

    public String getHotelName() {
        return this.hotelName;
    }

    public String getHotelAdress() {
        return this.hotelAdress;
    }

    public String getHotelEmail() {
        return this.hotelEmail;
    }

    public Long getHotelPhoneNum() {
        return this.hotelPhoneNum;
    }

    public List<Room> getRoomList() {
        return rooms;
    }

    public List<Reservation> getReservationList() {
        return reservations;
    }

    public List<Guest> getGuestsList() {
        return guests;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setHotelAdress(String hotelAdress) {
        this.hotelAdress = hotelAdress;
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public void setHotelPhoneNum(long hotelPhoneNum) {
        this.hotelPhoneNum = hotelPhoneNum;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        Guest guest = reservation.getGuest();
        guests.add(guest);
    }

    public void deleteRoom(Room room) {
        rooms.remove(room);
    }

    public void deleteReservation(Reservation reservation) {
        reservations.remove(reservation);
        Guest guest = reservation.getGuest();
        guests.remove(guest);
    }

    public String findReservationById(int num){
        for(Reservation r: reservations) {
            if(num == (r.getReservationNumber())){
                return r.toString();
            }
        }

        return null;
    }

    public String findGuestById(int ID) {
        for(Guest g: guests) {
            if(ID == (g.getGuestId())){
                return g.toString();
            }
        }

        return null;
    }

    
}
