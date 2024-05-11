package com.homeheaven.backend.repository;

import com.homeheaven.backend.entity.Category;

import java.util.ArrayList;
import java.util.Arrays;

public class CategoryRepository {

    private ArrayList<Category> categories = new ArrayList<Category>(
            Arrays.asList(Category.builder().name("Cocina").id(2).build())
    );


    public ArrayList<Category> getCategories() {
        return categories;
    }

    public Category getCategoryById(Integer id) {
        for (Category category : categories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null; // Category not found with the given ID
    }
}
