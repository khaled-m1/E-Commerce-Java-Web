package com.example.ecommerce.model;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.ArrayList;

 @Data
public class Product {
    @NotEmpty(message = "ID must not be empty")
    @Digits(integer = 3,fraction = 0,message = "Your id must be have to be 3 character long")
    private String id;

    @NotEmpty(message = "name must not be empty")
    @Size(min = 3,message = "Your name must be  have to be 3 length long")
    private String name;


    @NotNull(message = "price must not be empty")
    @Positive(message = "price have to be positive")
    private Integer price;


    @NotEmpty(message = "categoryID must not be empty")
    @Size(min = 3,message = "Your categoryID must be  have to be 3 length long")
    private  String categoryID;
    private ArrayList<Comment> commentsList;

    public Product(String id, String name, Integer price, String categoryID) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryID = categoryID;
        this.commentsList = new ArrayList<>();
    }
}
