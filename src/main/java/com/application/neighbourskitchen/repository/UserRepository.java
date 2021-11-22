package com.application.neighbourskitchen.repository;

import com.application.neighbourskitchen.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Component
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    Set<User> findByIsCook(boolean isCook);
}
