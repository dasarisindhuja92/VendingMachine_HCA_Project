package com.vending.controller;
import java.io.IOException;
import java.io.InputStream;
/**
 * This class provides different services endpoints for products,inventory and order history.
 * 
 * @author Sindhuja Dasari
 */
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vending.data.Order;
import com.vending.data.Product;
import com.vending.service.StockService;

import io.swagger.annotations.ApiOperation;

import com.vending.service.OrderService;
import com.vending.service.ProductService;

@RestController
@RequestMapping(value = "/vending")
public class VendingMachineServiceController {
	@Autowired
	private ProductService productService;
	@Autowired
	private StockService inventoryService;

	@Autowired
	private OrderService orderService;

	/**
	 * This method will return list of products
	 * 
	 * @return List of products
	 */
	@ApiOperation(value = "Get Products List")
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();
		Iterable<Product> products1 = productService.findAllProducts();
		for (Product p : products1) {
			products.add(p);
		}

		return products;

	}

	/**
	 * This method will return the requested product.
	 * 
	 * @param name
	 * @return Product
	 */
	@ApiOperation(value = "Get the specific Proct information from Repository")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{name}")
	public Product getProduct(@PathVariable(value = "name") String name) {
		Product product = productService.findProduct(name.toUpperCase());
		if (product == null) {
			return null;
		} else
			return product;
	}

	/**
	 * This method will return quantity of the requested product.
	 * 
	 * @param name
	 * @return quantity
	 */
	@ApiOperation(value = "Get the Quantity of specific Soda")
	@RequestMapping(method = RequestMethod.GET, value = "/store/{name}")
	public long getQuantity(@PathVariable(value = "name") String name) {
		Product product = productService.findProduct(name.toUpperCase());
		return inventoryService.getQuantity(product);
	}

	/**
	 * This method will return order history of the requested product.
	 * 
	 * @param id
	 * @return List of orders
	 */
	@ApiOperation(value = "Product History Information")
	@RequestMapping(method = RequestMethod.GET, value = "/product_history/{id}")
	public List<Order> getProductHistory(@PathVariable(value = "id") long id) {
		return orderService.retrieveOrder(id);

	}
	
	
}
