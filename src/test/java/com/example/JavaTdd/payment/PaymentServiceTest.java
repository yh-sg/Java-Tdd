package com.example.JavaTdd.payment;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.JavaTdd.customer.Customer;
import com.example.JavaTdd.customer.CustomerRepository;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Mock
	private PaymentRepository paymentRepository;
	
	@Mock
	private CardPaymentCharger cardPaymentCharger;
	
	@Mock
	private Customer customer;
	
	@Captor
	private ArgumentCaptor<Payment> paymentArgumentCaptor;
	
	@InjectMocks
	private PaymentService paymentService;
	
	@Test
	void chargeCardSuccessfully() {
		//Given
		UUID customerId = UUID.randomUUID();
		given(customerRepository.findById(customerId)).willReturn(Optional.of(customer));
		PaymentRequest paymentRequest = new PaymentRequest(
				new Payment(
						null,
						null,
						new BigDecimal("100.00"),
						Currency.JPY,
						"visaxx123xx",
						"School Fee"
						)
				);
		
		given(cardPaymentCharger.chargeCard(
				customerId, 
				paymentRequest.getPayment().getAmount(), 
				paymentRequest.getPayment().getCurrency(), 
				paymentRequest.getPayment().getDescription()
			)).willReturn(new CardPaymentCharge(true));
				
		//When
		paymentService.chargeCard(customerId, paymentRequest);
		
		//Then
		then(paymentRepository).should().save(paymentArgumentCaptor.capture());
		assertThat(paymentArgumentCaptor.getValue())
			.isEqualToIgnoringGivenFields(paymentRequest.getPayment(), "customerId");
		
		assertThat(paymentArgumentCaptor.getValue().getCustomerId()).isEqualTo(customerId);
	}
	
	@Test
	@Disabled("Under Construction")
	void throwErrWhenCardIsNotCharged() {
		
	}
	
	@Test
	@Disabled("Under Construction")
	void throwErrWhenCurrencyIsNotSupported() {
		
	}
	
	@Test
	@Disabled("Under Construction")
	void throwErrWhenCustomerNotFound() {
		
	}
}

