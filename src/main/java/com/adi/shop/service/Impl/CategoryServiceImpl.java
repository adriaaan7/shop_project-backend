package com.adi.shop.service.Impl;

import com.adi.shop.model.Category;
import com.adi.shop.repository.CategoryRepository;
import com.adi.shop.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        if(categoryRepository.findById(category.getId()).isPresent()) {
           return category;
        }
        else return categoryRepository.save(category);
    }
}
