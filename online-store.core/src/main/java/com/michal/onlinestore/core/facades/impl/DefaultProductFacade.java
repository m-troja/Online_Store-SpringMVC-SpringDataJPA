package com.michal.onlinestore.core.facades.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.michal.onlinestore.persistence.entities.Product;
import com.michal.onlinestore.persistence.repo.JpaProductRepo;
import com.michal.onlinestore.core.facades.ProductFacade;
import com.michal.onlinestore.core.services.impl.JpaProductManagementService;

/**
 * Default implementation of ProductFacade.
 * Provides product retrieval by various criteria with pagination support.
 * Delegates persistence to JpaProductRepo and management to JpaProductManagementService.
 */
=======
import com.michal.onlinestore.core.facades.ProductFacade;
import com.michal.onlinestore.core.services.impl.JpaProductManagementService;
import com.michal.onlinestore.persistence.entities.Product;
import com.michal.onlinestore.persistence.repo.JpaProductRepo;

>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
@Service
public class DefaultProductFacade implements ProductFacade {
	
	@Autowired
<<<<<<< HEAD
	private JpaProductRepo productRepo;
	
	@Autowired
	private JpaProductManagementService productService;
=======
	JpaProductRepo productRepo;
	
	@Autowired
	JpaProductManagementService productService;

>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b

	@Override
	public List<Product> getProductsLikeName(String searchQuery) {
		return productRepo.findByNameLike(searchQuery);
	}

	@Override
<<<<<<< HEAD
	public List<Product> getProductsByCategoryId(Integer categoryId) {
		return productRepo.findByCategoryId(categoryId);
	}

	@Override
	public List<Product> getProductsByCategoryIdForPageWithLimit(Integer categoryId, Integer page, Integer pageSize) {
		int offset = (page - 1) * pageSize;
		return productRepo.findByCategoryIdPaginationLimit(categoryId, offset, pageSize);
	}

	@Override
	public Integer getNumberOfPagesForCategory(Integer categoryId, Integer pageSize) {
		int totalProducts = productRepo.countProductsForCategory(categoryId);
		return calculateTotalPages(totalProducts, pageSize);
	}

	@Override
	public Integer getNumberOfPagesForSearch(String searchQuery, Integer pageSize) {
		int totalProducts = productRepo.getProductsForSearch(searchQuery);
		return calculateTotalPages(totalProducts, pageSize);
	}

	@Override
	public List<Product> getProductsLikeNameForPageWithLimit(String searchQuery, Integer page, Integer pageSize) {
		int offset = (page - 1) * pageSize;
		return productRepo.getProductsLikeNameForPageWithLimit(searchQuery, offset, pageSize);
=======
	public List<Product> getProductsByCategoryId(Integer id) {
		return productRepo.findByCategoryId(id);
	}

	@Override
	public List<Product> getProductsByCategoryIdForPageWithLimit(Integer categoryId, Integer page,
			Integer paginationLimit) {
		Integer offset = (page - 1) * paginationLimit;
		return productRepo.findByCategoryIdPaginationLimit(categoryId, offset, paginationLimit);
	}

	@Override
	public Integer getNumberOfPagesForCategory(Integer categoryId, Integer paginationLimit) {
		
		Integer totalProductsInCategory = productRepo.countProductsForCategory(categoryId);
		int pages = totalProductsInCategory / paginationLimit;
		if ( (totalProductsInCategory % paginationLimit) != 0) {
			pages++;
		}
		
		return pages;
		}

	@Override
	public Integer getNumberOfPagesForSearch(String searchQuery, Integer paginationLimit) {
		Integer totalProductsForSearch = productRepo.getProductsForSearch(searchQuery);
		int pages = totalProductsForSearch / paginationLimit;
		if ( (totalProductsForSearch % paginationLimit) != 0) {
			pages++;
		}
		return pages;
	}

	@Override
	public List<Product> getProductsLikeNameForPageWithLimit(String searchQuery, Integer page,
			Integer paginationLimit) {
		Integer offset = (page - 1) * paginationLimit;

		return productRepo.getProductsLikeNameForPageWithLimit(searchQuery, offset, paginationLimit);
>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
	}

	@Override
	public Product getProductById(Integer productId) {
		return productRepo.findById(productId).orElse(null);
	}

	@Override
	public Product getProductByGuid(String guid) {
		return productRepo.findByGuid(guid);
	}
<<<<<<< HEAD
	
	/**
	 * Calculates the total number of pages given total items and page size.
	 */
	private int calculateTotalPages(int totalItems, int pageSize) {
		return (totalItems + pageSize - 1) / pageSize; // Ceiling division
	}
=======

>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
}
