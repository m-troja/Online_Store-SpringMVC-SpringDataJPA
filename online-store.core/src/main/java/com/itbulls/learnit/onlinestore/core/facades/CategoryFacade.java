package com.itbulls.learnit.onlinestore.core.facades;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itbulls.learnit.onlinestore.persistence.entities.Category;

@Service
public interface CategoryFacade {

	List<Category> getCategories();

}
