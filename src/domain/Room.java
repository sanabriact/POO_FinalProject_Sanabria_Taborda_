package domain;

import java.io.Serializable;

public class Room implements Serializable {
    private int roomNum, beds, baths;
    private String roomType = "";
    private boolean available;
    private double price;

    // Constructor that initializes the room with basic attributes and default availability
    public Room(int roomNum, int beds, int baths, String roomType, double price) {
        this.setRoomNum(roomNum);
        this.setRoomBeds(beds);
        this.setRoomBaths(baths);
        this.available = true; // Room is available by default
        this.setRoomType(roomType);
        this.setPrice(price);
    }

    // Returns the room number
    public int getRoomNum() {
        return this.roomNum;
    }

    // Returns the number of beds
    public int getRoomBeds() {
        return this.beds;
    }

    // Returns the number of bathrooms
    public int getRoomBaths() {
        return this.baths;
    }

    // Returns the room type (Basic, Medium, Suit)
    public String getRoomType() {
        return this.roomType;
    }

    // Returns the room's price
    public double getPrice() {
        return price;
    }

    // Returns whether the room is available
    public boolean getAvailability() {
        return this.available;
    }

    // Sets the room number (must be positive; ignored otherwise)
    public void setRoomNum(int roomNum) {
        if (roomNum > 0) {
            this.roomNum = roomNum;
        } else {
            this.roomNum = 0;
        }
    }

    // Sets the number of beds (must be >= 1; ignored otherwise)
    public void setRoomBeds(int beds) {
        if (beds > 0) {
            this.beds = beds;
        } else {
            this.beds = 0;
        }
    }

    // Sets the number of bathrooms (must be >= 1; ignored otherwise)
    public void setRoomBaths(int baths) {
        if (baths > 0) {
            this.baths = baths;
        } else {
            this.baths = 0;
        }
    }

    // Sets the room type (only Basic, Medium, Suit; invalid values are ignored)
    public void setRoomType(String roomType) {
        if (roomType.equals("basic") || roomType.equals("medium") || roomType.equals("suit")) {
            this.roomType = roomType;
        } else {
            this.roomType = null;
        }
    }

    // Sets the availability flag for the room
    public void setAvailabilty(boolean available) {
        this.available = available;
    }

    // Sets the price (must be greater than 0; otherwise defaults to 0)
    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            this.price = 0; 
        }
    }

    // Returns a string representation of the room
    @Override
    public String toString() {
        return "Room{roomNum=" + roomNum + ", roomType=" + roomType +
               ", beds=" + beds + ", baths=" + baths +
               ", price=" + price + ", available=" + available + "}";
    }
}