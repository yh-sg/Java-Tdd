package com.example.JavaTdd.payment;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JavaTdd.customer.CustomerRepository;

@Service
public class PaymentService {

	private static final List<Currency> ACCEPTED_CURRENCIES = List.of(Currency.SGD, Currency.JPY);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	public PaymentService(CustomerRepository customerRepository, PaymentRepository paymentRepository) {
		this.customerRepository = customerRepository;
		this.paymentRepository = paymentRepository;
	}
	
	public void chargeCard(UUID customerId, PaymentRequest paymentRequest) {
		//Check customer and currency
		boolean checkIfCustomerExist = customerRepository.findById(customerId).isPresent();
		if(!checkIfCustomerExist) {
			throw new IllegalStateException("Customer does not exist!");
		}
		
		Currency checkCurrency = paymentRequest.getPayment().getCurrency();
		boolean isCurrencySupported = ACCEPTED_CURRENCIES.contains(checkCurrency);
		
		if(!isCurrencySupported) {
			throw new IllegalStateException(checkCurrency+" is not supported!");
		}
		
		//Charge card
		//Insert payment?
	}
}
