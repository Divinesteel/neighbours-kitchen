package com.application.neighbourskitchen.bootstrap;

import com.application.neighbourskitchen.model.Category;
import com.application.neighbourskitchen.model.Food;
import com.application.neighbourskitchen.model.Purchase;
import com.application.neighbourskitchen.model.User;
import com.application.neighbourskitchen.repository.CategoryRepository;
import com.application.neighbourskitchen.repository.FoodRepository;
import com.application.neighbourskitchen.repository.PurchaseRepository;
import com.application.neighbourskitchen.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;

@Component
public class Bootstrap implements CommandLineRunner {

    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final PurchaseRepository purchaseRepository;
    private final CategoryRepository categoryRepository;

    public Bootstrap(UserRepository userRepository, FoodRepository foodRepository, PurchaseRepository purchaseRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.foodRepository = foodRepository;
        this.purchaseRepository = purchaseRepository;
        this.categoryRepository = categoryRepository;
    }

    User user1 = new User("Stelios","Andreolas","Kapodistrioy 7 Egaleo",true,6955542869L,4.5);
    User user2 = new User("Sofia","Kagkelari","Kapodistrioy 7 Egaleo",false,6943026928L,0);

    Food food1 = new Food(null,"Gemista","Ntomates kai  piperies",new Date("17/11/2021"),3,1,1);

    Category c = new Category("Vegan");
    Category c1 = new Category("Ladera");
    Category c2 = new Category("Vegeterian");
    Category c3 = new Category("Gluten Free");

    @Override
    public void run(String... args) throws Exception {

        System.out.println("ASDASDASDASDASD");

        userRepository.save(user1);
        userRepository.save(user2);

        c.setFoodSet(new HashSet<Food>(){{
            add(food1);
        }});
        c1.setFoodSet(new HashSet<Food>(){{
            add(food1);
        }});
        c2.setFoodSet(new HashSet<Food>(){{
            add(food1);
        }});
        c3.setFoodSet(new HashSet<Food>(){{
            add(food1);
        }});
        categoryRepository.save(c);
        categoryRepository.save(c1);
        categoryRepository.save(c2);
        categoryRepository.save(c3);

        food1.setUser(user1);
        food1.setCategorySet(new HashSet<Category>(){
            {
                add(c);
                add(c1);
                add(c2);
                add(c3);
            }
        });
        foodRepository.save(food1);

        Purchase purchase = new Purchase(2,user1,user2, new Date());
        purchaseRepository.save(purchase);

    }
}
