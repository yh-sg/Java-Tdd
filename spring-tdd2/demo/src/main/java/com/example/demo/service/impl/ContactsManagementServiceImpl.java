package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomerContact;
import com.example.demo.repo.CustomerContactRepo;
import com.example.demo.service.ContactsManagementService;

@Service
public class ContactsManagementServiceImpl implements ContactsManagementService {
	
	@Autowired
	private CustomerContactRepo customerContactRepo;

	@Override
	public CustomerContact add(CustomerContact addContact) {
		
		CustomerContact newContact = customerContactRepo.save(addContact);
		
		return newContact;
	}

}
