package com.vending.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vending.data.Product;
import com.vending.impl.VendingMachineService;
import com.vending.service.StockService;
import com.vending.service.ProductService;
import com.vending.utility.Coin;

import io.swagger.annotations.ApiOperation;

/**
 * Controller class for soda vending machine.
 * 
 * @author Sindhuja Dasari
 */
@Controller
@RequestMapping(value = "/vendingMachineInsert")
public class VendingMachineInsertController {

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
	@RequestMapping(method = RequestMethod.GET)
	public String getProducts(Model model) {
		
		sodaVendingMachine.insertCoin(Coin.QUARTER);
		Iterable<Product> products = this.productService.findAllProducts();
	
		System.out.println("IS GIVE AWAY in products :"+isGiveAway);
		
		String soldOut = "Sold Out";
		model.addAttribute("productList", products);
		model.addAttribute("sodaVendingMachine", sodaVendingMachine);
		model.addAttribute("soldOut", soldOut);

		return "sodavendingmachine";
	}
	
	@ApiOperation(value = "Give Away Operation")
	@RequestMapping(method = RequestMethod.GET,value = "/giveAway")
	public String giveAway(Model model) {
	
		sodaVendingMachine.insertCoin(Coin.QUARTER);
		Iterable<Product> products = this.productService.findAllProducts();
		System.out.println("IS GIVE AWAY :"+isGiveAway);
		
		String soldOut = "Sold Out";
		model.addAttribute("productList", products);
		model.addAttribute("isGiveAway", isGiveAway);
		model.addAttribute("sodaVendingMachine", sodaVendingMachine);
		model.addAttribute("soldOut", soldOut);

		return "sodavendingmachine";
	}
	

	
	

}