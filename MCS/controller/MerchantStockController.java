package com.example.ecommerce.controller;

import com.example.ecommerce.model.MerchantStock;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.MerchantStockService;
import com.example.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/merchantstock")//MerchantStock
public class MerchantStockController {
    private final MerchantStockService merchantStockService;
    private final ProductService productService;
    // Constructor Merchant Stock and Product
    public MerchantStockController(MerchantStockService merchantStockService, ProductService productService) {
        this.merchantStockService = merchantStockService;
        this.productService = productService;
    }
    // View Merchant Stock
    @GetMapping
    public ResponseEntity<ArrayList<MerchantStock>> getMerchantStock(){
        return ResponseEntity.status(HttpStatus.OK).body(merchantStockService.getMerchantStock());
    }
    // Add Merchant Stock
    @PostMapping
    public ResponseEntity<Api> addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        boolean isAcceptedAdd = merchantStockService.addMerchantStock(merchantStock);
        if (!isAcceptedAdd){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Sorry but your request not Accepted :(",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Accepted request :)",200));
    }
    // Edit Merchant Stock
    @PutMapping("/{index}")
    public ResponseEntity<Api> editMerchantStock(@PathVariable Integer index, @RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        boolean isMerchantStock = merchantStockService.editMerchantStock(index,merchantStock);
        if (!isMerchantStock){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Sorry but your request not Edited :(",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Edited request :)",200));
    }
    // Remove Merchant Stock
    @DeleteMapping("/remove")
    public ResponseEntity<Api> removeMerchantStock(@RequestParam String id){
        boolean isRemovedMerchantStock= merchantStockService.removeMerchantStock(id);
        if (!isRemovedMerchantStock){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Sorry but your request not Removed :(",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Removed request :)",200));
    }
    // add product to a merchantStock
    @PostMapping("/add-prodect-merchantStock")
    public ResponseEntity<Api> addProdectTomerchantStock(Product product){
        ArrayList<Product> productList = productService.getProduct();
        if (productList==null){
            return ResponseEntity.status(404).body(new Api("Product not Found :(",404));
        }
        boolean isaddProdectTomerchantStock = merchantStockService.addProdectTomerchantStock(product);
        if (!isaddProdectTomerchantStock){
            return ResponseEntity.status(400).body(new Api("Your request not Added :(",400));
        }
        return ResponseEntity.status(201).body(new Api("Your request Added :)",201));
    }
}
