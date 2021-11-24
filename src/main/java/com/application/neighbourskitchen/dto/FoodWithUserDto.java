package com.application.neighbourskitchen.dto;

import com.application.neighbourskitchen.model.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class FoodWithUserDto extends UserDetailsDto{

    FoodDetailsDto food;
}
