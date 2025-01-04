package com.itbulls.learnit.onlinestore.persistence.entities;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "product_name")
	private String productName;

	@Column(name = "price")
	private BigDecimal price;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@Column(name = "img_name")
	private String imgName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "guid")
	private String guid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Category getCategoryDto() {
		return category;
	}
	public void setCategoryDto(Category categoryDto) {
		this.category = categoryDto;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getImgName() {
		return this.imgName;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getGuid() {
		return this.guid;
	}

}
