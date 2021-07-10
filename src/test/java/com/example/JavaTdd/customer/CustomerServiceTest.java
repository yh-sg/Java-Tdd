package com.example.JavaTdd.customer;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) 
public class CustomerServiceTest {
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Captor
	private ArgumentCaptor<Customer> customerArgumentCaptor;
	
	@InjectMocks
	private CustomerRegistrationService customerRegistrationService;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        customerRegistrationService = new CustomerRegistrationService(customerRepository);
//    }

	@Test
	void itShouldSaveNewCustomer() {
		// Given
		String phoneNumber = "65656565";
		Customer customer = new Customer(UUID.randomUUID(), "Kyaru", phoneNumber);

		// ... a request for registration
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(customer);
        
        // ... No existing customer with existing phone Number 
        given(customerRepository.selectCustomerByPhoneNumber(phoneNumber)).willReturn(Optional.empty());
        
        //when
        customerRegistrationService.registerNewCustomer(request);
        
		// Then
		then(customerRepository).should().save(customerArgumentCaptor.capture());
		Customer customerArgumentCaptorValue = customerArgumentCaptor.getValue();
		assertEquals(customer,customerArgumentCaptorValue);
	}
	
	@Test
	void itShouldSaveNewCustomerWhenIdIsNull() {
		//Given
		String phoneNumber = "654321234";
		Customer customer = new Customer(null, "Kokkoro", phoneNumber);
		
		// ... registration request
		CustomerRegistrationRequest request = new CustomerRegistrationRequest(customer);
        given(customerRepository.selectCustomerByPhoneNumber(phoneNumber)).willReturn(Optional.empty());
        //when
        customerRegistrationService.registerNewCustomer(request);
        
        //then
        then(customerRepository).should().save(customerArgumentCaptor.capture());
        Customer customerArgumentCaptorValue = customerArgumentCaptor.getValue();
        assertThat(customerArgumentCaptorValue)
        	.isEqualToComparingOnlyGivenFields(customer, "name", "phoneNumber");
        assertNotNull(customerArgumentCaptorValue.getId());
	}
	
	@Test
	void itShouldNotSaveCustomerIfExist() {
		//Given
		String phoneNumber = "65656565";
		Customer customer = new Customer(UUID.randomUUID(), "Kyaru", phoneNumber);

		// ... a request for registration
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(customer);
        
        // ... request customer does exist
        given(customerRepository.selectCustomerByPhoneNumber(phoneNumber)).willReturn(Optional.of(customer));
        
        // When
        customerRegistrationService.registerNewCustomer(request);
        
        //then
        then(customerRepository).should(never()).save(any());
	}
	
	@Test
	void itShouldThrowWhenPhoneNumberIsTaken() {
		//Given
		String phoneNumber = "999888777";
		Customer customer1 = new Customer(UUID.randomUUID(), "Hatsune", phoneNumber);
		Customer customer2 = new Customer(UUID.randomUUID(), "Shiori", phoneNumber);
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(customer1);
        given(customerRepository.selectCustomerByPhoneNumber(phoneNumber))
        	.willReturn(Optional.of(customer2));
        //when
        assertThatThrownBy(()->customerRegistrationService.registerNewCustomer(request))
        	.isInstanceOf(IllegalStateException.class)
        	.hasMessageContaining("Phone number "+phoneNumber+" is taken =p");
        //then
        then(customerRepository).should(never()).save(any());
	}
}
