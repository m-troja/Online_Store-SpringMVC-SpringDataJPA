package com.itbulls.learnit.onlinestore.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import com.itbulls.learnit.onlinestore.persistence.entities.Cart;

public class Cart {

	private List<Product> products;
	
	{
		products = new ArrayList<>();
	}
	
	public boolean isEmpty() {
		return products.isEmpty();
	}

	public void addProduct(Product product) {
		if (product == null) {
			return;
		}
		products.add(product);
	}

	public List<Product> getProducts() {
//		return Collections.unmodifiableList(this.products); // returns unmodifiable List
		return this.products;
	}

	public void clear() {
		products.clear();
	}

}
