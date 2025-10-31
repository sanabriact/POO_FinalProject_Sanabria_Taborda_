package domain;

import java.io.Serializable;

public class Room implements Serializable {
    private int roomNum, beds, baths;
    private String roomType = "";

    public Room(int roomNum, int beds, int baths, String roomType) {
        this.roomNum = roomNum;
        this.beds = beds;
        this.baths = baths;
        this.roomType = roomType;

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

    public boolean getSeaView() {
        if (this.roomType.equals("Basic")) {
            return false;
        }

        return true;
    }

    public double getPrice() {
        if (this.roomType.equals("Basic")) {
            return 39.99;
        }

        if (this.roomType.equals("Medium")) {
            return 59.99;
        }

        return 99.99;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public void setRoomBeds(int beds) {
        this.beds = beds;
        ;
    }

    public void setRoomBaths(int baths) {
        this.baths = baths;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Room{roomNum=" + roomNum + ", roomType=" + roomType + ", beds=" + beds + ", baths=" + baths + "}";
    }

}
