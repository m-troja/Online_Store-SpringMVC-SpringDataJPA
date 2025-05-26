package com.michal.onlinestore.persistence.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.michal.onlinestore.persistence.entities.Category;

@Repository
public interface JpaCategoryRepo extends CrudRepository<Category, Integer>{

	
	List<Category> findAll();
	
}
