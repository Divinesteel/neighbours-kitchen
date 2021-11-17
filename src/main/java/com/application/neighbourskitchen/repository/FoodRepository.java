package com.application.neighbourskitchen.repository;

import com.application.neighbourskitchen.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {


}
