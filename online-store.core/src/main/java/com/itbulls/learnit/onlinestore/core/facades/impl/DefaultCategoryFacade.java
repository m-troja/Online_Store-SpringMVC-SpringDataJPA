package com.itbulls.learnit.onlinestore.core.facades.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbulls.learnit.onlinestore.core.facades.CategoryFacade;
import com.itbulls.learnit.onlinestore.persistence.entities.Category;
import com.itbulls.learnit.onlinestore.persistence.repo.JpaCategoryRepo;

@Service
public class DefaultCategoryFacade implements CategoryFacade {
	
	@Autowired
	JpaCategoryRepo categoryRepo;


	@Override
	public List<Category> getCategories() {
		return categoryRepo.findAll();
	}

}
