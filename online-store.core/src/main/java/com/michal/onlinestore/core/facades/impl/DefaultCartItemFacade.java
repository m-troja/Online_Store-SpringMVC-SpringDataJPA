package com.michal.onlinestore.core.facades.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.michal.onlinestore.core.facades.CartItemFacade;
import com.michal.onlinestore.persistence.entities.CartItem;
import com.michal.onlinestore.persistence.repo.JpaCartItemRepo;

/**
 * Default implementation of CartItemFacade.
 * Provides retrieval of cart items by ID.
 */
=======
import com.michal.onlinestore.persistence.entities.CartItem;
import com.michal.onlinestore.persistence.repo.JpaCartItemRepo;

>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
@Service
public class DefaultCartItemFacade implements CartItemFacade {
	
	@Autowired
<<<<<<< HEAD
	private JpaCartItemRepo cartItemRepo;
	
	@Override
	public CartItem getCartItemById(Integer id) {
		return cartItemRepo.findById(id).orElse(null);
	}
=======
	JpaCartItemRepo cartItemRepo;
	
	@Override
	public CartItem getCartItemById(Integer id) {
		
		return cartItemRepo.findById(id).orElse(null);
	}

>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
}
