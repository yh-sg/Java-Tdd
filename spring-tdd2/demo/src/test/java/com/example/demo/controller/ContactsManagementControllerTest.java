package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.entity.CustomerContact;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ContactsManagementControllerTest {
	
	@Autowired
	private ContactsManagementController contactsManagementController;

	@Test
	public void testAddController() {
		//given
		CustomerContact newContact = new CustomerContact("Ayaka","Oohashi");
		
		//when
		ResponseEntity<Object> result = contactsManagementController.processAddContact(newContact);
		
		//then
		assertEquals(result, new ResponseEntity<>(HttpStatus.OK));
	}
	
	@Test
	public void testFailAddController() {
		//given
		CustomerContact newContact = new CustomerContact(null,"Oohashi");
		
		//when
		ResponseEntity<Object> result = contactsManagementController.processAddContact(newContact);
		
		//then
		assertEquals(result, new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED));
	}

}
