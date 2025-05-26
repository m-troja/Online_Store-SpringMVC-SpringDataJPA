package com.michal.onlinestore.core.facades;

import java.util.List;

import org.springframework.stereotype.Service;

import com.michal.onlinestore.persistence.entities.Product;

/**
 * Facade interface for product-related operations.
 * Provides methods to retrieve and query products based on various criteria.
 */
@Service
public interface ProductFacade {
	
	/**
	 * Retrieves a list of products whose names contain the given search query.
	 * 
	 * @param searchQuery the search string to match product names
	 * @return list of products matching the search criteria
	 */
	List<Product> getProductsLikeName(String searchQuery);

	/**
	 * Retrieves a list of products belonging to a specific category.
	 * 
	 * @param id the ID of the category
	 * @return list of products in the specified category
	 */
	List<Product> getProductsByCategoryId(Integer id);

	/**
	 * Retrieves a paginated list of products for a category.
	 * 
	 * @param categoryId the ID of the category
	 * @param page the current page number (starting from 1)
	 * @param paginationLimit number of products per page
	 * @return paginated list of products for the category
	 */
	List<Product> getProductsByCategoryIdForPageWithLimit(Integer categoryId, Integer page, Integer paginationLimit);

	/**
	 * Calculates the total number of pages available for a given category,
	 * based on the pagination limit.
	 * 
	 * @param categoryId the ID of the category
	 * @param paginationLimit the number of products per page
	 * @return total number of pages for the category
	 */
	Integer getNumberOfPagesForCategory(Integer categoryId, Integer paginationLimit);

	/**
	 * Calculates the total number of pages available for a search query,
	 * based on the pagination limit.
	 * 
	 * @param searchQuery the search string
	 * @param paginationLimit the number of products per page
	 * @return total number of pages matching the search query
	 */
	Integer getNumberOfPagesForSearch(String searchQuery, Integer paginationLimit);

	/**
	 * Retrieves a paginated list of products whose names match the search query.
	 * 
	 * @param searchQuery the search string to match product names
	 * @param page the current page number (starting from 1)
	 * @param paginationLimit number of products per page
	 * @return paginated list of products matching the search query
	 */
	List<Product> getProductsLikeNameForPageWithLimit(String searchQuery, Integer page, Integer paginationLimit);

	/**
	 * Retrieves a product by its unique ID.
	 * 
	 * @param parameter the product ID
	 * @return the product entity or null if not found
	 */
	Product getProductById(Integer productId);

	/**
	 * Retrieves a product by its globally unique identifier (GUID).
	 * 
	 * @param guid the product's GUID
	 * @return the product entity or null if not found
	 */
	Product getProductByGuid(String guid);

}
