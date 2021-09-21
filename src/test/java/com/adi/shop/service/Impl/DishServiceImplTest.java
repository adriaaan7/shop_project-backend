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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class DishServiceImplTest {

    @Mock
    DishRepository dishRepository;

    @InjectMocks
    DishServiceImpl dishService;

    @Test
    void getAllDishes() {
        // given
        Dish dish = new Dish();
        dish.setName("kebab");

        List<Dish> list = new ArrayList<>();
        list.add(dish);

        List<Dish> newList = new ArrayList<>();

        // when
        when(dishRepository.findAll()).thenReturn(list);
        Iterable<Dish> all = dishRepository.findAll();
        all.forEach(newList::add);


        // then
        assertEquals(1, newList.size());
    }


    @Test
    void findDishByMenuName() {
        // given
        String name = "pepperoni";
        String menuName = "byepizza";

        Dish dish = new Dish();
        dish.setName(name);
        dish.setMenuName(menuName);

        List<Dish> list = new ArrayList<>();
        list.add(dish);

        // when
        when(dishRepository.findDishByMenuName(menuName)).thenReturn(list);
        Dish returnedDish = dishRepository.findDishByMenuName(menuName).get(0);

        // then
        assertEquals(name, returnedDish.getName());
    }

    @Test
    void save() {
        // given
        Dish dish = new Dish();
        // when
        when(dishRepository.save(any(Dish.class))).thenReturn(dish);
        Dish created = dishService.save(dish);
        // then
        verify(dishRepository, times(1)).save(any());
        assertThat(dish.getName()).isEqualTo(created.getName());

    }

    @Test
    void removeDishById() {
        // given
        Dish dish = new Dish();
        Long id = 1L;
        dish.setId(id);
        dish.setName("test name");
        dishRepository.save(dish);

        // when

        dishRepository.save(dish);
        when(dishService.removeDishById(id)).thenReturn(dish);
        dishService.removeDishById(id);
        int size = dishService.getAllDishes().size();
        // then
        assertEquals(0, size);
    }

    @Test
    void updateDish() {
        // given
        Dish dish = new Dish();
        dish.setId(1L);
        dish.setName("pepperoni");
        dish.setMenuName("rynek");
        dish.setPrice(15);
        dish.setDescription("desc");

        dishService.save(dish);
        // when
        // then
    }
}