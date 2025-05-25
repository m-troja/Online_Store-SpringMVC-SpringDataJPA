package com.michal.onlinestore.core.facades;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.michal.onlinestore.persistence.entities.Cart;
import com.michal.onlinestore.persistence.entities.CartItem;
import com.michal.onlinestore.persistence.entities.Product;
import com.michal.onlinestore.persistence.entities.User;

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
	 
	 void deleteCart(Integer cartId);
	 
	 Integer getNumberOfProductsInCart(Cart cart);
}
