package com.example.JavaTdd.payment;

public class CardPaymentCharge {
	private boolean isCardDebited;
	
	public CardPaymentCharge(boolean isCardDebited) {
		this.isCardDebited = isCardDebited;
	}
	
	public boolean isCardDebited() {
		return isCardDebited;
	}
}
