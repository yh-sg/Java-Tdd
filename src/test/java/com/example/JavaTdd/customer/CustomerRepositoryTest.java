package com.example.JavaTdd.customer;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

public class CustomerRepositoryTest {
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Captor
	private ArgumentCaptor<Customer> customerArgumentCaptor;
	
	private CustomerRegistrationService customerRegistrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerRegistrationService = new CustomerRegistrationService(customerRepository);
    }
	
	@Test
	void itShouldSaveNewCustomer() {
		// Given
		 String phoneNumber = "65656565";
//        CustomerRegistrationService customerRegistrationService = new CustomerRegistrationService(customerRepository);
		Customer customer = new Customer(UUID.randomUUID(), "Kyaru", phoneNumber);

		// ... a request for registration
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(customer);
        
        // ... No customer with phone Number passed
        given(customerRepository.selectCustomerByPhoneNumber(phoneNumber)).willReturn(Optional.empty());
        
        //when
        customerRegistrationService.registerNewCustomer(request);
        
		// Then
		then(customerRepository).should().save(customerArgumentCaptor.capture());
		Customer customerArgumentCaptorValue = customerArgumentCaptor.getValue();
		System.out.println(customerArgumentCaptorValue.getName());
		assertEquals(customer,customerArgumentCaptorValue);
	}
	
	@Test
	@Disabled("Under Construction")
	void itShouldSaveNewCustomerWhenIdIsNull() {
		
	}
	
	@Test
	@Disabled("Under Construction")
	void itShouldNotSaveCustomerIfExist() {
		
	}
	
	@Test
	@Disabled("Under Construction")
	void itShouldThrowWhenPhoneNumberIsTaken() {
		
	}
}
