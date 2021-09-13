package com.adi.shop.controller;


import com.adi.shop.exceptions.MenuNameNotExistingException;
import com.adi.shop.model.Dish;
import com.adi.shop.service.DishService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@Controller
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/menu/all")
    public @ResponseBody Iterable<Dish> getDishes(){
        return dishService.getAllDishes();
    }

    @SneakyThrows
    @GetMapping("/menu/{restaurantName}")
    @ResponseBody
    public List<Dish> getDishesByMenuName(@PathVariable(name = "restaurantName") String restaurantName) {
        
        System.out.println("I'm in Dish Controller ----- getDishesByMenuName ()");

        if(restaurantName.equalsIgnoreCase("rynek") || restaurantName.equalsIgnoreCase("halo")) return dishService.findDishByMenuName(restaurantName) ;
        else throw new MenuNameNotExistingException("Provided menu name is not existing");
    }

    @PostMapping("/menu/add")
    @ResponseBody
    public Dish addDish(@RequestParam(value = "name", defaultValue = "name", required = false) String name,
                        @RequestParam(value = "price", defaultValue = "0", required = false) double price,
                        @RequestParam(value = "description", defaultValue = "description", required = false) String description,
                        @RequestParam(value = "menuName", defaultValue = "menu name", required = false) String menuName){



        Dish dishToBeAdded = new Dish();
        dishToBeAdded.setName(name);
        dishToBeAdded.setPrice(price);
        dishToBeAdded.setDescription(description);
        dishToBeAdded.setMenuName(menuName);
        return dishService.save(dishToBeAdded);
    }

    @DeleteMapping("/menu/{id}")
    @ResponseBody
    public Dish removeDish(@PathVariable(name = "id") Long id){
        return dishService.removeDishById(id);
    }

    @PutMapping("/menu/{id}")
    @ResponseBody
    public void updateDish(@PathVariable(name = "id", required = true) Long id,
                           @RequestParam(value = "name", defaultValue = "name", required = false) String name,
                           @RequestParam(value = "price", defaultValue = "0", required = false) double price,
                           @RequestParam(value = "description", defaultValue = "description", required = false) String description,
                           @RequestParam(value = "menuName", defaultValue = "menu name", required = false) String menuName){
     dishService.updateDish(id, name, price, description, menuName);
        System.out.println("TEST");
        System.out.println(name + " " + price + " " + description + " " + menuName);
    }
}
