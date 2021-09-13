package com.adi.shop.service;

import com.adi.shop.model.Dish;
import org.hibernate.SessionFactory;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DishService {

    List<Dish> getAllDishes();

    List<Dish> findDishByMenuName(String menuName);

    Dish save(Dish dish);

    Dish removeDishById(Long id);

    void updateDish(Long id, String name, double price, String description, String menu_name);

}
