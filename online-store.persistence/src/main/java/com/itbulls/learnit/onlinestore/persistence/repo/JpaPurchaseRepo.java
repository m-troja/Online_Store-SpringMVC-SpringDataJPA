package com.itbulls.learnit.onlinestore.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.itbulls.learnit.onlinestore.persistence.entities.Purchase;

@Repository
public interface JpaPurchaseRepo extends CrudRepository<Purchase,Integer>{

	@Query("SELECT p FROM Purchase p WHERE p.user.id = ?1")
	List<Purchase> findByUserId(Integer id);
	
//	List<Purchase> findAll();
	
	@Query("SELECT p FROM Purchase p WHERE p.purchaseStatus.id != ?1")
	 List<Purchase> getNotCompletedPurchases(Integer stageId);
	 
	
	
}
