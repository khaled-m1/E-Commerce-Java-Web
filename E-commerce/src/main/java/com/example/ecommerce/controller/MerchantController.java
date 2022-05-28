package com.example.ecommerce.controller;

import com.example.ecommerce.model.Merchant;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.MerchantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/merchant")//Merchant
public class MerchantController {
    private final MerchantService merchantService;
    // Constructor Merchant
    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }
    // View Merchant
    @GetMapping
    public ResponseEntity<ArrayList<Merchant>> getMerchant(){
        return ResponseEntity.status(HttpStatus.OK).body(merchantService.getMerchant());
    }
    // Add Merchant
    @PostMapping
    public ResponseEntity<Api> addMerchant(@RequestBody @Valid Merchant merchant, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        boolean isAcceptedAdd = merchantService.addMerchant(merchant);
        if (!isAcceptedAdd){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Sorry but your request not Accepted :(",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Accepted request :)",200));
    }
    // Edit Merchant
    @PutMapping("/{index}")
    public ResponseEntity<Api> editMerchant(@PathVariable Integer index, @RequestBody @Valid Merchant merchant, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        boolean isMerchantEdit = merchantService.editMerchant(index,merchant);
        if (!isMerchantEdit){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Sorry but your request not Edited :(",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Edited request :)",200));
    }
    // Remove Merchant
    @DeleteMapping("/remove")
    public ResponseEntity<Api> removeMerchant(@RequestParam String id){
        boolean isRemovedMerchant= merchantService.removeMerchant(id);
        if (!isRemovedMerchant){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Sorry but your request not Removed :(",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Removed request :)",200));
    }
}
