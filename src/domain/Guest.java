package domain;

import java.io.Serializable;

public class Guest implements Serializable{
    private String name;
    private String email;
    private int phoneNumber;
    private int id;

    public Guest(String name, String email, int phoneNumber, int id){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public String getGuestName(){
        return name;
    }

    public String getGuestEmail(){
        return email;
    }

    public int getGuestPhoneNum(){
        return phoneNumber;
    }

    public int getGuestId(){
        return id;
    }

    public void setGuestName(String name){
        if(name.isEmpty()){
            this.name = null;
        }else{
            this.name = name;
        }
    }

    public void setGuestEmail(String email){
        if(email.isEmpty()){
            this.email = null;
        }else{
            this.email = email;
        }
    }

    public void setPhoneNumber(int phoneNumber){
        if(phoneNumber > 0){
            this.phoneNumber = phoneNumber;
        }else{
            this.phoneNumber = 0;
        }
    }

    public void setGuestId(int id){
        if(id > 0){
            this.id = id;
        }else{
            this.id = 0;
        }
    }
}
