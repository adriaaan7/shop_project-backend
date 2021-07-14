package com.adi.shop.model.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;

    @Column(name = "description")
    @Lob
    private String description;

    public Item(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

// dodać potem zdjecie przedmiotu

}
