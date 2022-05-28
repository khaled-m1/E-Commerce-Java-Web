package com.example.ecommerce.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.*;
@AllArgsConstructor @Data
public class Category {
    @NotEmpty(message = "ID must not be empty")
    @Digits(integer = 3,fraction = 0,message = "Your id must be have to be 3 character long")
    private String id;

    @NotEmpty(message = "name must not be empty")
    @Size(min = 3,message = "Your name must be  have to be 5 length long")
    private String name;
}
