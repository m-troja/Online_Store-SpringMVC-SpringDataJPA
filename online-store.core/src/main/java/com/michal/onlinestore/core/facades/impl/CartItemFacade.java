package com.michal.onlinestore.core.facades.impl;

import com.michal.onlinestore.persistence.entities.CartItem;

public interface CartItemFacade {
	
	CartItem getCartItemById(Integer id);

}
