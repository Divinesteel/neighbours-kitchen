package com.application.neighbourskitchen.repository;

import com.application.neighbourskitchen.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {


}
