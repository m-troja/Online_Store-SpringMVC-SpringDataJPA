package com.michal.onlinestore.core.facades.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michal.onlinestore.persistence.entities.Category;
import com.michal.onlinestore.persistence.repo.JpaCategoryRepo;
import com.michal.onlinestore.core.facades.CategoryFacade;

/**
 * Default implementation of CategoryFacade.
 * Provides retrieval of all product categories.
 */
@Service
public class DefaultCategoryFacade implements CategoryFacade {
	
	@Autowired
	private JpaCategoryRepo categoryRepo;

	@Override
	public List<Category> getCategories() {
		return categoryRepo.findAll();
	}
}
