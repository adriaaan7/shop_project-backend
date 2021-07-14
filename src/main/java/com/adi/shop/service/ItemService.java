package com.adi.shop.service;

import com.adi.shop.model.item.Item;

import java.util.Set;

public interface ItemService {
    Set<Item> getItems();

    Item findById(Long id);

    void deleteById(Long id);

    Iterable<Item> findAll();

    void save(Item item);
}
