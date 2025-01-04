package com.itbulls.learnit.onlinestore.core.facades.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.itbulls.learnit.onlinestore.core.facades.ProductFacade;
import com.itbulls.learnit.onlinestore.core.services.impl.SpringDataJpaProductManagementService;
import com.itbulls.learnit.onlinestore.persistence.entities.Product;
import com.itbulls.learnit.onlinestore.persistence.repo.JpaProductRepo;

@Service
public class DefaultProductFacade implements ProductFacade {
	
	@Autowired
	JpaProductRepo productRepo;
	
	@Autowired
	SpringDataJpaProductManagementService productService;


	@Override
	public List<Product> getProductsLikeName(String searchQuery) {
		return productRepo.findByNameLike(searchQuery);
	}

	@Override
	public List<Product> getProductsByCategoryId(Integer id) {
		return productRepo.findByCategoryId(id);
	}

	@Override
	public List<Product> getProductsByCategoryIdForPageWithLimit(Integer categoryId, Integer page,
			Integer paginationLimit) {
		return productRepo.findByCategoryIdPaginationLimit(categoryId, page, paginationLimit);
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
		return productRepo.getProductsLikeNameForPageWithLimit(searchQuery, page, paginationLimit);
	}

	@Override
	public Product getProductById(Integer productId) {
		return productRepo.findById(productId).orElse(null);
	}

	@Override
	public Product getProductByGuid(String guid) {
		return productRepo.findByGuid(guid);
	}

}
