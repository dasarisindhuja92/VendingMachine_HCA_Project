package com.vending.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vending.data.Order;
import com.vending.data.Product;
import com.vending.repository.OrderRepository;
import com.vending.utility.Item;

/**
 * Service class for Order repository.
 * 
 * @author Sindhuja Dasari
 */
@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Value("${vending.machine.giveaway}")
	public Boolean isGiveAway;
	@SuppressWarnings("unused")
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public void placeOrder(Product product, int quantity) {
		Order order = new Order();
		order.setDate(new Date());
		order.setProductId(product.getId());
		order.setQuantity(quantity);
		if(!isGiveAway) {
			order.setPrice(0);
		}else {
			order.setPrice(Item.valueOf(product.getName()).getPrice());
		}
		
		orderRepository.save(order);
	}

	public List<Order> retrieveOrder(long id) {

		return orderRepository.findByProductId(id);

	}

	public void placeOrder(Product product) {
		placeOrder(product, 1);
	}
}
