package com.homeheaven.backend.service;

import com.homeheaven.backend.entity.Category;
import com.homeheaven.backend.repository.CategoryRepository;

import java.util.ArrayList;

public class CategoryService {

    public ArrayList<Category> getCategories() {
        CategoryRepository repository = new CategoryRepository();
        return repository.getCategories();
    }

    public Category getCategoryById(int id) {
        CategoryRepository repository = new CategoryRepository();
        return repository.getCategoryById(id);
    }

}
