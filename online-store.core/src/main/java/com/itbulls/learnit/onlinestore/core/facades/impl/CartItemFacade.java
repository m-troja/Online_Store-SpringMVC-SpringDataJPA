package com.itbulls.learnit.onlinestore.core.facades.impl;

import com.itbulls.learnit.onlinestore.persistence.entities.CartItem;

public interface CartItemFacade {
	
	CartItem getCartItemById(Integer id);

}
