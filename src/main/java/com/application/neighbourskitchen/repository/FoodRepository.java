package com.application.neighbourskitchen.repository;

import com.application.neighbourskitchen.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Component
public interface FoodRepository extends JpaRepository<Food, Long> {

    Set<Food> findByIAvailable(boolean availability);

}
