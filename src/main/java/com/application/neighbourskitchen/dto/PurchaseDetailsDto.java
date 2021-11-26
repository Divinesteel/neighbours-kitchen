package com.application.neighbourskitchen.dto;

import com.application.neighbourskitchen.model.Purchase;
import com.application.neighbourskitchen.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PurchaseDetailsDto {

    private double price;
    private UserDetailsDto seller;
    private UserDetailsDto buyer;
    private Date date;
    private boolean isCompleted;

    public PurchaseDetailsDto(Purchase purchase) {
        this.price = purchase.getPrice();
        this.seller = new UserDetailsDto(purchase.getSeller());
        this.buyer = new UserDetailsDto(purchase.getBuyer());
        this.date = purchase.getDate();
        this.isCompleted = purchase.isCompleted();
    }
}
