package com.application.neighbourskitchen.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private boolean isCook;
    private long phone;
    private double score;
    private boolean enabled;
    private boolean openToPublic;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "seller",fetch = FetchType.EAGER)
    private Set<Purchase> purchasesAsSeller;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "buyer",fetch = FetchType.EAGER)
    private Set<Purchase> purchasesAsBuyer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cook",fetch = FetchType.EAGER)
    private Set<Food> foodList;

    public User(){}

    public void addFood(Food food){
        if (foodList==null){
            foodList = new HashSet<>();
        }
        foodList.add(food);
    }
}
