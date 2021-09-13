package com.adi.shop.service.Impl;

import com.adi.shop.exceptions.DishNotFoundException;
import com.adi.shop.model.Dish;
import com.adi.shop.repository.DishRepository;
import com.adi.shop.service.DishService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class DishServiceImpl implements DishService {

    private DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Dish> getAllDishes() {
        log.debug("I'm in the DishServiceImpl ----- getAllDishes()");
        log.debug("Someone requested to access to the items from the DATABASE!");
        List<Dish> allDishes = new ArrayList<>();
        dishRepository.findAll().iterator().forEachRemaining(allDishes::add);
        return allDishes;
    }

    @Override
    @SneakyThrows
    public List<Dish> findDishByMenuName(String menuName) {
        
        if(dishRepository.findDishByMenuName(menuName).isEmpty()) throw new DishNotFoundException("Dish with menu name: " + menuName + " was not found");

        else return dishRepository.findDishByMenuName(menuName);
    }

    @Override
    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }

    @SneakyThrows
    @Override
    public Dish removeDishById(Long id) {
        if(dishRepository.existsById(id)) {
            Dish dish = dishRepository.findById(id).get();
            dishRepository.delete(dish);
            return dish;
        }else{
            throw new DishNotFoundException("Dish with id:" + id + " does not exist.");
        }
    }

    @Override
    public void updateDish(Long id, String name, double price, String description, String menu_name) {
        dishRepository.updateDish(id, name, price, description, menu_name);
    }
}
