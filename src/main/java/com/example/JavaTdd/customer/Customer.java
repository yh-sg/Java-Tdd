package com.example.JavaTdd.customer;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
	private UUID id;
	private String name;
	private String phoneNumber;
	
	public Customer(UUID id, String name, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public Customer() {
		
	}
}
