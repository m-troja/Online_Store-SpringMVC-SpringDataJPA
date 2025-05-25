package com.itbulls.learnit.onlinestore.core.facades;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itbulls.learnit.onlinestore.persistence.entities.Cart;
import com.itbulls.learnit.onlinestore.persistence.entities.Purchase;
import com.itbulls.learnit.onlinestore.persistence.entities.User;

@Service
public interface PurchaseFacade {

	Integer LAST_STATUS_OF_ORDER_FULFILMENT_ID = 6;
	
	Purchase createPurchaseAndReturnPurchase(User user, Cart cart);

	List<Purchase> getNotCompletedPurchases();

	void markFulfilmentStageForPurchaseIdAsCompleted(Integer purchaseId);
	
	Purchase getPurchaseById(Integer purhcaseId);
}
