package com.michal.onlinestore.core.services;

import java.util.List;

import com.michal.onlinestore.persistence.entities.Purchase;

public interface PurchaseManagementService {

	void addPurchase(Purchase purchase);

	List<Purchase> getPurchasesByUserId(int userId);
	
	List<Purchase> getPurchases();
}
