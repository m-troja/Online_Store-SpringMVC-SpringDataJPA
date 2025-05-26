package com.itbulls.learnit.onlinestore.core.facades;

import com.itbulls.learnit.onlinestore.persistence.entities.CartItem;

public interface CartItemFacade {
	
	CartItem getCartItemById(Integer id);

}
