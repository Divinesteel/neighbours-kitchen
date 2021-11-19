package com.application.neighbourskitchen.controller;

import com.application.neighbourskitchen.dto.FoodListDto;
import com.application.neighbourskitchen.exception.FoodListNotFoundException;
import com.application.neighbourskitchen.exception.UserNotFoundException;
import com.application.neighbourskitchen.model.Food;
import com.application.neighbourskitchen.model.User;
import com.application.neighbourskitchen.repository.FoodRepository;
import com.application.neighbourskitchen.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/food")
public class FoodController {

    UserRepository userRepository;
    FoodRepository foodRepository;
    ModelMapper modelMapper;

    public FoodController(UserRepository userRepository, FoodRepository foodRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.foodRepository = foodRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/getAllFoodsByUser/{username}")
    @ResponseBody
    public FoodListDto getFoodDto(@PathVariable String username) {
        User user = userRepository.findByUsername(username).get();

        try{
            FoodListDto foodListDto = new FoodListDto(user.getFoodList());

            return foodListDto;
        }catch (RuntimeException exception){
            throw new UserNotFoundException(username);
        }
    }

}
