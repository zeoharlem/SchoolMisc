package com.zeoharlem.gads.schoolmisc.Models;

public class Teachers {
    private String fullName;
    private String location;
    private String phoneNumber;
    private String email;

    public Teachers(String fullName, String location, String phoneNumber, String email) {
        this.fullName       = fullName;
        this.location       = location;
        this.phoneNumber    = phoneNumber;
        this.email          = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
