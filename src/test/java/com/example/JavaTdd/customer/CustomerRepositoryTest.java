package com.example.JavaTdd.customer;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
	properties = {
			"spring.jpa.properties.javax.persistence.validation.mode=none"
	}
)

public class CustomerRepositoryTest {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	void saveCustomer() {
		//Given
		UUID id = UUID.randomUUID();
		Customer customer = new Customer(id, "Ruka", "999");
		
		//when
		customerRepository.save(customer);
		
		//then
		Optional<Customer> findCustById = customerRepository.findById(id);
		assertThat(findCustById)
			.isPresent()
			.hasValueSatisfying(c-> {
				assertThat(c).
				isEqualToComparingFieldByField(customer);
			});
	}
	
	@Test
	void notSelectCustomerIfPhoneNumberDoesNotExist() {
		//Given
		Customer customer = new Customer( UUID.randomUUID(), "Anna", "008");
		
		//When
		customerRepository.save(customer);
		Optional<Customer> findCustById = customerRepository.selectCustomerByPhoneNumber("007");
		
		//Then
		assertThat(findCustById).isNotPresent();
	}
	
	@Test
	@Disabled("Under construction")
	void selectCustomerIfPhoneNumberExist() {
		//Given
		//When
		//Then
	}
	
	@Test
	@Disabled("Under construction")
	void shouldNotSaveWhenNameIsNull() {
		//Given
		//When
		//Then
	}
	
	@Test
	@Disabled("Under construction")
	void shouldNotSaveWhenPhoneNumIsNull() {
		//Given
		//When
		//Then
	}
}
