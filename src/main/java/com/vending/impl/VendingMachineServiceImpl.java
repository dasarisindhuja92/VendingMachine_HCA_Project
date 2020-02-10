package com.vending.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vending.data.Product;
import com.vending.service.StockService;
import com.vending.service.OrderService;
import com.vending.service.ProductService;
import com.vending.utility.Coin;
import com.vending.utility.NoQuarterFoundException;

/**
 * Service implementation class which has the methods for vending machine
 * activity.
 * 
 * @author Sindhuja Dasari
 */
@Service
public class VendingMachineServiceImpl implements VendingMachineService {
	private List<Coin> coins = new ArrayList<>();
	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;
	@Autowired
	private StockService inventoryService;
	@Value("${vending.machine.giveaway}")
	private Boolean isGiveAway;

	private boolean hasQuarter;
	private boolean soldOut;

	VendingMachineServiceImpl() {
		hasQuarter = false;
		soldOut = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vending.machine.business.service.impl.SodaVendingMachine#isHasQuarter
	 * ()
	 */
	@Override
	public boolean isHasQuarter() {
		if (coins.size() > 0) {
			hasQuarter = true;
		} else {
			hasQuarter = false;
		}
		return hasQuarter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vending.machine.business.service.impl.SodaVendingMachine#isSoldOut(
	 * com.vending.machine.data.entity.Product)
	 */
	@Override
	public boolean isSoldOut(Product product) {
		
		long quantity = inventoryService.getQuantity(product);
		return quantity <= 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vending.machine.business.service.impl.SodaVendingMachine#
	 * insertQuarter(com.vending.machine.data.utility.Coin)
	 */
	@Override
	public void insertCoin(Coin quarter) {
		
		coins.add(quarter);
		hasQuarter = isHasQuarter();
	}
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vending.machine.business.service.impl.SodaVendingMachine#
	 * getDesiredSoda(java.lang.String)
	 */
	@Override
	public void getSelectedSoda(String soda) {
		System.out.println("hasQuarter:::::::::::::::::::::::::::"+hasQuarter+"coins.size()"+coins.size());
		
		if (hasQuarter && coins.size() > 0) {
			Product p = productService.findProduct(soda);
			soldOut = isSoldOut(p);
			if (!soldOut) {
				inventoryService.deductProduct(p);
				dispenseSoda(p);
				coins.remove(0);
			} else {
				throw new RuntimeException("Item is Sold out");
			}

		} else {
			throw new RuntimeException("no quarter inserted");

		}
	}

	private void dispenseSoda(Product product) {
		orderService.placeOrder(product);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vending.machine.business.service.impl.SodaVendingMachine#
	 * removeQuarter()
	 */
	@Override
	public void removeCoin() {
		if (coins.size() > 0) {
			coins.remove(0);
		} else {
			throw new NoQuarterFoundException("No quarter was inserted ", coins.size());
		}
	}

}
