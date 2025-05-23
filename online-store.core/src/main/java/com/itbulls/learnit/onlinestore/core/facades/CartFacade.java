package com.itbulls.learnit.onlinestore.core.facades;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itbulls.learnit.onlinestore.persistence.entities.Cart;
import com.itbulls.learnit.onlinestore.persistence.entities.Product;
import com.itbulls.learnit.onlinestore.persistence.entities.User;

@Service
public interface CartFacade {

	void createCart(List<Product> products, User user);

	void addToCart(User user, Product product);

	Cart findCartById(Integer cartId);

	void deleteCart(Cart cart);
	
	 Cart findByUser(User user);
	 
	 public BigDecimal calculatePriceOfCart(Cart cart);
	 
	 Cart removeProductFromCart(Integer cartId, Integer productId);
	 
	 Integer getSizeOfCart(Cart cart);
}
