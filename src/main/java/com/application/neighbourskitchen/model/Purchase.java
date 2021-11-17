package com.application.neighbourskitchen.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Purchase {

    @EmbeddedId
    private BuyerSeller id;

    private double price;

    @MapsId("sellerId")
    @ManyToOne(cascade = CascadeType.ALL)
    private User seller;
    @MapsId("buyerId")
    @ManyToOne(cascade = CascadeType.ALL)
    private User buyer;

    public Purchase() {
        id = new BuyerSeller();
    }

    public Purchase(double price, User seller, User buyer) {
        id = new BuyerSeller();
        this.price = price;
        this.seller = seller;
        this.buyer = buyer;
    }

    public BuyerSeller getId() {
        return id;
    }

    public void setId(BuyerSeller id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
}
