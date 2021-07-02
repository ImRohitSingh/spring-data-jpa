package com.test.springdatajpa.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.springdatajpa.dao.ProductRepository;
import com.test.springdatajpa.models.Product;
import com.test.springdatajpa.models.Request;

@Service
public class SpringDataJpaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringDataJpaService.class);

	private ProductRepository productRepository;

	@Autowired
	public SpringDataJpaService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public void saveProduct(Request entity) {
		LocalDateTime currTime = LocalDateTime.now();
		productRepository.save(new Product(null, entity.getName(), entity.getPrice(), currTime, currTime));
		LOGGER.info("{} successfully saved to database", entity);
	}

	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	public Product findProductById(Integer id) {
		Optional<Product> retrievedEntity = productRepository.findById(id);
		return retrievedEntity.isPresent() ? retrievedEntity.get() : null;
	}

	public Product findProductByName(String name) {
		return productRepository.findByName(name);
	}

	public Integer updateProductById(Double price, Integer id) {
		Integer noOfRowsUpdated = productRepository.setPriceForProduct(price, LocalDateTime.now(), id);
		LOGGER.info("Product id {} updated with price {}", id, price);
		return noOfRowsUpdated;
	}

	public void deleteProductById(Integer id) {
		productRepository.deleteById(id);
		LOGGER.info("Product id {} successfully deleted", id);
	}

}
