package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/product")//Product
public class ProductController {
    private final ProductService productService;
    // Constructor Product
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    // View Product
    @GetMapping
    public ResponseEntity<ArrayList<Product>> getProduct(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct());
    }
    // Add Product
    @PostMapping
    public ResponseEntity<Api> addProduct(@RequestBody @Valid Product product, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        boolean isAcceptedAdd = productService.addProduct(product);
        if (!isAcceptedAdd){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Sorry but your request not Accepted :(",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Accepted request :)",200));
    }
    // Edit Product
    @PutMapping("/{index}")
    public ResponseEntity<Api> editProduct(@PathVariable Integer index, @RequestBody @Valid Product product, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        boolean isProductEdit = productService.editProduct(index,product);
        if (!isProductEdit){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Sorry but your request not Edited :(",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Edited request :)",200));
    }
    // Remove Product
    @DeleteMapping("/remove")
    public ResponseEntity<Api> removeProduct(@RequestParam String id){
        boolean isRemovedProduct= productService.removeProduct(id);
        if (!isRemovedProduct){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Sorry but your request not Removed :(",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Removed request :)",200));
    }
    // user can buy a product directly without a cart
    @PostMapping("/directly-purchase")
    public ResponseEntity<String> directlyPurchase(@RequestParam String userid,
                                                   @RequestParam String productid,
                                                   @RequestParam String merchantid,
                                                   @RequestParam String merchantStockid,
                                                   @RequestBody String purchaseHistoryid){
        Integer directlyPurchaseCase = productService.directlyPurchase(userid,productid,merchantid,merchantStockid,purchaseHistoryid);
        switch (directlyPurchaseCase){
            case -1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User id or Product is or Merchant id is Warning! :(");
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sorry but you don't have money for the product :(");
            case 1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sorry but your product is empty from stock :(");
            case 2:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ProductPurchase :)");
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Service Error :(");
        }
    }
}
