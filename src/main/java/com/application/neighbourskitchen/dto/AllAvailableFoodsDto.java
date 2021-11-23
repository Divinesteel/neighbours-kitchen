package com.application.neighbourskitchen.dto;

import com.application.neighbourskitchen.model.Food;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class AllAvailableFoodsDto {

    private Set<FoodDetailsDto> availableFoods;

    public AllAvailableFoodsDto(Set<Food> availableFoods){
        this.availableFoods = new HashSet<>();

        availableFoods.forEach(food -> {
            FoodDetailsDto foodDetailsDto = new FoodDetailsDto(food);
            this.availableFoods.add(foodDetailsDto);
        });
    }

}
