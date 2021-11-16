package com.application.neighbourskitchen.model;

import java.util.Date;

public class Food {

    private long id;
    private Byte[] image;
    private String title;
    private String description;
    private Date timeCooked;
    private int portions;
    private int packages;
    private double price;

    public Food(long id, Byte[] image, String title, String description, Date timeCooked, int portions, int packages, double price) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.timeCooked = timeCooked;
        this.portions = portions;
        this.packages = packages;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimeCooked() {
        return timeCooked;
    }

    public void setTimeCooked(Date timeCooked) {
        this.timeCooked = timeCooked;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public int getPackages() {
        return packages;
    }

    public void setPackages(int packages) {
        this.packages = packages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
