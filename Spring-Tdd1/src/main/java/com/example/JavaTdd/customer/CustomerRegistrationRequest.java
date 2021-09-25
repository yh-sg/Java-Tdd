package com.example.JavaTdd.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerRegistrationRequest {
	
	private Customer customer;
	
	public CustomerRegistrationRequest(@JsonProperty("customer") Customer customer) {
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}

}
