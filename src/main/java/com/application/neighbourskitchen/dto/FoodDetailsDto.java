package com.application.neighbourskitchen.dto;

import com.application.neighbourskitchen.model.Food;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodDetailsDto {

    public FoodDetailsDto(Food food) {
        id = food.getId();
        image = food.getImage();
        title = food.getTitle();
        description = food.getDescription();
        timeCooked = food.getTimeCooked();
        realPortions = food.getRealPortions();
        virtualPortions = food.getVirtualPortions();
        isAvailable = food.isAvailable();
        packages = food.getPackages();
        price = food.getPrice();
    }

    private Long id;
    private Byte[] image;
    private String title;
    private String description;
    private Date timeCooked;
    private int realPortions;
    private int virtualPortions;
    private boolean isAvailable;
    private int packages;
    private double price;

}
