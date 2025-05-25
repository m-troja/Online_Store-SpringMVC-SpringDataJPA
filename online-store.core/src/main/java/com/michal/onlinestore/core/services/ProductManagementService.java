package com.michal.onlinestore.core.services;

import java.util.List;

import com.michal.onlinestore.persistence.entities.Product;

public interface ProductManagementService {

	List<Product> getProducts();


	Product getProductById(Integer id);
	
}
