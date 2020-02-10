package com.vending.utility;

/**
 * Enumeration for coin.
 * 
 * @author Sindhuja Dasari
 */
public enum Coin {
	QUARTER(25);

	private int denomination;

	Coin(int denomination) {
		this.denomination = denomination;
	}

	public int getDenomination() {
		return denomination;
	}
}
