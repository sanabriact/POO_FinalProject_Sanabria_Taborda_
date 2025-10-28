package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hotel implements Serializable{
    private List<Room> rooms;
    private List<Reservation> reservations;
    private List<Guest> guests;
    private String name = "", adress = "", hotelEmail = "";
    private int hotelPhoneNum;

    public Hotel(String name, String adress, String hotelEmail, int hotelPhoneNum) {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        guests = new ArrayList<>();

        this.name = name;
        this.adress = adress;
        this.hotelPhoneNum = hotelPhoneNum;
        this.hotelEmail = hotelEmail;

    }

    public String getHotelName() {
        return this.name;
    }

    public String getHotelAdress() {
        return this.adress;
    }

    public String getHotelEmail() {
        return this.hotelEmail;
    }

    public int getHotelPhoneNum() {
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

    public void setHotelName(String name) {
        this.name = name;
    }

    public void setHotelAdress(String adress) {
        this.adress = adress;
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public void setHotelPhoneNum(int hotelPhoneNum) {
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
}