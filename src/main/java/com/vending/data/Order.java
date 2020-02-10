package com.vending.data;

import javax.persistence.*;
import java.util.Date;

/**
 * hibernate entity mapping class for order history.
 * 
 * @author Sindhuja Dasari
 */
@Entity
@Table(name = "Order_history")
public class Order {
	@Id
	@Column(name = "Order_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "product_ID")
	private long productId;
	@Column(name = "quantity")
	private long quantity;
	@Column(name = "order_date")
	private Date date;
	@Column(name = "price")
	private double price;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
