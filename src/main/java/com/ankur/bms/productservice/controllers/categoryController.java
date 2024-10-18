package com.ankur.bms.productservice.controllers;


import com.ankur.bms.productservice.models.*;
import com.ankur.bms.productservice.services.*;
import jakarta.websocket.server.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/Category")
public class categoryController {


    CategoryService categoryService;

    public categoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/{id}")
    public Category getCategory(@PathVariable("id") Long id){

       return categoryService.getCategoryById(id);

    }

    @PostMapping
    public Category saveCategory(@RequestBody Category category){

        return categoryService.saveCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PatchMapping("/{id}")
    public Category updateCategory(@PathVariable("id") Long id,@RequestBody Category category){

       return categoryService.updateCategory(id,category);
    }


    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable("id") Long id){

        return categoryService.deleteCategoryById(id);
    }


}

