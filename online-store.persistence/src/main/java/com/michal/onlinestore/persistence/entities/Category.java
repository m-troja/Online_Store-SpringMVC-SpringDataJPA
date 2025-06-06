package com.michal.onlinestore.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "category")
public class Category
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "category_name")
	private String categoryName;
	
	@Column(name = "img_name")
	private String imgName;
	
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgName() {
		return this.imgName;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName + ", imgName=" + imgName + "]";
	}

	public Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public Category(Integer id, String categoryName, String imgName) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.imgName = imgName;
	}



}
