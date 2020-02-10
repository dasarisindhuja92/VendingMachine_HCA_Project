package com.vending.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vending.data.Store;
import com.vending.data.Product;
import com.vending.repository.StockRepository;

/**
 * Service class for Iventory repository.
 * 
 * @author Sindhuja Dasari
 */
@Service
public class StockService {

	@Autowired
	private StockRepository inventoryRepository;

	public long getQuantity(Product product) {
		Store iventory = inventoryRepository.findByProductId(product.getId());

		return iventory.getQuatity();
	}

	public void addProduct(Product product, int quantity) {
		Store iventory = new Store();
		iventory.setPrice(product.getPrice());
		iventory.setProductId(product.getId());
		iventory.setQuatity(quantity);

	}

	public void deductProduct(Product product, long quantity) {
		Store iventory = inventoryRepository.findByProductId(product.getId());
		long currentQuantity = iventory.getQuatity();
		if (currentQuantity - quantity >= 0) {
			iventory.setQuatity(currentQuantity - quantity);
			inventoryRepository.save(iventory);
		}
	}

	public void deductProduct(Product product) {
		deductProduct(product, 1);
	}

}
