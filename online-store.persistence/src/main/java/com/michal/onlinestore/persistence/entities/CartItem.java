package com.michal.onlinestore.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	
	public CartItem(Product product, Integer quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public CartItem(Product product) {
		super();
		this.product = product;
	}

	public CartItem() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
	    return "CartItem [id=" + id + ", product=" + product + ", quantity=" + quantity +
	           ", cartId=" + (cart != null ? cart.getId() : null) + "]";
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof CartItem)) return false;
	    CartItem that = (CartItem) o;
	    return id != null && id.equals(that.getId());
	}

	@Override
	public int hashCode() {
	    return 31;
	}

	public CartItem(Product product, Integer quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

}
