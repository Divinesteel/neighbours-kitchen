package com.application.neighbourskitchen.repository;

import com.application.neighbourskitchen.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CategoryRepository extends JpaRepository<Category, Long> {


}
