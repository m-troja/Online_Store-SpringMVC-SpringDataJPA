package com.michal.onlinestore.core.facades;

import java.util.List;

import org.springframework.stereotype.Service;

import com.michal.onlinestore.persistence.entities.Category;

@Service
public interface CategoryFacade {

	List<Category> getCategories();

}
