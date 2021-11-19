package com.application.neighbourskitchen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserCredentialsDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private boolean isCook;
    private long phone;
    private boolean enabled;

}