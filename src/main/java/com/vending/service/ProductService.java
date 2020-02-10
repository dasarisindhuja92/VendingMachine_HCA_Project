package com.vending.service;

import com.vending.data.Product;
import com.vending.repository.ProductRepository;
import com.vending.utility.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for product repository.
 * 
 * @author Sindhuja Dasari
 */
@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;

	void addProduct(Product product) {
		repository.save(product);
	}

	public Product findProduct(Item item) {
		return repository.findByName(item.getName());
	}

	public Product findProduct(String name) {
		Product product = repository.findByName(name);
		return product;
	}

	public Iterable<Product> findAllProducts() {
		return repository.findAll();
	}
}
