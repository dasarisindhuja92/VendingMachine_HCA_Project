package com.vending.data;

import javax.persistence.*;

/**
 * hidernate entity mapping class for inventory.
 * 
 * @author Sindhuja Dasari
 */
@Entity
@Table(name = "inventory")
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private long id;
	@Column(name = "product_ID")
	private long productId;
	@Column(name = "quantity")
	private long quatity;
	@Column(name = "price")
	private double price;

	public long getQuatity() {
		return quatity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public void setQuatity(long quatity) {
		this.quatity = quatity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
