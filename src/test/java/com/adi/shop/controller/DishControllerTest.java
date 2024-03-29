package com.adi.shop.controller;

import com.adi.shop.config.SecurityConfig;
import com.adi.shop.model.Dish;
import com.adi.shop.service.DishService;
import com.adi.shop.service.Impl.DishServiceImpl;
import com.adi.shop.service.Impl.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DishController.class)
public class DishControllerTest {

    @MockBean
    private DishServiceImpl dishService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    // addDish() test
    @Test
    public void addDish() throws Exception{

        // given
        String name = "Pizza";
        Dish dish = new Dish();
        dish.setName(name);

        // when
        when(dishService.save(ArgumentMatchers.any(Dish.class)))
                .thenReturn(dish);

        // then
        mvc.perform(post("/menu/add")
                .param("name", name)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(dish.getName()));


    }


    // getDishes() test
    @Test
    public void getDishes() throws Exception {
        // given
        List<Dish> allDishes = dishService.getAllDishes();
        Dish dish = new Dish();
        dish.setName("first");
        allDishes.add(dish);

        // when
        when(dishService.getAllDishes()).thenReturn(allDishes);

        // then
        mvc.perform(get("/menu/all")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(allDishes)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value(dish.getName()));
    }

    // getDishesByMenuName() test
    @Test
    public void getDishesByMenuName() throws Exception {
        // given
        String menuName = "rynek";
        List<Dish> list = new ArrayList<>();

        Dish dish = new Dish();
        dish.setName("hawaii");
        list.add(dish);

        // when
        when(dishService.findDishByMenuName(menuName)).thenReturn(list);

        // then
        mvc.perform(get("/menu/{restaurantName}", menuName)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[0].name").value(dish.getName()));
    }

    @Test
    public void removeDish() throws Exception {
        // given
        Long id = 1L;
        Dish dish = new Dish();
        dish.setName("pepperoni");

        // when
        when(dishService.removeDishById(id)).thenReturn(dish);

        // then
        mvc.perform(delete("/menu/{id}", id)
        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.name").value(dish.getName()));
    }

    @Test
    public void updateDish() {
        // given
        Long id = 1L;
        String name = "hawaii";
        double price = 25;
        String description = "hawaii pizza";
        String menuName = "halo";

        Dish dish = new Dish();

        // when

        // then
    }
}
