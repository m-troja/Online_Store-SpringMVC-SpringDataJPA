package com.itbulls.learnit.onlinestore.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.itbulls.learnit.onlinestore.persistence.entities.Cart;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<CartItem> items;
	
	@ManyToOne
	@JoinColumn(name = "fk_cart_user")
	private User user;
	
	{
		items = new ArrayList<>();
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

	public void setProducts(List<CartItem> products) {
		this.items = products;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public void addItem(CartItem item) {
		if (item == null) {
			return;
		}
		items.add(item);
	}

	public List<CartItem> getItems() {
		return this.items;
	}

	public void clear() {
		items.clear();
	}

	public Cart(List<CartItem> items, User user) {
		super();
		this.items = items;
		this.user = user;
	}

	public Cart() {
		super();
	}

	@Override
	public String toString() {
	    return "Cart [id=" + id + ", itemsCount=" + (items != null ? items.size() : 0) +
	           ", user=" + user + "]";
	}


}
