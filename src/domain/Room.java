package domain;

import java.io.Serializable;

public class Room implements Serializable {
    private int roomNum, beds, baths;
    private String roomType = "";
    private boolean available;
    private double price;

    public Room(int roomNum, int beds, int baths, String roomType, double price) {
        this.roomNum = roomNum;
        this.beds = beds;
        this.baths = baths;
        this.available = true;
        this.price = price;
        if (roomType.toLowerCase().equals("basic") || roomType.toLowerCase().equals("medium") || roomType.toLowerCase().equals("suit")) {
            this.roomType = roomType;
        }else{
            this.roomType = null;
        }

    }

    public int getRoomNum() {
        return this.roomNum;
    }

    public int getRoomBeds() {
        return this.beds;
    }

    public int getRoomBaths() {
        return this.baths;
    }

    public String getRoomType() {
        return this.roomType;
    }

    public double getPrice() {
        return price;
    }

    public boolean getAvailability() {
        return this.available;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public void setRoomBeds(int beds) {
        this.beds = beds;
    }

    public void setRoomBaths(int baths) {
        this.baths = baths;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setAvailabilty(boolean available) {
        this.available = available;
    }

    public void setPrice(double price){
        if(price<= 0){
            this.price = 0;
        }else{
            this.price = price;
        }
    }

    @Override
    public String toString() {
        return "Room{roomNum=" + roomNum + ", roomType=" + roomType + ", beds=" + beds + ", baths=" + baths + "}";
    }
}
