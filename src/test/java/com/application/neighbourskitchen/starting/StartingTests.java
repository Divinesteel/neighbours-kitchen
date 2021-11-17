package com.application.neighbourskitchen.starting;

import com.application.neighbourskitchen.model.Food;
import com.application.neighbourskitchen.model.User;
import com.application.neighbourskitchen.repository.CategoryRepository;
import com.application.neighbourskitchen.repository.FoodRepository;
import com.application.neighbourskitchen.repository.PurchaseRepository;
import com.application.neighbourskitchen.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class StartingTests {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final FoodRepository foodRepository;
    private final PurchaseRepository purchaseRepository;

    public StartingTests(@Qualifier("userRepository") UserRepository userRepository,
                         @Qualifier("categoryRepository") CategoryRepository categoryRepository,
                         @Qualifier("foodRepository") FoodRepository foodRepository,
                         @Qualifier("purchaseRepository") PurchaseRepository purchaseRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.foodRepository = foodRepository;
        this.purchaseRepository = purchaseRepository;
    }

    User user1;
    User user2;
    Food food1;

    @BeforeEach
    public void prepare() {
        user1 = User.builder().firstName("Stelios").lastName("Andreolas")
                .address("Kapodistrioy 7 Egaleo").isCook(true).phone(6955542869l)
                .score(4.5).build();

        user2 = User.builder().firstName("Sofia").lastName("Kagkelari")
                .address("Kapodistrioy 7 Egaleo").isCook(true).phone(6954787845l)
                .score(4.5).build();

        food1 = Food.builder().title("Gemista").description("Ntomates kai piperies")
                .timeCooked(new Date("17/11/2021")).portions(3).packages(1).price(1).user(user1).build();
    }

    @Test
    public void findUserById() {
        userRepository.save(user1);
        assertEquals(userRepository.findById(1l).get().getFirstName(), "Stelios");
    }

    @Test
    public void findFoodById() {
        userRepository.save(user1);
        foodRepository.save(food1);
        assertEquals(foodRepository.findById(1l).get().getTitle(), "Gemista");
    }

    @Test
    public void findUserFromFood() {
        userRepository.save(user1);
        foodRepository.save(food1);
        assertEquals(foodRepository.findById(1l).get().getUser().getFirstName(), "Stelios");
    }

    @Test
    public void findFoodFromUser() {
        userRepository.save(user1);
        foodRepository.save(food1);
        assertEquals(userRepository.findById(1l).get().getFoodList().stream().findFirst().get().getTitle(), "Gemista");
    }
}
