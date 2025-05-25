package com.itbulls.learnit.onlinestore.persistence.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Purchase")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "fk_purchase_user")
	private User user;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="purchase_product",
	   joinColumns=@JoinColumn(name="purchase_id"),
	   inverseJoinColumns=@JoinColumn(name="product_id"))
	private Set<Product> products;
	
	@ManyToOne
	@JoinColumn(name = "fk_purchase_purchase_status")
	private PurchaseStatus purchaseStatus;
	
	@Column(name = "purchase_timestamp", insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseTimestamp;
	
	public Purchase() {
	}
	
	public Purchase(Integer id, User user, PurchaseStatus purchaseStatus) {
		this.id = id;
		this.user = user;
		this.purchaseStatus = purchaseStatus;
	}

	public Integer getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public PurchaseStatus getPurchaseStatus() {
		return this.purchaseStatus;
	}
	public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}
	public Date getPurchaseTimestamp() {
		return purchaseTimestamp;
	}
	public void setPurchaseTimestamp(Date purchaseTimestamp) {
		this.purchaseTimestamp = purchaseTimestamp;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getTotalPurchaseCost() {
		return products.stream().map(product -> product.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
