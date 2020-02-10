package com.vending.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vending.data.Store;


/**
 * Repository class for Inventory.
 * 
 * @author Sindhuja Dasari
 */
@Repository
public interface StockRepository extends CrudRepository<Store, Long> {
	Store findByProductId(long product_id);
}