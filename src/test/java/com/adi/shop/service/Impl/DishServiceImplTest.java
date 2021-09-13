package com.adi.shop.service.Impl;

import com.adi.shop.model.Dish;
import com.adi.shop.repository.DishRepository;
import com.adi.shop.service.DishService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class DishServiceImplTest {

    @Mock
    DishRepository dishRepository;

    @InjectMocks
    DishServiceImpl dishService;

    @Test
    void getAllDishes() {
        // given
        Set<Dish> set = new HashSet<>();

        Dish dish = new Dish();
        dish.setName("kebab");

        set.add(dish);

        System.out.println(set.size());

        // when
        when(dishRepository.findAll()).thenReturn(set);

        // then
        assertEquals(1, set.size());
    }

    @Test
    void findDishByMenuId() {
    }

}