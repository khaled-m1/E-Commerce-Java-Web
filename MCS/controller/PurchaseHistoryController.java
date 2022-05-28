package com.example.ecommerce.controller;

import com.example.ecommerce.model.PurchaseHistory;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.PurchaseHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/purchasehistory")//PurchaseHistory
public class PurchaseHistoryController {
    private final PurchaseHistoryService purchaseHistoryService;
    // Constructor PurchaseHistory
    public PurchaseHistoryController(PurchaseHistoryService purchaseHistoryService) {
        this.purchaseHistoryService = purchaseHistoryService;
    }
    // View PurchaseHistory
    @GetMapping
    public ResponseEntity<ArrayList<PurchaseHistory>> getPurchaseHistory(){
        return ResponseEntity.status(HttpStatus.OK).body(purchaseHistoryService.getPurchaseHistory());
    }
}
