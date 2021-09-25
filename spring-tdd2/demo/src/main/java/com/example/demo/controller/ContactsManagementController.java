package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.CustomerContact;
import com.example.demo.service.ContactsManagementService;

@Controller
@RequestMapping("/addContact")
public class ContactsManagementController {
	@Autowired
	private ContactsManagementService contactsManagementService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> processAddContact(@RequestBody CustomerContact contact) {
		CustomerContact newContact = contactsManagementService.add(contact);
		
		if(newContact==null) {
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
