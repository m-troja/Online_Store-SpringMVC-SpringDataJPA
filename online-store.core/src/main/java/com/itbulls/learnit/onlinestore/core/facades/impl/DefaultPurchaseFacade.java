package com.itbulls.learnit.onlinestore.core.facades.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itbulls.learnit.onlinestore.core.facades.PurchaseFacade;
import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.persistence.entities.Product;
import com.itbulls.learnit.onlinestore.persistence.entities.Purchase;
import com.itbulls.learnit.onlinestore.persistence.entities.PurchaseStatus;
import com.itbulls.learnit.onlinestore.persistence.entities.User;
import com.itbulls.learnit.onlinestore.persistence.repo.JpaPurchaseRepo;

@Service
public class DefaultPurchaseFacade implements PurchaseFacade {
	
	
	@Autowired
	private UserFacade userFacade;
	
	@Value("${referrer.reward.rate}")
	private Double reffererRewardRate;
	
	@Autowired
	JpaPurchaseRepo purchaseRepo;
	
	@Override
	public void createPurchase(User user, Product product) {
		Purchase purchase = new Purchase();
		purchase.setUser(user);
		purchase.setProducts(new ArrayList<>(Arrays.asList(product)));
		
		var purchaseStatus = new PurchaseStatus();
		purchaseStatus.setId(1); // the initial, the first purchase status
		purchase.setPurchaseStatus(purchaseStatus);
		
		purchaseRepo.save((purchase));
	}

	@Override
	public List<Purchase> getNotCompletedPurchases() {
		return purchaseRepo.getNotCompletedPurchases(LAST_STATUS_OF_ORDER_FULFILMENT_ID);
	}

	@Override
	public void markFulfilmentStageForPurchaseIdAsCompleted(Integer purchaseId) {
		Purchase purchase = purchaseRepo.findById(purchaseId).orElse(null);
		PurchaseStatus purchaseStatus = purchase.getPurchaseStatus();
		int newPurchaseStatusId = purchaseStatus.getId() + 1;
		purchaseStatus.setId(newPurchaseStatusId);
		purchase.setPurchaseStatus(purchaseStatus);
		
		purchaseRepo.save(purchase);
		
		if (LAST_STATUS_OF_ORDER_FULFILMENT_ID.equals(newPurchaseStatusId) 
				&& purchase.getUser().getReferrerUser() != null) {
			User referrerUser = purchase.getUser().getReferrerUser();
			BigDecimal shareFromPurchase = purchase.getTotalPurchaseCost().add(BigDecimal.valueOf(reffererRewardRate));
			referrerUser.setMoney(referrerUser.getMoney().add(shareFromPurchase));
			userFacade.updateUser(referrerUser);
		}
	}

}
