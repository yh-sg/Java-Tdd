package com.example.JavaTdd.customer;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
	void selectCustomerIfPhoneNumberExist() {
		//Given
		UUID id = UUID.randomUUID();
		Customer customer = new Customer(id, "Nagisa", "112233");
		//When
		customerRepository.save(customer);
		//Then
		Optional<Customer> selectPhoneCustomer = customerRepository.selectCustomerByPhoneNumber("112233");
		assertThat(selectPhoneCustomer)
			.isPresent()
			.hasValueSatisfying(c->{
				assertThat(c).isEqualToComparingFieldByField(customer);
			});
	}
	
	@Test
	void shouldNotSaveWhenNameIsNull() {
		//Given
		UUID id = UUID.randomUUID();
		Customer customer = new Customer(id, null, "999");
		//When
		//Then
		assertThatThrownBy(()->customerRepository.save(customer))
		.hasMessageStartingWith("not-null")
		.isInstanceOf(DataIntegrityViolationException.class);
	}
	
	@Test
	void shouldNotSaveWhenPhoneNumIsNull() {
		//Given
		UUID id = UUID.randomUUID();
		Customer customer = new Customer(id, "Sarashiki", null);
		//When
		//Then
		assertThatThrownBy(()->customerRepository.save(customer))
		.hasMessageStartingWith("not-null")
		.isInstanceOf(DataIntegrityViolationException.class);
	}
}
