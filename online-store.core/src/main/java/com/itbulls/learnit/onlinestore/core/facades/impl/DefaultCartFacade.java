package com.itbulls.learnit.onlinestore.core.facades.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.learnit.onlinestore.core.facades.CartFacade;
import com.itbulls.learnit.onlinestore.persistence.entities.Cart;
import com.itbulls.learnit.onlinestore.persistence.entities.Product;
import com.itbulls.learnit.onlinestore.persistence.entities.User;
import com.itbulls.learnit.onlinestore.persistence.repo.JpaCartRepo;

@Service
public class DefaultCartFacade implements CartFacade {
	
	@Autowired
	JpaCartRepo cartRepo;
	
/*	Called by checkout controller. Check if user has a cart already . If no, create a cart and add the product into it.
*	If user has a cart already, add the product into existing cart.	
	Single user may have only one existing (opened) cart                            */
	public void addToCart(User user, Product product)
	{
		
		// Check if user has a cart
		if ( cartRepo.findByUser(user) == null)
		{
			// No existing cart - save a new cart for user
			List<Product> products = new ArrayList<>();
			products.add(product);
			createCart(products , user);

		}
		// User has a cart - add the product into cart
		else {
			Cart cart = cartRepo.findByUser(user);
			cart.addProduct(product);
			cartRepo.save(cart);
			
			System.out.println("Added to cart. New cart : " +  cart.toString());

		}
	}
	
	@Override
	public void createCart(List<Product> products, User user) {
		Cart cart = new Cart(products, user);
		cartRepo.save(cart);
		System.out.println("Created cart : " +  products.toString());

	}

	@Override
	public Cart findCartById(Integer cartId) {
		
		return cartRepo.findById(cartId).orElse(null);
	}

	@Override
	public void deleteCart(Cart cart) {
		cartRepo.delete(cart);
	}

	public Cart findByUser(User user)
	{
		return cartRepo.findByUser(user);
	}

	public BigDecimal calculatePriceOfCart(Cart cart)
	{
		List<Product> products = cart.getProducts();
		BigDecimal price = BigDecimal.ZERO;
		
		for (Product product : products)
		{
			price = price.add(product.getPrice());
		}
		
		return price;
	}
	
	public Cart removeProductFromCart(Integer cartId, Integer productId)
	{

		Cart cart = findCartById(cartId);
		System.out.println("Found cart by ID : " + findCartById(cartId).toString() );

		System.out.println("Size of cart before removeProductFromCart: " + getSizeOfCart(cart) );

		System.out.println("cart : " + cart.toString());

		List<Product> products = cart.getProducts();

		System.out.println("products PRZED : " + products.toString());

		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
		    Product p = iterator.next();
		    if (p.getId().equals(productId)) {
		    	System.out.println("p.getId() : " + p.getId() );
		    	System.out.println("productIdInteger : " + productId );
		        iterator.remove();
		        break; // remove only one product
		    }
		}
		System.out.println("products PO : " + products.toString());

		cart.setProducts(products);
		System.out.println("Size of cart after removeProductFromCart: " + getSizeOfCart(cart) );
		
		return cart;
	}
	
	public Integer getSizeOfCart(Cart cart)
	{
		return cart.getProducts().size();
	}
}
