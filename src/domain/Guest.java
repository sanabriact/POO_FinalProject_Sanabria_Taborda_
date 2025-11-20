package domain;

import java.io.Serializable;

public class Guest implements Serializable {
    private String name;
    private String email;
    private long phoneNumber;
    private long id;

    public Guest(String name, String email, long phoneNumber, long id) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getPhoneNum() {
        return phoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            this.name = null;
        } else {
            this.name = name;
        }
    }

    public void setEmail(String email) {
        if (email.isEmpty()) {
            this.email = null;
        } else {
            this.email = email;
        }
    }

    public void setPhoneNumber(long phoneNumber) {
        if (phoneNumber > 0) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = 0;
        }
    }

    public void setId(long id) {
        if (id > 0) {
            this.id = id;
        } else {
            this.id = 0;
        }
    }

    @Override
    public String toString() {
        return "Guest{id=" + id + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + "}";
    }
}
