package com.michal.onlinestore.core.facades;

import com.michal.onlinestore.persistence.entities.CartItem;

/**
 * Facade interface for cart item related operations.
 * Provides method to retrieve a cart item by its identifier.
 */
public interface CartItemFacade {

    /**
     * Retrieves a CartItem by its unique identifier.
     *
     * @param id the identifier of the cart item
     * @return the CartItem if found, otherwise null
     */
    CartItem getCartItemById(Integer id);

}
