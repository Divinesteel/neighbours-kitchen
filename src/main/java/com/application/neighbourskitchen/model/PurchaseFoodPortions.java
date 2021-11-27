package com.application.neighbourskitchen.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor
public class PurchaseFoodPortions {

    @EmbeddedId
    private PurchaseFoodId purchaseFoodId = new PurchaseFoodId();

    @ManyToOne
    @MapsId("comPurchaseId")
    private Purchase purchase;

    @ManyToOne
    @MapsId("comFoodId")
    private Food food;

    private int portions;

    public PurchaseFoodPortions() {
    }

    public PurchaseFoodPortions(Purchase purchase, Food food, int portions) {
        this.purchase = purchase;
        this.food = food;
        this.portions = portions;
    }

    public PurchaseFoodId getPurchaseFoodId() {
        return purchaseFoodId;
    }

    public void setPurchaseFoodId(PurchaseFoodId purchaseFoodId) {
        this.purchaseFoodId = purchaseFoodId;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseFoodPortions that = (PurchaseFoodPortions) o;
        return portions == that.portions && Objects.equals(purchaseFoodId, that.purchaseFoodId) && Objects.equals(purchase, that.purchase) && Objects.equals(food, that.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseFoodId, purchase, food, portions);
    }
}
