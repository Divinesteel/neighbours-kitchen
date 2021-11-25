package com.application.neighbourskitchen.dto;

import com.application.neighbourskitchen.model.Purchase;
import com.application.neighbourskitchen.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@NoArgsConstructor
@Setter
@Getter
public class PurchaseDetailsDto {

    private double price;
    private User seller;
    private User buyer;
    private Date date;

    public PurchaseDetailsDto(Purchase purchase) {
        this.price = purchase.getPrice();
        this.seller = purchase.getSeller();
        this.buyer = purchase.getBuyer();
        this.date = purchase.getDate();
    }
}
