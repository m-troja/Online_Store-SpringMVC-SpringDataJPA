package com.itbulls.learnit.onlinestore.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.itbulls.learnit.onlinestore.persistence.entities.Cart;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="cart_product",
	   joinColumns=@JoinColumn(name="cart_id"),
	   inverseJoinColumns=@JoinColumn(name="product_id"))
	private List<Product> products;
	
	@ManyToOne
	@JoinColumn(name = "fk_cart_user")
	private User user;
	
	{
		products = new ArrayList<>();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
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
		return this.products;
	}

	public void clear() {
		products.clear();
	}

	public Cart(List<Product> products, User user) {
		super();
		this.products = products;
		this.user = user;
	}

	public Cart() {
		super();
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", products=" + products + ", user=" + user + "]";
	}

}
