package com.application.neighbourskitchen.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private boolean isCook;
    private long phone;
    private double score;
    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "seller",fetch = FetchType.EAGER)
    private Set<Purchase> purchasesAsSeller;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "buyer",fetch = FetchType.EAGER)
    private Set<Purchase> purchasesAsBuyer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user",fetch = FetchType.EAGER)
    private Set<Food> foodList;

    public User(){}

    public void addFood(Food food){
        if (foodList==null){
            foodList = new HashSet<>();
        }
        foodList.add(food);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isCook() {
        return isCook;
    }

    public void setCook(boolean cook) {
        isCook = cook;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Set<Purchase> getPurchasesAsSeller() {
        return purchasesAsSeller;
    }

    public void setPurchasesAsSeller(Set<Purchase> purchasesAsSeller) {
        this.purchasesAsSeller = purchasesAsSeller;
    }

    public Set<Purchase> getPurchasesAsBuyer() {
        return purchasesAsBuyer;
    }

    public void setPurchasesAsBuyer(Set<Purchase> purchasesAsBuyer) {
        this.purchasesAsBuyer = purchasesAsBuyer;
    }

    public Set<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(Set<Food> foodList) {
        this.foodList = foodList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
