package com.application.neighbourskitchen.dto;

import com.application.neighbourskitchen.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@NoArgsConstructor
@Getter
@Setter
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
