package com.adi.shop.repository;

import com.adi.shop.model.Dish;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {

    @Query(value = "SELECT * FROM dish WHERE dish.menu_name=:menuName", nativeQuery = true)
    List<Dish> findDishByMenuName(@Param("menuName")String menuName);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dish d SET d.name = ?2, d.price = ?3, d.description = ?4, d.menu_name = ?5 WHERE d.id = ?1", nativeQuery = true)
    void updateDish(Long id, String name, double price, String description, String menu_name);
}
