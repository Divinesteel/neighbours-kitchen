package com.application.neighbourskitchen.bootstrap;

import com.application.neighbourskitchen.model.*;
import com.application.neighbourskitchen.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;

@Component
public class Bootstrap implements CommandLineRunner {

    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final PurchaseRepository purchaseRepository;
    private final CategoryRepository categoryRepository;
    private final PurchaseFoodPortionsRepository purchaseFoodPortionsRepository;
    private final PasswordEncoder passwordEncoder;

    User user1;
    User user2;
    User user3;
    Food food1;
    Food food2;
    Category c;
    Category c1;
    Category c2;
    Category c3;

    public Bootstrap(UserRepository userRepository, FoodRepository foodRepository, PurchaseRepository purchaseRepository, CategoryRepository categoryRepository, PurchaseFoodPortionsRepository purchaseFoodPortionsRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.foodRepository = foodRepository;
        this.purchaseRepository = purchaseRepository;
        this.categoryRepository = categoryRepository;
        this.purchaseFoodPortionsRepository = purchaseFoodPortionsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void prepareProperties(){
        user1 = User.builder().username("Divinesteel").password(passwordEncoder.encode("jaka2957"))
                .firstName("Stelios").lastName("Andreolas")
                .address("Kapodistrioy 7 Egaleo").isCook(true).phone(6955542869l).openToPublic(true).enabled(true)
                .score(4.5).build();

        user2 = User.builder().username("Lauren").firstName("Sofia").lastName("Kagkelari")
                .password(passwordEncoder.encode("123"))
                .address("Kapodistrioy 7 Egaleo").isCook(false).phone(6954787845l).openToPublic(false).enabled(true)
                .score(0).build();

        user3 = User.builder().username("GeorgeXen").password(passwordEncoder.encode("pswd")).firstName("Giorgos").lastName("Xenidis")
                .address("Neo Faliro").isCook(true).phone(6976530703l).openToPublic(true).enabled(true)
                .score(4.5).build();

        food1 = new Food(null, "Gemista", "Ntomates kai  piperies", new Date("17/11/2021"), 3, true, 1, 1);
        food2 = new Food(null, "Pastitsio", "skata bghke", new Date("17/11/2021"), 5, true, 0, 1.5 );

        c = Category.builder().description("Vegan").build();
        c1 = Category.builder().description("Ladera").build();
        c2 = Category.builder().description("Vegeterian").build();
        c3 = Category.builder().description("Gluten Free").build();
    }

    @Override
    public void run(String... args) throws Exception {

        prepareProperties();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);


        c.setFoodSet(new HashSet<Food>() {{
            add(food1);
        }});
        c1.setFoodSet(new HashSet<Food>() {{
            add(food1);
        }});
        c2.setFoodSet(new HashSet<Food>() {{
            add(food1);
        }});
        c3.setFoodSet(new HashSet<Food>() {{
            add(food1);
        }});
        categoryRepository.save(c);
        categoryRepository.save(c1);
        categoryRepository.save(c2);
        categoryRepository.save(c3);

        food2.setCook(user1);
//
        food1.setCook(user1);
        food1.setCategorySet(new HashSet<Category>() {
            {
                add(c);
                add(c1);
                add(c2);
                add(c3);
            }
        });
        Food savedFood1 = foodRepository.save(food1);
        Food retrievedFood = foodRepository.findById(savedFood1.getId()).get();
//        foodRepository.save(food2);

        Purchase purchase = Purchase.builder().buyer(user2).seller(user1).price(0).date(new Date()).isCompleted(false).build();
        Purchase savedPurchase = purchaseRepository.save(purchase);
        Purchase retrievedPurchase = purchaseRepository.findById(savedPurchase.getId()).get();

        retrievedFood.setRealPortions(retrievedFood.getRealPortions()-2);
        Food savedFood11 = foodRepository.save(retrievedFood);
        retrievedPurchase.setPrice(retrievedPurchase.getPrice()+(savedFood11.getPrice()*2));

        PurchaseFoodPortions a = retrievedPurchase.addFood(savedFood11, 2);
//        PurchaseFoodPortions b = purchase.addFood(food2, 1);
        purchaseFoodPortionsRepository.save(a);
//        purchaseFoodPortionsRepository.save(b);
        System.out.println(userRepository.findById("Divinesteel").get().getPurchasesAsSeller().stream().findFirst().get().getSeller().getFirstName());

        System.out.println(userRepository.findById("Divinesteel").get().getFoodList().stream().findFirst().get().getTitle());
    }
}
