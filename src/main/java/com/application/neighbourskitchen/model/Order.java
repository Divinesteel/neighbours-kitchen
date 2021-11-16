package com.application.neighbourskitchen.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Order {

    @EmbeddedId
    private BuyerSeller id;

    private double price;

    @MapsId("sellerId")
    @ManyToOne
    private User seller;
    @MapsId("buyerId")
    @ManyToOne
    private User buyer;


}
