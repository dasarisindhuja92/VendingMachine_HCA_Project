package com.vending.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vending.data.Order;

/**
 * Order repository class for products.
 * 
 * @author Sindhuja Dasari
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findByProductId(long id);
}
