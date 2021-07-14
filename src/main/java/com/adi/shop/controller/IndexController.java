package com.adi.shop.controller;

import com.adi.shop.model.item.Item;
import com.adi.shop.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IndexController {

    private ItemService itemService;

    public IndexController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(path = "/api/all")
    public @ResponseBody Iterable<Item> getAllItems(){
        return itemService.getItems();
    }

    @GetMapping(path = "/api")
    public String getIndexPage(Model model){
        log.debug("I'm in the Index Controller ----- getIndexPage()");

        model.addAttribute("items", itemService.getItems());

        return "index";
    }

    @PostMapping(path = "/api")
    @ResponseBody
    public String addItem(@RequestParam(defaultValue = "Name of an item") String name, @RequestParam(defaultValue = "0") double price){

        Item testItem = new Item();
        testItem.setName(name);
        testItem.setPrice(price);
        testItem.setDescription("Loreum pseum descriptioneum");

        itemService.save(testItem);

        return "Added an item ;]";
    }

    @DeleteMapping(path = "/api")
    public Item deleteItem(@RequestParam(required = true) Long id){
        itemService.deleteById(id);
        return itemService.findById(id);
    }


    @GetMapping(path = "/about")
    public String getAboutPage(){
        log.debug("I'm in the Index Controller ----- getAboutPage()");
        return "about";
    }

    @GetMapping(path = "/contact")
    public String getContactPage(){
        log.debug("I'm in the Index Controller ----- getContactPage()");
        return "contact";
    }

}
