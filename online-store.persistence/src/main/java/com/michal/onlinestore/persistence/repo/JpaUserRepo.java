package com.michal.onlinestore.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.michal.onlinestore.persistence.entities.User;

@Repository
public interface JpaUserRepo<U> extends CrudRepository<User, Integer> 
{
	//@Query("SELECT u FROM user u where LOWER(u.firstName) = :firstName)")
	//List<User> getUsersByFirstNameCaseInsensitive(String firstName);
	
    @Query("SELECT u FROM User u WHERE LOWER(u.firstName) = LOWER(:firstName)")
	List<User> getAllUsersOrderByFirstname();
	
	User findByEmail( String email);

	
	User findByPartnerCode(String partnerCode);

	List<User> findByReferrerUser(User user);

    @Query("SELECT u FROM User u WHERE LOWER(u.firstName) = LOWER(:firstName)")
	List<User> findByFirstNameCaseInsensitive(@Param(value = "firstName") String firstName);
    
    @Query("SELECT u FROM User u ORDER BY u.firstName")
	List<User> findAllOrderByFirstName();
	
	
}
