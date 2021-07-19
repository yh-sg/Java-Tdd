package com.example.JavaTdd.payment;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
		properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
		}
)

public class PaymentRepoTest {
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Test
	public void insertPaymentTest() {
		//given
		long paymentId = 1L;
		Payment payment = new Payment(
				null,
				UUID.randomUUID(),
				new BigDecimal("10000.00"),
				Currency.JPY,
				"MasterCardx1235",
				"Shopping");
		//when
		paymentRepository.save(payment);
		//then
		Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);
		assertThat(paymentOptional).isPresent()
			.hasValueSatisfying(e -> assertThat(e).isEqualTo(payment));
	}
}
