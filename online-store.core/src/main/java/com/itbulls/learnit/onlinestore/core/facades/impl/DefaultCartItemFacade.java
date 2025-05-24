package com.itbulls.learnit.onlinestore.core.facades.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.learnit.onlinestore.persistence.entities.CartItem;
import com.itbulls.learnit.onlinestore.persistence.repo.JpaCartItemRepo;

@Service
public class DefaultCartItemFacade implements CartItemFacade {
	
	@Autowired
	JpaCartItemRepo cartItemRepo;
	
	@Override
	public CartItem getCartItemById(Integer id) {
		
		return cartItemRepo.findById(id).orElse(null);
	}

}
