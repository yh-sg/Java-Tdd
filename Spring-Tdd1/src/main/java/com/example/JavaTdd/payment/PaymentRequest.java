package com.example.JavaTdd.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaymentRequest {
	
	private Payment payment;
	
	public PaymentRequest(@JsonProperty("payment") Payment payment) {
		this.payment = payment;
	}

}
