package com.vending.machine;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vending.data.Product;
import com.vending.impl.VendingMachineService;
import com.vending.service.StockService;
import com.vending.service.ProductService;
import com.vending.utility.Coin;
/**
 * Test class for  soda vending machine.
 * 
 * @author Sindhuja Dasari
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SodaVendingMachineApplicationTest {
    @Autowired
    VendingMachineService vendingMachine;
    @Autowired
    StockService inventoryService;
    @Autowired
    ProductService productService;


    @Test
    public void contextLoads() {
    }

    @Test
    public void InsertCoin(){
        vendingMachine.insertCoin(Coin.QUARTER);
        boolean value = vendingMachine.isHasQuarter();
        Assert.assertEquals(value,true);
        vendingMachine.removeCoin();
        value = vendingMachine.isHasQuarter();
        Assert.assertEquals(value,false);
    }
   


}
