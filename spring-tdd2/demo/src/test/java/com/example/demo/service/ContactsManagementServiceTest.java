package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.entity.CustomerContact;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ContactsManagementServiceTest {
	
	@Autowired
	private ContactsManagementService contactsManagementService;

	@Test
	@DisplayName("Integration test for @Service")
	public void testAddContact() {
		//given
		CustomerContact newContact = new CustomerContact();
		newContact.setFirstName("Rikka");
		newContact.setLastName("Tachibana");
		
		//when
		contactsManagementService.add(newContact);
		
		//then
		assertNotNull(newContact);
		assertNotNull(newContact.getId());
		assertEquals("Rikka",newContact.getFirstName());
		
	}
}
