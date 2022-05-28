package com.example.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class Comment {
    @NotEmpty(message = "ID must not be empty")
    @Digits(integer = 3,fraction = 0,message = "Your id must be have to be 3 character long")
    private String id;

    @NotEmpty(message = "userid must not be empty")
    @Size(min = 5,message = "Your userid must be  have to be 5 length long")
    private String userid;

    @NotEmpty(message = "productid must not be empty")
    @Digits(integer = 3,fraction = 0,message = "Your productid must be have to be 3 character long")
    private String productid;

    @NotEmpty(message = "message must not be empty")
    @Size(min = 6,message = "Your message must be  have to be 6 length long")
    private String message;

    @NotNull(message = "rate must not be empty")
    @Min(value = 1,message = "must be a number between 1 - 5")
    @Max(value = 5,message = "must be a number between 1 - 5")
    private Integer rate;


}
