package com.application.neighbourskitchen.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BuyerSeller implements Serializable {

    @Column(name = "seller_id")
    private Long sellerId;
    @Column(name = "buyer_id")
    private Long buyerId;
    @Column(name = "gen_id")
    private Long genId;

    public BuyerSeller() {
    }

    public BuyerSeller(Long sellerId, Long buyerId, Long gen) {
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.genId = 1l;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getGenId() {
        return genId;
    }

    public void setGenId(Long genId) {
        this.genId = genId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyerSeller that = (BuyerSeller) o;
        return Objects.equals(sellerId, that.sellerId) && Objects.equals(buyerId, that.buyerId) && Objects.equals(genId, that.genId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sellerId, buyerId, genId);
    }
}
