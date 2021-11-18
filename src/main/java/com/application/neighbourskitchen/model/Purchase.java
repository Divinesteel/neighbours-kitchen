package com.application.neighbourskitchen.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double price;

    @ManyToOne
    private User seller;
    @ManyToOne
    private User buyer;

    private Date date;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseFoodPortions> foodPortions=new ArrayList<>();

    public Purchase() {
    }

    public Purchase(double price, User seller, User buyer, Date date) {
        this.price = price;
        this.seller = seller;
        this.buyer = buyer;
        this.date = date;
    }

    public PurchaseFoodPortions addFood(Food food,int portion) {
        PurchaseFoodPortions purchaseFoodPortions = new PurchaseFoodPortions(this, food,portion);
        foodPortions.add(purchaseFoodPortions);
        food.getPurchasePortions().add(purchaseFoodPortions);
        return purchaseFoodPortions;
    }

    public void removeFood(Food food) {
        for (Iterator<PurchaseFoodPortions> iterator = foodPortions.iterator();
             iterator.hasNext(); ) {
            PurchaseFoodPortions purchaseFoodPortions = iterator.next();

            if (purchaseFoodPortions.getPurchase().equals(this) &&
                    purchaseFoodPortions.getFood().equals(food)) {
                iterator.remove();
                purchaseFoodPortions.getFood().getPurchasePortions().remove(purchaseFoodPortions);
                purchaseFoodPortions.setPurchase(null);
                purchaseFoodPortions.setFood(null);
            }
        }
    }

    public List<PurchaseFoodPortions> getFoodPortions() {
        return foodPortions;
    }

    public void setFoodPortions(List<PurchaseFoodPortions> foodPortions) {
        this.foodPortions = foodPortions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Double.compare(purchase.price, price) == 0 && Objects.equals(id, purchase.id) && Objects.equals(seller, purchase.seller) && Objects.equals(buyer, purchase.buyer) && Objects.equals(date, purchase.date) && Objects.equals(foodPortions, purchase.foodPortions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, seller, buyer, date, foodPortions);
    }
}
