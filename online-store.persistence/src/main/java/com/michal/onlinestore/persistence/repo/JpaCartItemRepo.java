package com.michal.onlinestore.persistence.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.michal.onlinestore.persistence.entities.Cart;
<<<<<<< HEAD
import com.michal.onlinestore.persistence.entities.CartItem;
import com.michal.onlinestore.persistence.entities.Product;
=======
import com.michal.onlinestore.persistence.entities.Product;
import com.michal.onlinestore.persistence.entities.CartItem;
>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b

@Repository
public interface JpaCartItemRepo extends CrudRepository<CartItem, Integer> {
	
	@Query("SELECT count(ci) FROM CartItem ci WHERE ci.cart = ?1 AND ci.product = ?2")
	Integer countByCartAndProduct(Cart cart, Product product);
}
