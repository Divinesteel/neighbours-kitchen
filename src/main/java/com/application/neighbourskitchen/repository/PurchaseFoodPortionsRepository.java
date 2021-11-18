package com.application.neighbourskitchen.repository;

import com.application.neighbourskitchen.model.PurchaseFoodId;
import com.application.neighbourskitchen.model.PurchaseFoodPortions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PurchaseFoodPortionsRepository extends JpaRepository<PurchaseFoodPortions, PurchaseFoodId> {


}
