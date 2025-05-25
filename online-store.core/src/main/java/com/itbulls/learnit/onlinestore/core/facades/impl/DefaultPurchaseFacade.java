package com.itbulls.learnit.onlinestore.core.facades.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itbulls.learnit.onlinestore.core.facades.CartFacade;
import com.itbulls.learnit.onlinestore.core.facades.PurchaseFacade;
import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.persistence.entities.Cart;
import com.itbulls.learnit.onlinestore.persistence.entities.CartItem;
import com.itbulls.learnit.onlinestore.persistence.entities.Product;
import com.itbulls.learnit.onlinestore.persistence.entities.Purchase;
import com.itbulls.learnit.onlinestore.persistence.entities.PurchaseStatus;
import com.itbulls.learnit.onlinestore.persistence.entities.User;
import com.itbulls.learnit.onlinestore.persistence.repo.JpaCartItemRepo;
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
	public Purchase createPurchaseAndReturnPurchase(User user, Cart cart) {
		Purchase purchase = new Purchase();
		purchase.setUser(user);
		purchase.setProducts(getProductsFromCart(cart));
		
		var purchaseStatus = new PurchaseStatus();
		purchaseStatus.setId(1); // the initial status, the first purchase status
		purchase.setPurchaseStatus(purchaseStatus);
		
		purchaseRepo.save((purchase));
		
		return purchase;
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
	
	public Set<Product> getProductsFromCart(Cart cart)
	{
		Set<CartItem> items = cart.getItems();
		Set<Product> products = new HashSet<>();
		Iterator<CartItem> itemIterator = items.iterator(); 
		
//		Iterate over every cart's item
		while(itemIterator.hasNext())
		{
			CartItem item = itemIterator.next();
			Integer itemQty = item.getQuantity();
			
//			Add one product for one quantity of item
			for (int i = 0; i < itemQty ; i++) 
			{
				System.out.println("item =  " + item.toString() + ", i = " + i + ", itemQty = " + itemQty + ", product = " + item.getProduct().toString());
				products.add(item.getProduct());
			}		
		}
		
		System.out.println("getProductsFromCart, products: " + products.toString());
		
		return products;
	}
	
	public Purchase getPurchaseById(Integer purchaseId)
	{
		return purchaseRepo.findById(purchaseId).orElse(null);
	}
	
	public List<Purchase> getPurchasesByUserId(Integer userId)
	{
		return purchaseRepo.findByUserId(userId);
	}

}
