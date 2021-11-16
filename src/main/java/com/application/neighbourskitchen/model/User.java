package com.application.neighbourskitchen.model;

import javax.persistence.Entity;

@Entity
public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private boolean isCook;
    private int phone;
    private long score;

    public User(long id, String firstName, String lastName, String address, boolean isCook, int phone, long score) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.isCook = isCook;
        this.phone = phone;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isCook() {
        return isCook;
    }

    public void setCook(boolean cook) {
        isCook = cook;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
