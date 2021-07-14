package com.adi.shop.service.Impl;

import com.adi.shop.model.item.Item;
import com.adi.shop.repository.ItemRepository;
import com.adi.shop.service.ItemService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Set<Item> getItems() {
        log.debug("I'm in the item service ----- getItems()");
        log.debug("Someone requested to access to the items from the DATABASE!");
        Set<Item> itemSet = new HashSet<>();
        itemRepository.findAll().iterator().forEachRemaining(itemSet::add);
        return itemSet;
    }

    @Override
    public Item findById(Long id) {
        log.debug("I'm in the item service ----- findById()");

        Optional<Item> itemOptional = itemRepository.findById(id);

        if (!itemOptional.isPresent()) {
            try {
                throw new NotFoundException("Item not found for provided ID: " + id.toString());
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        return itemOptional.get();
    }

    @Override
    public void deleteById(Long id) {
        log.debug("I'm in the item service ----- deleteById()");

        itemRepository.deleteById(id);
    }

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Override
    public Iterable<Item> findAll(){
        return itemRepository.findAll();
    }
}
