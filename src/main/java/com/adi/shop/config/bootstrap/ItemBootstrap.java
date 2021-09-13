package com.adi.shop.config.bootstrap;

import com.adi.shop.model.Category;
import com.adi.shop.model.Dish;
//import com.adi.shop.model.Menu;
import com.adi.shop.model.User;
//
//import com.adi.shop.repository.MenuRepository;
import com.adi.shop.repository.CategoryRepository;
import com.adi.shop.repository.DishRepository;
import com.adi.shop.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ItemBootstrap {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final DishRepository dishRepository;
//    private final MenuRepository menuRepository;

    public ItemBootstrap(UserRepository userRepository, CategoryRepository categoryRepository, DishRepository dishRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.dishRepository = dishRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // method to add users on the start of the application -> testing purposes
    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        User user = new User("user", passwordEncoder().encode("user"), "USER");
        userRepository.save(user);

        User mod = new User("mod", passwordEncoder().encode("mod"), "MODERATOR");
        userRepository.save(mod);

        User admin = new User("admin", passwordEncoder().encode("admin"), "ADMIN");
        userRepository.save(admin);

//        Menu rynek = new Menu("Rynek");
//        Menu halo = new Menu("Halo");
//        menuRepository.save(rynek);
//        menuRepository.save(halo);

        Dish pepperoniPizza = new Dish("Pepperoni", 27, "Pizza pepperoni with some additional cheese", "Rynek");
        Dish hawaiiPizza = new Dish("Hawaii", 25, "Pizza hawaii with pineapple and ham", "Rynek");

        dishRepository.save(pepperoniPizza);
        dishRepository.save(hawaiiPizza);
//        rynek.addDishToMenu(pepperoniPizza);
//        rynek.addDishToMenu(hawaiiPizza);

        Dish margheritaPizza = new Dish("Margherita", 20, "Margherita pizza with only cheese", "Halo");
        Dish kebabDish = new Dish("Kebab", 16, "Kebab with chicken", "Halo");

        dishRepository.save(margheritaPizza);
        dishRepository.save(kebabDish);
//
//        rynek.addDishToMenu(pepperoniPizza);
//        rynek.addDishToMenu(hawaiiPizza);
//
//        halo.addDishToMenu(margheritaPizza);
//        halo.addDishToMenu(kebabDish);
//
//        menuRepository.save(rynek);
//        menuRepository.save(halo);
    }
}
