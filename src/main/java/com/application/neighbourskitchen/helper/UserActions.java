package com.application.neighbourskitchen.helper;

import com.application.neighbourskitchen.dto.UserDetailsDto;
import com.application.neighbourskitchen.exception.UnauthorizedActionException;
import com.application.neighbourskitchen.exception.UserIsDisabledException;
import com.application.neighbourskitchen.exception.UserNotFoundException;
import com.application.neighbourskitchen.model.User;
import com.application.neighbourskitchen.model.UserAuth;
import com.application.neighbourskitchen.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public abstract class UserActions<I extends UserDetailsDto, O> {

    public O verifyOwnAction(UserRepository userRepository, ModelMapper modelMapper, Authentication auth, I t, boolean skipEnabled) {
        Optional<User> userOpt = userRepository.findById(t.getUsername());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.isEnabled() || skipEnabled) {
                UserAuth userPrincipal = (UserAuth) auth.getPrincipal();
                if (userPrincipal.getUsername().equals(t.getUsername())) {
                    return actionOnVerified(userOpt.get());
                } else {
                    return actionOnUnverified(userOpt.get());
                }
            } else {
                throw new UserIsDisabledException(user.getUsername());
            }

        } else {
            throw new UserNotFoundException(t.getUsername());
        }
    }

    public abstract O actionOnVerified(User user);

    public O actionOnUnverified(User user) {
        throw new UnauthorizedActionException(user.getUsername());
    }
}
