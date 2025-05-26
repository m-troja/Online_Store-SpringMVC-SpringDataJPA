package com.michal.onlinestore.core.facades.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michal.onlinestore.persistence.entities.Product;
import com.michal.onlinestore.persistence.repo.JpaProductRepo;
import com.michal.onlinestore.core.facades.ProductFacade;
import com.michal.onlinestore.core.services.impl.JpaProductManagementService;

/**
 * Default implementation of ProductFacade.
 * Provides product retrieval by various criteria with pagination support.
 * Delegates persistence to JpaProductRepo and management to JpaProductManagementService.
 */
@Service
public class DefaultProductFacade implements ProductFacade {
	
	@Autowired
	private JpaProductRepo productRepo;
	
	@Autowired
	private JpaProductManagementService productService;

	@Override
	public List<Product> getProductsLikeName(String searchQuery) {
		return productRepo.findByNameLike(searchQuery);
	}

	@Override
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
	}

	@Override
	public Product getProductById(Integer productId) {
		return productRepo.findById(productId).orElse(null);
	}

	@Override
	public Product getProductByGuid(String guid) {
		return productRepo.findByGuid(guid);
	}
	
	/**
	 * Calculates the total number of pages given total items and page size.
	 */
	private int calculateTotalPages(int totalItems, int pageSize) {
		return (totalItems + pageSize - 1) / pageSize; // Ceiling division
	}
}
