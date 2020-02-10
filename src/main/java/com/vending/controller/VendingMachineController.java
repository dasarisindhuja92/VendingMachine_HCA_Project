package com.vending.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vending.data.Product;
import com.vending.data.StockCount;
import com.vending.impl.VendingMachineService;
import com.vending.service.ProductService;
import com.vending.service.StockService;

import io.swagger.annotations.ApiOperation;


/**
 * Controller class for  vending machine.
 * 
 * @author Sindhuja Dasari
 */
@Controller
@RequestMapping(value = "/vendingmachine")
public class VendingMachineController {

	@Autowired
	private ProductService productService;
	@Autowired
	private VendingMachineService sodaVendingMachine;
	@Autowired
	private StockService inventoryService;
	@Value("${vending.machine.giveaway}")
	public Boolean isGiveAway;

	/**
	 * This method provides list of products
	 * 
	 * @param model
	 * @return String
	 */
	@ApiOperation(value = "Products Information")
	@RequestMapping(method = RequestMethod.GET)
	public String getProducts(Model model) {

		
		System.out.println("Entered getting Products");
		/*
		 * Map<String,Long> countMap=new HashMap<String,Long>();
		 * 
		 * for (Product product : products) {
		 * countMap.put(product.getName(),inventoryService.getQuantity(product)); }
		 */
		
		
		Iterable<Product> products = this.productService.findAllProducts();
		ArrayList<StockCount> countList=getProductListWithSize(products);
		System.out.println("countList::::::::" + countList.size());
		 model.addAttribute("countList", countList);

		String soldOut = "Sold Out";
		model.addAttribute("productList", products);
		 
		model.addAttribute("sodaVendingMachine", sodaVendingMachine);
		model.addAttribute("soldOut", soldOut);

		return "sodavendingmachine";
	}

	public  ArrayList<StockCount> getProductListWithSize(Iterable<Product> products) {
		int i = 0;
		ArrayList<StockCount> countList = new ArrayList<StockCount>();
		
		for (Product product : products) {
			StockCount obj=new StockCount();
			obj.setName(product.getName());
			obj.setCount(inventoryService.getQuantity(product));
			
			countList.add(i, obj);
			i++;
		}
		return countList;
	}

	/**
	 * This method will insert coin.
	 * 
	 * @param model
	 * @return String
	 */
	@ApiOperation(value = "To get the Products list")
	@RequestMapping(value = "/coin", method = RequestMethod.GET)
	public String insertCoin(Model model) {
		Iterable<Product> products = this.productService.findAllProducts();
		model.addAttribute("productList", products);

		return getProducts(model);
	}
	@ApiOperation(value = "The insert operation")
	@RequestMapping(value = "/Home", method = RequestMethod.GET)
	public String getHome(Model model) {

		Iterable<Product> products = this.productService.findAllProducts();
		ArrayList<StockCount> countList=getProductListWithSize(products);
		System.out.println("countList in home" + countList.size() );
		 model.addAttribute("countList", countList);
		model.addAttribute("productList", products);
		model.addAttribute("isGiveAway", isGiveAway);
		

		return "Home";
	}
	@ApiOperation(value = "Get the desired Soda")
	@RequestMapping(value = "/{soda}", method = RequestMethod.GET)
	public String getDesiredSoda(@PathVariable(value = "soda") String soda, Model model) {
		sodaVendingMachine.getSelectedSoda(soda);
		String message = "Please enjoy your ";
		model.addAttribute("success", message);
		model.addAttribute("soda", soda);
		return getProducts(model);
	}

	/**
	 * This method will return the coin which is inserted into vending machine
	 * 
	 * @param model
	 * @return String
	 */
	@ApiOperation(value = "Remove operation Controller")
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String ejectQuarter(Model model) {

		sodaVendingMachine.removeCoin();
		Iterable<Product> products = this.productService.findAllProducts();
		model.addAttribute("productList", products);
		ArrayList<StockCount> countList=getProductListWithSize(products);
		System.out.println("countList::remove" + countList.size());
		 model.addAttribute("countList", countList);
		 model.addAttribute("isGiveAway", isGiveAway);
		return "Home";
	}
	
	

}