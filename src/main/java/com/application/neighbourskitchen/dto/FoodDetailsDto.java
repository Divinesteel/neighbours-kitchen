package com.application.neighbourskitchen.dto;

import com.application.neighbourskitchen.model.Category;
import com.application.neighbourskitchen.model.Food;
import com.application.neighbourskitchen.model.PurchaseFoodPortions;
import com.application.neighbourskitchen.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class FoodDetailsDto {

    public FoodDetailsDto(Food food) {
        id = food.getId();
        image = food.getImage();
        title = food.getTitle();
        description = food.getDescription();
        timeCooked = food.getTimeCooked();
        portions = food.getPortions();
        packages = food.getPackages();
        price = food.getPrice();
    }

    private Long id;
    private Byte[] image;
    private String title;
    private String description;
    private Date timeCooked;
    private int portions;
    private int packages;
    private double price;

}
