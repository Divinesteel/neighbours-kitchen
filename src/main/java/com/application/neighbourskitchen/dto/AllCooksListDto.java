package com.application.neighbourskitchen.dto;

import com.application.neighbourskitchen.model.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class AllCooksListDto {

    Set<UserDetailsDto> cooksSet;

    public AllCooksListDto(Set<User> cookSet){
        cooksSet = new HashSet<>();

        cookSet.forEach(user -> {
            UserDetailsDto userDetailsDto = new UserDetailsDto(user);
            this.cooksSet.add(userDetailsDto);
        });
    }

}
