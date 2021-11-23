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

    Set<FoodDetailsDto> availableFoods;

    public AllAvailableFoodsDto(List<Food> allFoodsList){
        availableFoods = new HashSet<>();

        allFoodsList.forEach(food -> {
            if(food.getPortions() >= 1){
                FoodDetailsDto foodDetailsDto = new FoodDetailsDto(food);
                availableFoods.add(foodDetailsDto);
            }
        });
    }

}
