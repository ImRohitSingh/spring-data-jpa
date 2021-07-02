package com.test.springdatajpa.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.test.springdatajpa.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByPrice(Double price);

	@Query("SELECT p FROM products p WHERE LOWER(p.name) = LOWER(:name)")
	Product findByName(@Param("name") String name);

	@Modifying
	@Transactional(readOnly = false)
	@Query("UPDATE products p SET p.price = ?1, p.lstCngDTime = ?2 WHERE p.id = ?3")
	int setPriceForProduct(Double price, LocalDateTime lstCngDTime, Integer id);

}
