package com.application.neighbourskitchen.controller;

import com.application.neighbourskitchen.dto.FoodSetDto;
import com.application.neighbourskitchen.exception.UserNotFoundException;
import com.application.neighbourskitchen.model.User;
import com.application.neighbourskitchen.repository.FoodRepository;
import com.application.neighbourskitchen.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

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
    public FoodSetDto getFoodDto(@PathVariable String username) {
        User user = userRepository.findByUsername(username).get();

        try{
            FoodSetDto foodSetDto = new FoodSetDto(user.getFoodList().stream().filter(food->food.isAvailable()).collect(Collectors.toSet()));

            return foodSetDto;
        }catch (RuntimeException exception){
            throw new UserNotFoundException(username);
        }
    }

    @GetMapping("/getAvailableFoods")
    @ResponseBody
    public FoodSetDto getAvailableFoods(){
        FoodSetDto foodSetDto = new FoodSetDto(foodRepository.isAvailable(true));

        return foodSetDto;
    }

}
