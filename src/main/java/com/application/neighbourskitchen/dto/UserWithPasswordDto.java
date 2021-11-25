package com.application.neighbourskitchen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserWithPasswordDto extends UserDetailsDto{

    private String password;
}
