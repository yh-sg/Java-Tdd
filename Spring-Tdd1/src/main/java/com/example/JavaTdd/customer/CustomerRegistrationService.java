package com.example.JavaTdd.customer;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JavaTdd.utils.PhoneNumberValidator;

@Service
public class CustomerRegistrationService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PhoneNumberValidator phoneNumberValidator;
	
    public CustomerRegistrationService(CustomerRepository customerRepository,PhoneNumberValidator phoneNumberValidator) {
        this.customerRepository = customerRepository;
        this.phoneNumberValidator = phoneNumberValidator;
    }
	
    public void registerNewCustomer(CustomerRegistrationRequest request) {
    	
    	String phoneNumber = request.getCustomer().getPhoneNumber();
    	
    	if(!phoneNumberValidator.validate(phoneNumber)) {
    		throw new IllegalStateException("Phone number "+phoneNumber+" is not valid"); 
    	}
    
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
