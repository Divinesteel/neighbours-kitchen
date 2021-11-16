package com.application.neighbourskitchen.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class BuyerSeller implements Serializable {

    @Column(name = "buyer_id")
    private Long buyerId;
    @Column(name = "seller_id")
    private Long sellerId;
    @Column(name = "date")
    private Date date;
}
