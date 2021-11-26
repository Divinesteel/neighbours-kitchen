package com.application.neighbourskitchen.controller;

import com.application.neighbourskitchen.dto.FoodDetailsDto;
import com.application.neighbourskitchen.dto.FoodSetDto;
import com.application.neighbourskitchen.dto.FoodWithUserDto;
import com.application.neighbourskitchen.exception.UserNotFoundException;
import com.application.neighbourskitchen.helper.UserActions;
import com.application.neighbourskitchen.model.Food;
import com.application.neighbourskitchen.model.User;
import com.application.neighbourskitchen.repository.FoodRepository;
import com.application.neighbourskitchen.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public FoodSetDto getFoodDto(@PathVariable String username,Authentication authentication) {
        User user = userRepository.findByUsername(username).get();

        try{
            FoodSetDto foodListDto = new FoodSetDto(user.getFoodList());

            return foodListDto;
        }catch (RuntimeException exception){
            throw new UserNotFoundException(username);
        }
    }

    @GetMapping("/getAvailableFoods")
    @ResponseBody
    public FoodSetDto getAvailableFoods(){
        FoodSetDto allAvailableFoodsDto = new FoodSetDto(foodRepository.findByIsAvailable(true));

        return allAvailableFoodsDto;
    }

    @PostMapping("/addFood")
    @ResponseBody
    public FoodDetailsDto getAvailableFoods(@RequestBody FoodWithUserDto foodWithUserDto, Authentication auth){

        UserActions<FoodWithUserDto,FoodDetailsDto> userActions = new UserActions() {
            @Override
            public FoodDetailsDto actionOnVerified(User user) {
                Food food = Food.builder().image(foodWithUserDto.getFood().getImage())
                        .description(foodWithUserDto.getFood().getDescription())
                        .realPortions(foodWithUserDto.getFood().getRealPortions())
                        .virtualPortions(foodWithUserDto.getFood().getVirtualPortions())
                        .packages(foodWithUserDto.getFood().getPackages())
                        .price(foodWithUserDto.getFood().getPrice())
                        .timeCooked(foodWithUserDto.getFood().getTimeCooked())
                        .title(foodWithUserDto.getFood().getTitle())
                        .isAvailable(true)
                        .cook(user)
                        .build();

                Food savedFood = foodRepository.save(food);
                return modelMapper.map(savedFood,FoodDetailsDto.class);
            }
        };
        return userActions.verifyOwnAction(userRepository,modelMapper,auth,foodWithUserDto,false);
    }

}
