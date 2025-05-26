package com.michal.onlinestore.core.facades;

import java.util.List;

import org.springframework.stereotype.Service;

import com.michal.onlinestore.persistence.entities.Category;

/**
 * Facade interface for category-related operations.
 * Provides methods to retrieve product categories.
 */
@Service
public interface CategoryFacade {

    /**
     * Retrieves all available categories.
     * 
     * @return a list of all categories
     */
    List<Category> getCategories();

}
