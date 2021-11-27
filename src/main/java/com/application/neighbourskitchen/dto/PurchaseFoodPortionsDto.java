package com.application.neighbourskitchen.dto;

import com.application.neighbourskitchen.model.PurchaseFoodPortions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PurchaseFoodPortionsDto {

    private PurchaseDetailsDto purchase;
    private FoodDetailsDto food;
    private int portions;

    public PurchaseFoodPortionsDto(PurchaseFoodPortions purchaseFoodPortions) {
        this.purchase = new PurchaseDetailsDto(purchaseFoodPortions.getPurchase());
        this.food = new FoodDetailsDto(purchaseFoodPortions.getFood());
        this.portions = purchaseFoodPortions.getPortions();
    }
}
