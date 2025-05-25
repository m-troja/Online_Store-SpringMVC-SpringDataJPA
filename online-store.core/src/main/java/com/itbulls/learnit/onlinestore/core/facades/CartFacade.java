package com.itbulls.learnit.onlinestore.core.facades;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.itbulls.learnit.onlinestore.persistence.entities.Cart;
import com.itbulls.learnit.onlinestore.persistence.entities.CartItem;
import com.itbulls.learnit.onlinestore.persistence.entities.Product;
import com.itbulls.learnit.onlinestore.persistence.entities.User;

@Service
public interface CartFacade {

	void createCart(Set<CartItem> items, User user);

	void addToCart(User user, Product product);

	Cart findCartById(Integer cartId);

	void deleteCart(Cart cart);
	
	 Cart findByUser(User user);
	 
	 public BigDecimal calculatePriceOfCart(Cart cart);
	 
	 Cart removeItemFromCart(Integer cartId, Integer itemId);
	 
	 Integer getSizeOfCart(Cart cart);
	 
	 Integer getQtyOfItemInCart(Cart cart, Product product);
}
