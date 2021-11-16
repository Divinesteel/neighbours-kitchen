package com.application.neighbourskitchen.repository;

import com.application.neighbourskitchen.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {


}
