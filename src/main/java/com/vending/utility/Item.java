package com.vending.utility;

/**
 * Enumeration for products.
 * 
 * @author Sindhuja Dasari
 */
public enum Item {
	Coke("Coke", 25), Pepsi("Pepsi", 25), Orange("Orange",25) ,  Fanta("Fanta", 25) , Sprite("Sprite", 25), Water("Water",25);

	private String name;
	private int price;

	Item(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
