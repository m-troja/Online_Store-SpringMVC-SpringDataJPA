package com.michal.onlinestore.core.facades;

import com.michal.onlinestore.persistence.entities.CartItem;

public interface CartItemFacade {
	
	CartItem getCartItemById(Integer id);

}
