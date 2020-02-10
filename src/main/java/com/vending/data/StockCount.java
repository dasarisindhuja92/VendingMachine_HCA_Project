package com.vending.data;

import org.springframework.stereotype.Service;

@Service
public class StockCount {
private String name;
private long count;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public long getCount() {
	return count;
}
public void setCount(long count) {
	this.count = count;
}


}
