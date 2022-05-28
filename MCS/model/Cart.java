package com.example.ecommerce.model;
import lombok.Data;
import javax.validation.constraints.*;
import java.util.ArrayList;
 @Data
public class Cart {
    @NotEmpty(message = "ID must not be empty")
    @Digits(integer = 3,fraction = 0,message = "Your id must be have to be 3 character long")
    private String id;
    @NotEmpty(message = "userid must not be empty")
    @Digits(integer = 3,fraction = 0,message = "Your userid must be have to be 3 character long")
    private String userid;
    private ArrayList<Product> productList;

     public Cart(String id, String userid) {
         this.id = id;
         this.userid = userid;
         this.productList = new ArrayList<>();
     }
 }
