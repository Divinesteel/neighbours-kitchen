package com.application.neighbourskitchen.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Builder
@AllArgsConstructor
@Getter
@Setter
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Byte[] image;
    private String title;
    private String description;
    private Date timeCooked;
    private int realPortions;
    private int virtualPortions;
    private boolean isAvailable;
    private int packages;
    private double price;

    @ManyToMany
    @JoinTable(name = "FOOD_CATEGORY")
    private Set<Category> categorySet;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User cook;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseFoodPortions> purchasePortions =new ArrayList<>();;

    public Food() {
    }

    public Food(Byte[] image, String title, String description, Date timeCooked, int portions, boolean isAvailable, int packages, double price) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.timeCooked = timeCooked;
        this.realPortions = portions;
        this.isAvailable = isAvailable;
        this.packages = packages;
        this.price = price;
    }

    public List<PurchaseFoodPortions> getPurchasePortions() {
        return purchasePortions;
    }

    public void setPurchasePortions(List<PurchaseFoodPortions> purchasePortions) {
        this.purchasePortions = purchasePortions;
    }
}
