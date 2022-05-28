package com.example.ecommerce.controller;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/category")//Category
public class CategoryController {
    private final CategoryService categoryService;
    // Constructor Category
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    // View Category
    @GetMapping
    public ResponseEntity<ArrayList<Category>> getCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategory());
    }
    // Add Category
    @PostMapping
    public ResponseEntity<Api> addCategory(@RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        boolean isAcceptedAdd = categoryService.addCategory(category);
        if (!isAcceptedAdd){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Sorry but your request not Accepted :(",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Accepted request :)",200));
    }
    // Edit Category
    @PutMapping("/{index}")
    public ResponseEntity<Api> editCategory(@PathVariable Integer index, @RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        boolean isCategoryEdit = categoryService.editCategory(index,category);
        if (!isCategoryEdit){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Sorry but your request not Edited :(",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Edited request :)",200));
    }
    // Remove Category
    @DeleteMapping("/remove")
    public ResponseEntity<Api> removeCategory(@RequestParam String id){
        boolean isRemovedCategory= categoryService.removeCategory(id);
        if (!isRemovedCategory){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Sorry but your request not Removed :(",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Removed request :)",200));
    }


}
