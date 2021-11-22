package com.application.neighbourskitchen.dto;

import com.application.neighbourskitchen.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserDetailsDto {

    private String firstName;
    private String lastName;
    private String address;
    private boolean isCook;
    private long phone;

    public UserDetailsDto(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.isCook = user.isCook();
        this.phone = user.getPhone();
    }
}
