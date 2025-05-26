package com.michal.onlinestore.core.facades.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michal.onlinestore.core.facades.CartItemFacade;
import com.michal.onlinestore.persistence.entities.CartItem;
import com.michal.onlinestore.persistence.repo.JpaCartItemRepo;

@Service
public class DefaultCartItemFacade implements CartItemFacade {
	
	@Autowired
	JpaCartItemRepo cartItemRepo;
	
	@Override
	public CartItem getCartItemById(Integer id) {
		
		return cartItemRepo.findById(id).orElse(null);
	}

}
