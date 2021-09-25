package com.example.JavaTdd.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer-registration")
public class CustomerController {
	@Autowired
	private CustomerRegistrationService customerRegistrationService;
	
	@PutMapping
	public void registerNewCustomer(@RequestBody CustomerRegistrationRequest request) {
		customerRegistrationService.registerNewCustomer(request);
	}
}
