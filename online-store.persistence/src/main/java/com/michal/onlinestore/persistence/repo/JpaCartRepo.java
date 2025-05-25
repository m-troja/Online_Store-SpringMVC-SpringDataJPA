package com.michal.onlinestore.persistence.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.michal.onlinestore.persistence.entities.Cart;
import com.michal.onlinestore.persistence.entities.CartItem;
import com.michal.onlinestore.persistence.entities.User;

@Repository
public interface JpaCartRepo extends CrudRepository<Cart, Integer>{

	Optional<Cart> findById(Integer cartId);
	
	@Query("SELECT c.items FROM Cart c WHERE c.id = ?1")
	List<CartItem> findItemsByCartId(Integer cartId);
	 
	Cart findByUser(User user);
	
	@Query("SELECT count(c.items) from Cart c ")
	Integer findProductInCart(Cart cart, CartItem item);
	
}
