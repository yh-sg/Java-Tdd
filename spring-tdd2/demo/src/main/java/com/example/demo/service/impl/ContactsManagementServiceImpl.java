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
		
		//! This is a very bad example, by right there should be a validator util for it.
		//! But for simple testing exercise purpose, this will do. =)
		CustomerContact newContact = null;
		
		System.out.println(addContact);
		System.out.println(addContact.getFirstName());
		
		if(addContact.getFirstName()!=null) {
			newContact = customerContactRepo.save(addContact);
		}
		
		return newContact;
	}

}
