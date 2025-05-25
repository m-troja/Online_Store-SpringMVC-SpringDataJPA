package com.michal.onlinestore.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michal.onlinestore.core.services.PurchaseManagementService;
import com.michal.onlinestore.persistence.entities.Purchase;
import com.michal.onlinestore.persistence.repo.JpaPurchaseRepo;

@Service
public class JpaPurchaseService implements PurchaseManagementService {
	
	@Autowired
	private JpaPurchaseRepo purchaseRepo;
	
	
	public void addPurchase(Purchase purchase) {
		purchaseRepo.save(purchase);
	}

	public List<Purchase> getPurchasesByUserId(int userId) {
		return purchaseRepo.findByUserId(userId);
		
	}

	public List<Purchase> getPurchases() {
		return (List<Purchase>) purchaseRepo.findAll();
	}

}
