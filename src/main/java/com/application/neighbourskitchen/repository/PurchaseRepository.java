package com.application.neighbourskitchen.repository;

import com.application.neighbourskitchen.model.Purchase;
import com.application.neighbourskitchen.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Component
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    Set<Purchase> findByBuyer(User buyer/*!Finds by Buyer's Username!*/);

}
