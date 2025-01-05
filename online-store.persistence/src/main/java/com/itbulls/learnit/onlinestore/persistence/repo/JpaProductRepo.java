package com.itbulls.learnit.onlinestore.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itbulls.learnit.onlinestore.persistence.entities.Product;

@Repository
public interface JpaProductRepo extends CrudRepository<Product, Integer>
{
	List<Product> findAll();
	
	@Query("SELECT p FROM Product p WHERE p.productName like ?1")
	List<Product> findByNameLike(String name);
	
	@Query("SELECT p FROM Product p WHERE p.category.id = ?1")
	List<Product> findByCategoryId(Integer categoryId);
	
	@Query(value = "SELECT * FROM Product p WHERE p.category_id = :categoryId LIMIT :paginationLimit OFFSET :page", nativeQuery = true)
	List<Product> findByCategoryIdPaginationLimit(@Param("categoryId") Integer categoryId, @Param("page") Integer page, @Param("paginationLimit") Integer paginationLimit);

	@Query("SELECT COUNT(p) FROM Product p WHERE p.category.id = ?1")
	Integer countProductsForCategory(Integer categoryId);

	
	@Query(value = "SELECT COUNT(*) FROM Product WHERE UPPER(product_name) LIKE UPPER(CONCAT('%',?1,'%'))", nativeQuery = true  )
	Integer getProductsForSearch(String searchQuery);
	
	@Query(value = "SELECT p.id, p.guid, p.product_name, p.description, p.price, p.category_id, p.img_name, c.id as cat_id, c.category_name, c.img_name as cat_img "
							+ "FROM learn_it_db.product p JOIN category c ON p.category_id = c.id "
							+ "WHERE UPPER(product_name) LIKE UPPER(CONCAT('%',?1,'%')) LIMIT ?2, ?3", nativeQuery = true)
	 List<Product> getProductsLikeNameForPageWithLimit(String searchQuery, Integer offset, Integer limit);
	
	Product findByGuid(String guid);
	
}
