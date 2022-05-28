package com.example.ecommerce.service;

import com.example.ecommerce.model.MerchantStock;
import com.example.ecommerce.model.PurchaseHistory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PurchaseHistoryService {
    private ArrayList<PurchaseHistory> purchaseHistories = new ArrayList<>();
    public ArrayList<PurchaseHistory> getPurchaseHistory() {
        return purchaseHistories;
    }
    // get PurchaseHistory ID
    public PurchaseHistory getPurchaseHistory(String purchaseHistoryid) {
        for (PurchaseHistory purchaseHistory:purchaseHistories) {
            if (purchaseHistory.getId().equals(purchaseHistoryid)){
                return purchaseHistory;
            }
        }
        return null;
    }
}
