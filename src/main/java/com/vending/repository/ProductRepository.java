package com.vending.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vending.data.Product;

/**
 * Repository class for products.
 * 
 * @author Sindhuja Dasari
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	Product findByName(String name);
}
