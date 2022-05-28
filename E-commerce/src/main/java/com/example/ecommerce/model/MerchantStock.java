package com.example.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class MerchantStock {
    @NotEmpty(message = "ID must not be empty")
    @Digits(integer = 3,fraction = 0,message = "Your id must be have to be 3 character long")
    private String id;

    @NotEmpty(message = "productid must not be empty")
    @Digits(integer = 3,fraction = 0,message = "Your productid must be have to be 3 character long")
    private String productid;

    @NotEmpty(message = "merchant id must not be empty")
    @Digits(integer = 3,fraction = 0,message = "Your merchantid must be have to be 3 character long")
    private String merchantid;


    @NotEmpty(message = "stock must not be empty")
    @Digits(integer = 10,fraction = 0,message = "Your stock must be have to be more than 10 at start")
    private  String stock;
}
