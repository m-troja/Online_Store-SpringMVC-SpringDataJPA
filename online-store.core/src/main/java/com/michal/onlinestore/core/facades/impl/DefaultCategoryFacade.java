package com.michal.onlinestore.core.facades.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.michal.onlinestore.persistence.entities.Category;
import com.michal.onlinestore.persistence.repo.JpaCategoryRepo;
import com.michal.onlinestore.core.facades.CategoryFacade;

/**
 * Default implementation of CategoryFacade.
 * Provides retrieval of all product categories.
 */
=======
import com.michal.onlinestore.core.facades.CategoryFacade;
import com.michal.onlinestore.persistence.entities.Category;
import com.michal.onlinestore.persistence.repo.JpaCategoryRepo;

>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
@Service
public class DefaultCategoryFacade implements CategoryFacade {
	
	@Autowired
<<<<<<< HEAD
	private JpaCategoryRepo categoryRepo;
=======
	JpaCategoryRepo categoryRepo;

>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b

	@Override
	public List<Category> getCategories() {
		return categoryRepo.findAll();
	}
<<<<<<< HEAD
=======

>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
}
