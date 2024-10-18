package com.ankur.bms.productservice.services;


import com.ankur.bms.productservice.models.*;
import com.ankur.bms.productservice.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CategoryService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();

    }

    public Category getCategoryById(Long id) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isEmpty()) throw new RuntimeException();

        return optionalCategory.get();

    }

    public Category saveCategory(Category category) {

        return  categoryRepository.save(category);

    }

    public Category updateCategory(Long id, Category category) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if(optionalCategory.isEmpty()) throw new RuntimeException();

        Category newCategory = optionalCategory.get();

        if(category.getName()!=null){
            newCategory.setName(category.getName());
        }

        return categoryRepository.save(newCategory);


    }

    public void deleteCategory(Category category) {


        categoryRepository.delete(category);

    }

    public boolean deleteCategoryById(Long id) {

       Optional<Category> optionalCategory = categoryRepository.findById(id);

       if(optionalCategory.isEmpty()) return false;

       categoryRepository.deleteById(id);

       return true;


    }

}
