package com.application.neighbourskitchen.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PurchaseFoodId implements Serializable {

    @Column(name ="purchase_id")
    private Long comPurchaseId;
    @Column(name ="food_id")
    private Long comFoodId;

    public PurchaseFoodId() {
    }

    public PurchaseFoodId(Long purchaseId, Long foodId) {
        this.comPurchaseId = purchaseId;
        this.comFoodId = foodId;
    }

    public Long getComPurchaseId() {
        return comPurchaseId;
    }

    public void setComPurchaseId(Long comPurchaseId) {
        this.comPurchaseId = comPurchaseId;
    }

    public Long getComFoodId() {
        return comFoodId;
    }

    public void setComFoodId(Long comFoodId) {
        this.comFoodId = comFoodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseFoodId that = (PurchaseFoodId) o;
        return Objects.equals(comPurchaseId, that.comPurchaseId) && Objects.equals(comFoodId, that.comFoodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comPurchaseId, comFoodId);
    }
}
