package com.example.JavaTdd.payment;

public class CardPaymentCharge {
	
	private final boolean isCardDebited;
	
	public CardPaymentCharge(boolean isCardDebited) {
		this.isCardDebited = isCardDebited;
	}
	
	public boolean isCardDebited() {
		return isCardDebited;
	}
}
