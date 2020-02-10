package com.vending.utility;

/**
 * Customized exception class when no quater found in vending machine.
 * 
 * @author Sindhuja Dasari
 */
@SuppressWarnings("serial")
public class NoQuarterFoundException extends RuntimeException {
	String message;
	long coins;

	public NoQuarterFoundException(String message, long coins) {
		this.message = message;
		this.coins = coins;
	}

	@Override
	public String getMessage() {
		return message + coins;
	}

	public long getCoins() {
		return coins;
	}
}
