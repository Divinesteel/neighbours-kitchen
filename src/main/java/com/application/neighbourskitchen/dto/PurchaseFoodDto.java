package com.application.neighbourskitchen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PurchaseFoodDto extends UserDetailsDto{

    FoodDetailsDto food;
    PurchaseDetailsDto purchase;
    int portions;
}
