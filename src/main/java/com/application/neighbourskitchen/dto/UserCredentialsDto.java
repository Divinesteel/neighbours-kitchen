package com.application.neighbourskitchen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserCredentialsDto extends UserDetailsDto{

    private String username;
    private String password;
    private boolean enabled;
    private boolean openToPublic;
}
