package com.application.neighbourskitchen.model;

import java.util.Date;

public class Order {

    private long sellerId;
    private long buyerId;
    private Date date;
    private double price;

    public Order(long sellerId, long buyerId, Date date, double price) {
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.date = date;
        this.price = price;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
