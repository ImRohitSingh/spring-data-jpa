package com.test.springdatajpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.springdatajpa.models.Product;
import com.test.springdatajpa.models.Request;
import com.test.springdatajpa.service.SpringDataJpaService;

@RestController
public class SpringDataJpaController {

	@Autowired
	private SpringDataJpaService springDataJpaService;

	@PostMapping(path = "/save")
	@ResponseStatus(code = HttpStatus.CREATED, reason = "Product saved")
	public void saveProduct(@RequestBody Request request) {
		springDataJpaService.saveProduct(request);
	}

	@GetMapping(path = "/findAll")
	public ResponseEntity<List<Product>> getAllProducts() {
		return new ResponseEntity<>(springDataJpaService.findAllProducts(), HttpStatus.OK);
	}

	@GetMapping(path = "/findById/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(name = "id", required = true) Integer id) {
		Product response = springDataJpaService.findProductById(id);
		HttpStatus responseCode = ObjectUtils.isEmpty(response) ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
		return new ResponseEntity<>(response, responseCode);
	}

	@GetMapping(path = "/findByName/{name}")
	public ResponseEntity<Product> getProductByName(@PathVariable(name = "name", required = true) String name) {
		Product response = springDataJpaService.findProductByName(name);
		HttpStatus responseCode = ObjectUtils.isEmpty(response) ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
		return new ResponseEntity<>(response, responseCode);
	}

	@PutMapping(path = "/setPriceById/{id}")
	public ResponseEntity<Integer> updateProductById(@PathVariable(name = "id", required = true) Integer id,
			@RequestParam(name = "price", required = true) Double price) {
		return new ResponseEntity<>(springDataJpaService.updateProductById(price, id), HttpStatus.OK);
	}

	@DeleteMapping(path = "/deleteById/{id}")
	@ResponseStatus(code = HttpStatus.MOVED_PERMANENTLY, reason = "Product deleted")
	public void deleteProductById(@PathVariable(name = "id", required = true) Integer id) {
		springDataJpaService.deleteProductById(id);
	}

}
