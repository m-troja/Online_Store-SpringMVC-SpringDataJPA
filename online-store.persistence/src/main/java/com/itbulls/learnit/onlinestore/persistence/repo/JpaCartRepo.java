package com.itbulls.learnit.onlinestore.persistence.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itbulls.learnit.onlinestore.persistence.entities.Cart;
import com.itbulls.learnit.onlinestore.persistence.entities.Product;
import com.itbulls.learnit.onlinestore.persistence.entities.User;

@Repository
public interface JpaCartRepo extends CrudRepository<Cart, Integer>{

	Optional<Cart> findById(Integer cartId);
	
	@Query("SELECT c.products FROM Cart c WHERE c.id = ?1")
	List<Product> findProductsByCartId(Integer cartId);
	 
	Cart findByUser(User user);
	
}
