package domain;

import java.io.Serializable;

public class Guest implements Serializable {
    private String name;
    private String email;
    private long phoneNumber;
    private long id;

    // Constructor that initializes the guest with validated setters
    public Guest(String name, String email, long phoneNumber, long id) {
        this.setName(name);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.setId(id);
    }

    // Returns the guest's name
    public String getName() {
        return name;
    }

    // Returns the guest's email address
    public String getEmail() {
        return email;
    }

    // Returns the guest's phone number
    public long getPhoneNum() {
        return phoneNumber;
    }

    // Returns the guest's identification number
    public long getId() {
        return id;
    }

    // Sets the guest's name; assigns null if the provided name is empty
    public void setName(String name) {
        if (name.isEmpty()) {
            this.name = null;
        } else {
            this.name = name;
        }
    }

    // Sets the guest's email; assigns null if the provided email is empty
    public void setEmail(String email) {
        if (email.isEmpty()) {
            this.email = null;
        } else {
            this.email = email;
        }
    }

    // Sets the guest's phone number; accepts only positive values
    public void setPhoneNumber(long phoneNumber) {
        if (phoneNumber > 0) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = 0;
        }
    }

    // Sets the guest's ID; accepts only positive values
    public void setId(long id) {
        if (id > 0) {
            this.id = id;
        } else {
            this.id = 0;
        }
    }

    // Returns a string representation of the guest object
    @Override
    public String toString() {
        return "Guest{id=" + id + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + "}";
    }
}