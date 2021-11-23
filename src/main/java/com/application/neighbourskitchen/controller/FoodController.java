package com.application.neighbourskitchen.controller;

import com.application.neighbourskitchen.dto.FoodListDto;
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

    @GetMapping("/getAvailableFoods")
    @ResponseBody
    public FoodListDto getAvailableFoods(){
        FoodListDto allAvailableFoodsDto = new FoodListDto(foodRepository.isAvailable(true));

        return allAvailableFoodsDto;
    }

}
