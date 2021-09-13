package com.adi.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Table(name = "dish")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "menuName")
    private String menuName;

//    @ManyToOne(cascade = CascadeType.REMOVE)
//    @JsonManagedReference
//    private Menu menu = new Menu();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name="dish_category",
               joinColumns = {@JoinColumn(name="dish_id", referencedColumnName = "id")},
               inverseJoinColumns = {@JoinColumn(name="category_id", referencedColumnName = "id")})
    private List<Category> categories = new ArrayList<>();


    public Dish(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Dish(String name, double price, String description, String menuName) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.menuName = menuName;
    }

    public Dish(String name, double price, String description, String menuName, List<Category> categories) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.menuName = menuName;
        this.categories = categories;
    }

    public void addCategoryToDish(Category category){
        this.categories.add(category);
//        category.getDishes().add(this);

    }

    public void removeCategoryFromDish(Category category){
        this.categories.remove(category);
//        category.getDishes().remove(this);
    }
}
