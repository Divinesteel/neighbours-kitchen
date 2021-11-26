package com.application.neighbourskitchen.dto;

import com.application.neighbourskitchen.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDetailsWrapperDto extends UserDetailsDto {

    public UserDetailsWrapperDto(UserDetailsDto userDto) {
        this.setUsername(userDto.getUsername());
        this.setAddress(userDto.getAddress());
        this.setCook(userDto.isCook());
        this.setFirstName(userDto.getFirstName());
        this.setLastName(userDto.getLastName());
        this.setOpenToPublic(userDto.isOpenToPublic());
        this.setPhone(userDto.getPhone());
        this.setEnabled(userDto.isEnabled());
    }
}
