package com.example.ecommerce.controller;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/cart")//Cart
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    // Constructor Cart , Product
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }
    // View Category
    @GetMapping
    public ResponseEntity<ArrayList<Cart>> getCart(){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCart());
    }
    // add product to card
    @PostMapping("/add-prodect-card")
    public ResponseEntity<Api> addProdectToCard(Product product){
        ArrayList<Product> productList = productService.getProduct();
        if (productList==null){
            return ResponseEntity.status(404).body(new Api("Product not Found :(",404));
        }
      boolean isaddProdectToCard = cartService.addProdectToCard(product);
        if (!isaddProdectToCard){
            return ResponseEntity.status(400).body(new Api("Your request not Added :(",400));
        }
        return ResponseEntity.status(201).body(new Api("Your request Added :)",201));
    }
    // remove product from a card
    @DeleteMapping("/remove-prodect-card")
    public ResponseEntity<Api> removeProdectFromCard(@RequestParam String id){
        boolean isRemovedProdectFromCard=productService.removeProduct(id);
        if (!isRemovedProdectFromCard){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Sorry but your request not Removed :(",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Removed request :)",200));
    }
    // user can buy a product with a cart
    @PostMapping("/cart-purchase")
    public ResponseEntity<String> cartPurchase(@RequestParam String userid,
                                                   @RequestParam String cardid,
                                                   @RequestParam String productid,
                                                   @RequestParam String merchantid,
                                                   @RequestParam String merchantStockid,
                                                   @RequestBody String purchaseHistoryid){
        Integer cartPurchaseCase = cartService.cartPurchase(userid,productid,merchantid,merchantStockid,purchaseHistoryid,cardid);
        switch (cartPurchaseCase){
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
