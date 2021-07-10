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
	
	@Autowired
	private CardPaymentCharger cardPaymentCharger;
	
	public PaymentService(CustomerRepository customerRepository, PaymentRepository paymentRepository, CardPaymentCharger cardPaymentCharger) {
		this.customerRepository = customerRepository;
		this.paymentRepository = paymentRepository;
		this.cardPaymentCharger = cardPaymentCharger;
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
		CardPaymentCharge cardPaymentCharge = 
				cardPaymentCharger
					.chargeCard
					(
						customerId, 
						paymentRequest.getPayment().getAmount(), 
						checkCurrency, 
						paymentRequest.getPayment().getDescription()
					);
		
		if(!cardPaymentCharge.isCardDebited()) {
			throw new IllegalStateException("Card not debited for customer " + customerId);
		}
		//Insert payment?
		paymentRequest.getPayment().setCustomerId(customerId);
		paymentRepository.save(paymentRequest.getPayment());
	}
}
