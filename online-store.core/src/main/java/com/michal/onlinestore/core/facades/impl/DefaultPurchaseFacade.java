package com.michal.onlinestore.core.facades.impl;

import java.math.BigDecimal;
<<<<<<< HEAD
import java.util.HashSet;
=======
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
=======
import com.michal.onlinestore.core.facades.CartFacade;
import com.michal.onlinestore.core.facades.PurchaseFacade;
import com.michal.onlinestore.core.facades.UserFacade;
>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
import com.michal.onlinestore.persistence.entities.Cart;
import com.michal.onlinestore.persistence.entities.CartItem;
import com.michal.onlinestore.persistence.entities.Product;
import com.michal.onlinestore.persistence.entities.Purchase;
import com.michal.onlinestore.persistence.entities.PurchaseStatus;
import com.michal.onlinestore.persistence.entities.User;
<<<<<<< HEAD
import com.michal.onlinestore.persistence.repo.JpaPurchaseRepo;
import com.michal.onlinestore.core.facades.PurchaseFacade;
import com.michal.onlinestore.core.facades.UserFacade;

/**
 * Default implementation of PurchaseFacade.
 * Handles purchase lifecycle and referral reward logic.
 */
@Service
public class DefaultPurchaseFacade implements PurchaseFacade {
	
=======
import com.michal.onlinestore.persistence.repo.JpaCartItemRepo;
import com.michal.onlinestore.persistence.repo.JpaPurchaseRepo;

@Service
public class DefaultPurchaseFacade implements PurchaseFacade {
	
	
>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
	@Autowired
	private UserFacade userFacade;

	@Value("${referrer.reward.rate}")
<<<<<<< HEAD
	private Double referrerRewardRate;
	
	@Autowired
	private JpaPurchaseRepo purchaseRepo;

=======
	private Double reffererRewardRate;
	
	@Autowired
	JpaPurchaseRepo purchaseRepo;
	
>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
	@Override
	public Purchase createPurchaseAndReturnPurchase(User user, Cart cart) {
		Purchase purchase = new Purchase();
		purchase.setUser(user);
<<<<<<< HEAD
		purchase.setProducts(extractProductsFromCart(cart));

		// Initialize purchase status to "pending" (id = 1)
		PurchaseStatus initialStatus = new PurchaseStatus();
		initialStatus.setId(1);
		purchase.setPurchaseStatus(initialStatus);

		return purchaseRepo.save(purchase);
=======
		purchase.setProducts(getProductsFromCart(cart));
		
		var purchaseStatus = new PurchaseStatus();
		purchaseStatus.setId(1); // the initial status, the first purchase status
		purchase.setPurchaseStatus(purchaseStatus);
		
		purchaseRepo.save((purchase));
		
		return purchase;
>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
	}

	@Override
	public List<Purchase> getNotCompletedPurchases() {
		return purchaseRepo.getNotCompletedPurchases(LAST_STATUS_OF_ORDER_FULFILMENT_ID);
	}

	@Override
	public void markFulfilmentStageForPurchaseIdAsCompleted(Integer purchaseId) {
		Purchase purchase = purchaseRepo.findById(purchaseId).orElse(null);
<<<<<<< HEAD
		if (purchase == null) return;

		PurchaseStatus currentStatus = purchase.getPurchaseStatus();
		int nextStatusId = currentStatus.getId() + 1;
		currentStatus.setId(nextStatusId);
		purchase.setPurchaseStatus(currentStatus);
		purchaseRepo.save(purchase);

		// If final status reached and user has a referrer, reward referrer
		if (LAST_STATUS_OF_ORDER_FULFILMENT_ID.equals(nextStatusId) && purchase.getUser().getReferrerUser() != null) {
			User referrer = purchase.getUser().getReferrerUser();
			BigDecimal reward = purchase.getTotalPurchaseCost().multiply(BigDecimal.valueOf(referrerRewardRate));
			referrer.setMoney(referrer.getMoney().add(reward));
			userFacade.updateUser(referrer);
		}
	}
	
	private Set<Product> extractProductsFromCart(Cart cart) {
		Set<Product> products = new HashSet<>();
		for (CartItem item : cart.getItems()) {
			for (int i = 0; i < item.getQuantity(); i++) {
				products.add(item.getProduct());
			}
		}
		return products;
	}

	@Override
	public Purchase getPurchaseById(Integer purchaseId) {
		return purchaseRepo.findById(purchaseId).orElse(null);
	}

	@Override
	public List<Purchase> getPurchasesByUserId(Integer userId) {
		return purchaseRepo.findByUserId(userId);
	}
=======
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

>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
}
