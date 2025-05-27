package com.michal.onlinestore.core.facades.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.michal.onlinestore.persistence.entities.Cart;
import com.michal.onlinestore.persistence.entities.CartItem;
import com.michal.onlinestore.persistence.entities.Product;
import com.michal.onlinestore.persistence.entities.Purchase;
import com.michal.onlinestore.persistence.entities.PurchaseStatus;
import com.michal.onlinestore.persistence.entities.User;
import com.michal.onlinestore.persistence.repo.JpaPurchaseRepo;
import com.michal.onlinestore.core.facades.PurchaseFacade;
import com.michal.onlinestore.core.facades.UserFacade;

/**
 * Default implementation of PurchaseFacade.
 * Handles purchase lifecycle and referral reward logic.
 */
@Service
public class DefaultPurchaseFacade implements PurchaseFacade {
	
	@Autowired
	private UserFacade userFacade;

	@Value("${referrer.reward.rate}")
	private Double referrerRewardRate;
	
	@Autowired
	private JpaPurchaseRepo purchaseRepo;

	@Override
	public Purchase createPurchaseAndReturnPurchase(User user, Cart cart) {
		Purchase purchase = new Purchase();
		purchase.setUser(user);
		purchase.setProducts(extractProductsFromCart(cart));

		// Initialize purchase status to "pending" (id = 1)
		PurchaseStatus initialStatus = new PurchaseStatus();
		initialStatus.setId(1);
		purchase.setPurchaseStatus(initialStatus);

		return purchaseRepo.save(purchase);
	}

	@Override
	public List<Purchase> getNotCompletedPurchases() {
		return purchaseRepo.getNotCompletedPurchases(LAST_STATUS_OF_ORDER_FULFILMENT_ID);
	}

	@Override
	public void markFulfilmentStageForPurchaseIdAsCompleted(Integer purchaseId) {
		Purchase purchase = purchaseRepo.findById(purchaseId).orElse(null);
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
	
	public Set<Product> extractProductsFromCart(Cart cart) {
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
}
