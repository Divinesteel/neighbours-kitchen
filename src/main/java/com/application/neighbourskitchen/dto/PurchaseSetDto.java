package com.application.neighbourskitchen.dto;

import com.application.neighbourskitchen.model.Purchase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class PurchaseSetDto {

    private Set<PurchaseDetailsDto> purchaseSetDto;

    public PurchaseSetDto(Set<Purchase> purchaseSetObject) {
        purchaseSetDto = new HashSet<>();

        purchaseSetObject.stream().forEach(purchase -> {
            this.purchaseSetDto.add(new PurchaseDetailsDto(purchase));
        });

    }
}
