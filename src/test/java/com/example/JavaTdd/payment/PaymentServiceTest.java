package com.example.JavaTdd.payment;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
	
	UUID customerId = UUID.randomUUID();
	
	@Test
	void chargeCardSuccessfully() {
		//Given
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
	void throwErrWhenCustomerNotFound() {
		//given
		given(customerRepository.findById(customerId)).willReturn(Optional.empty());
		//when - Customer not found in db
		assertThatThrownBy(()->paymentService.chargeCard(customerId, new PaymentRequest(new Payment())
				)).isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("Customer does not exist!");
		//then
		then(cardPaymentCharger).shouldHaveNoInteractions();
		then(paymentRepository).shouldHaveNoInteractions();
	}
	
	@Test
	void throwErrWhenCurrencyIsNotSupported() {
		//given
		given(customerRepository.findById(customerId)).willReturn(Optional.of(customer));
		Currency notSupportedCurrency = Currency.EUR;
		PaymentRequest paymentRequest = new PaymentRequest(
				new Payment(
			        null,
			        null,
			        new BigDecimal("100"),
			        notSupportedCurrency,
			        "visaxx123xx",
			        "School Fee"
				)
			);
		//when
		assertThatThrownBy(()->paymentService.chargeCard(customerId, paymentRequest))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining(notSupportedCurrency+" is not supported!");
		//then
		then(cardPaymentCharger).shouldHaveNoInteractions();
		then(paymentRepository).shouldHaveNoInteractions();
	}
	
	@Test
	void throwErrWhenCardIsNotCharged() {
		//Given
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
			)).willReturn(new CardPaymentCharge(false));
				
		//When
		assertThatThrownBy(() -> paymentService.chargeCard(customerId, paymentRequest))
					.isInstanceOf(IllegalStateException.class)
					.hasMessageContaining("Card not debited for customer " + customerId);
		
		//Then
		then(paymentRepository).shouldHaveNoInteractions();
	}
	

	

}

