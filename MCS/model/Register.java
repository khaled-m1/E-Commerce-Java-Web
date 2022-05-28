package com.example.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class Register {
    @NotEmpty(message = "ID must not be empty")
    @Digits(integer = 3,fraction = 0,message = "Your id must be have to be 3 character long")
    private String id;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 5,message = "Your username must be  have to be 5 length long")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 6,message = "Your password must be  have to be 6 length long")
    @Digits(integer = 6,fraction = 0,message = "password must be An Integer numbers")
    private String password;

}
