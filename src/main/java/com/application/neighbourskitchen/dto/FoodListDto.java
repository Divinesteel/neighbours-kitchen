package com.application.neighbourskitchen.dto;

import com.application.neighbourskitchen.model.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class FoodListDto {

    private Set<FoodDetailsDto> foodDetailsList;

    public FoodListDto(Set<Food> foodList) {
        foodDetailsList = new HashSet<>();
        foodList.forEach(food->{
            FoodDetailsDto foodDetailsDto = new FoodDetailsDto(food);
            foodDetailsList.add(foodDetailsDto);
        });
    }
}
