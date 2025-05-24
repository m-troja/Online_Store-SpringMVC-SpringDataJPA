package com.itbulls.learnit.onlinestore.persistence.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itbulls.learnit.onlinestore.persistence.entities.Cart;
import com.itbulls.learnit.onlinestore.persistence.entities.Product;
import com.itbulls.learnit.onlinestore.persistence.entities.CartItem;

@Repository
public interface JpaCartItemRepo extends CrudRepository<CartItem, Integer> {
	
	@Query("SELECT count(ci) FROM CartItem ci WHERE ci.cart = ?1 AND ci.product = ?2")
	Integer countByCartAndProduct(Cart cart, Product product);
}
