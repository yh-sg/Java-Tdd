package com.example.JavaTdd.customer;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerRegistrationService {

	@Autowired
	private CustomerRepository customerRepository;
	
    public CustomerRegistrationService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
	
    public void registerNewCustomer(CustomerRegistrationRequest request) {
    	
    	String phoneNumber = request.getCustomer().getPhoneNumber();
    
    	Optional<Customer> customerOptional = customerRepository.selectCustomerByPhoneNumber(phoneNumber);
    	
    	if(customerOptional.isPresent()) {
    		Customer customer = customerOptional.get();
    		if(customer.getName().equals(request.getCustomer().getName())) {
    			return;
    		}
    		throw new IllegalStateException("Phone number " +phoneNumber+ " is taken =p");
    	}

    	if(request.getCustomer().getId()==null) {
    		request.getCustomer().setId(UUID.randomUUID());
    	}
    	
    	customerRepository.save(request.getCustomer());
    }
}
