package com.michal.onlinestore.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michal.onlinestore.core.services.ProductManagementService;
import com.michal.onlinestore.persistence.entities.Product;
import com.michal.onlinestore.persistence.repo.JpaProductRepo;

@Service
public class JpaProductManagementService implements ProductManagementService {

	@Autowired
	JpaProductRepo productRepo;
	
	public List<Product> getProducts() {
		
		return productRepo.findAll();
	}

	@Override
	public Product getProductById(Integer  id) {
		
		return productRepo.findById(id).orElse(null);
	}


}
