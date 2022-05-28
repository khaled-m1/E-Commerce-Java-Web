package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    private ArrayList<Category> categories = new ArrayList<>();
    // View all Category
    public ArrayList<Category> getCategory() {
        return categories;
    }
    // Add Category
    public boolean addCategory(Category category) {
        return categories.add(category);
    }
    // Edit Category
    public boolean editCategory(Integer index, Category category ) {
        categories.set(index,category);
        return true;
    }
    // Remove Category
    public boolean removeCategory(String id) {
        Integer currentCategory = getCategory(id);
        categories.remove((int)currentCategory);
        return true;
    }
    // get ID Category
    public Integer getCategory(String id){
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }
}
