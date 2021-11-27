package com.application.neighbourskitchen.model;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
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

    private boolean isCompleted;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<PurchaseFoodPortions> foodPortions;

    public PurchaseFoodPortions addFood(Food food,int portion) {
        PurchaseFoodPortions purchaseFoodPortions = new PurchaseFoodPortions(this, food,portion);
        if(foodPortions==null){
            foodPortions = new ArrayList<>();
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Double.compare(purchase.price, price) == 0 && isCompleted == purchase.isCompleted && Objects.equals(id, purchase.id) && Objects.equals(seller, purchase.seller) && Objects.equals(buyer, purchase.buyer) && Objects.equals(date, purchase.date) && Objects.equals(foodPortions, purchase.foodPortions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, seller, buyer, date, isCompleted, foodPortions);
    }
}
