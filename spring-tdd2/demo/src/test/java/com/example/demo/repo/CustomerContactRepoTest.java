package com.example.demo.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.entity.CustomerContact;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerContactRepoTest {
	
	@Autowired
	private CustomerContactRepo customerContactRepo;

	@Test
	void test() {
		//given
		CustomerContact newContact = new CustomerContact("Konomi","Kohara", "kokochan@ymail.com");
		
		//when
		customerContactRepo.save(newContact);
		Optional<CustomerContact> foundContact = customerContactRepo.findByEmail("kokochan@ymail.com");
		
		//then
		assertThat(foundContact).isPresent().hasValue(newContact).hasValueSatisfying(e->{assertEquals(e,newContact);});
		assertEquals(foundContact.get().getEmail(), newContact.getEmail());
	}
	
	@Test
	void test2() {
		//given
		CustomerContact newContact = new CustomerContact("Azusa","Tadokoro", "kaichou@ymail.com");
		
		//when
		customerContactRepo.save(newContact);
		Optional<CustomerContact> foundContact = customerContactRepo.findByEmail("koroazu@ymail.com");
		
		//then
		assertTrue(foundContact.isEmpty());
	}


}
