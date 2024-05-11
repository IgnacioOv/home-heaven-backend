package com.homeheaven.backend.controller;


import com.homeheaven.backend.entity.Category;
import com.homeheaven.backend.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("categories")
public class CategoriesController {

    @GetMapping
    public ArrayList<Category> getCategories()
    {
        CategoryService categoryService = new CategoryService();
        return categoryService.getCategories();
    }

    @GetMapping("/{categoryId}")
    public Category getCategoryById(@PathVariable int categoryId) {
        CategoryService categoryService = new CategoryService();
        return categoryService.getCategoryById(categoryId);
    }



}
