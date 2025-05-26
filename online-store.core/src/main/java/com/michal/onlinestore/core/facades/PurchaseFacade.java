package com.michal.onlinestore.core.facades;

import java.util.List;

import org.springframework.stereotype.Service;

import com.michal.onlinestore.persistence.entities.Cart;
import com.michal.onlinestore.persistence.entities.Purchase;
import com.michal.onlinestore.persistence.entities.User;

<<<<<<< HEAD
/**
 * Facade interface for managing purchase-related operations.
 * Defines methods to create and track purchases within the system.
 */
@Service
public interface PurchaseFacade {

    /**
     * Constant representing the last status ID in the order fulfillment process.
     */
    Integer LAST_STATUS_OF_ORDER_FULFILMENT_ID = 6;

    /**
     * Creates a purchase for the given user based on the provided cart
     * and returns the persisted Purchase entity.
     *
     * @param user the user making the purchase
     * @param cart the cart containing products to purchase
     * @return the created Purchase entity
     */
    Purchase createPurchaseAndReturnPurchase(User user, Cart cart);

    /**
     * Retrieves a list of purchases that have not yet been completed.
     *
     * @return list of incomplete Purchase entities
     */
    List<Purchase> getNotCompletedPurchases();

    /**
     * Advances the fulfillment stage of a purchase by purchase ID,
     * marking the current stage as completed.
     *
     * @param purchaseId the ID of the purchase to update
     */
    void markFulfilmentStageForPurchaseIdAsCompleted(Integer purchaseId);
    
    /**
     * Retrieves a Purchase entity by its unique identifier.
     *
     * @param purchaseId the ID of the purchase
     * @return the Purchase entity or null if not found
     */
    Purchase getPurchaseById(Integer purchaseId);
    
    /**
     * Retrieves all purchases made by a specific user.
     *
     * @param userId the ID of the user
     * @return list of purchases associated with the user
     */
    List<Purchase> getPurchasesByUserId(Integer userId);
=======
@Service
public interface PurchaseFacade {

	Integer LAST_STATUS_OF_ORDER_FULFILMENT_ID = 6;
	
	Purchase createPurchaseAndReturnPurchase(User user, Cart cart);

	List<Purchase> getNotCompletedPurchases();

	void markFulfilmentStageForPurchaseIdAsCompleted(Integer purchaseId);
	
	Purchase getPurchaseById(Integer purhcaseId);
	
	List<Purchase> getPurchasesByUserId(Integer userId);
>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
}
