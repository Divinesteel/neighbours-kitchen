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

    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private boolean isCook;
    private long phone;
    private boolean enabled;
    private boolean openToPublic;

    public UserDetailsDto(User user) {
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.isCook = user.isCook();
        this.phone = user.getPhone();
        this.enabled = user.isEnabled();
        this.openToPublic = user.isOpenToPublic();
    }
}
