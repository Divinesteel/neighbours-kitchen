package com.application.neighbourskitchen.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Byte[] image;
    private String title;
    private String description;
    private Date timeCooked;
    private int portions;
    private int packages;
    private double price;

    @ManyToMany
    @JoinTable(name = "FOOD_CATEGORY")
    private Set<Category> categorySet;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    public Food() {
    }

    public Food(Byte[] image, String title, String description, Date timeCooked, int portions, int packages, double price) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.timeCooked = timeCooked;
        this.portions = portions;
        this.packages = packages;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimeCooked() {
        return timeCooked;
    }

    public void setTimeCooked(Date timeCooked) {
        this.timeCooked = timeCooked;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public int getPackages() {
        return packages;
    }

    public void setPackages(int packages) {
        this.packages = packages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Category> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<Category> categorySet) {
        this.categorySet = categorySet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
