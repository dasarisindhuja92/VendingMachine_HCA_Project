package com.vending.impl;

import com.vending.data.Product;
import com.vending.utility.Coin;

/**
 * Service interface which has the methods for vending machine activity.
 * 
 * @author Sindhuja Dasari
 */
public interface VendingMachineService {

	void insertCoin(Coin quarter);

	void getSelectedSoda(String name);

	void removeCoin();

	boolean isHasQuarter();

	boolean isSoldOut(Product product);

}
