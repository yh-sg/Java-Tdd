package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.CustomerContact;
import com.example.demo.repo.CustomerContactRepo;
import com.example.demo.service.impl.ContactsManagementServiceImpl;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ContactManagementServiceUnitTest {
	
	@Mock
	private CustomerContactRepo customerContactRepo;
	
	@InjectMocks
	private ContactsManagementServiceImpl contactsManagementService;
	
	@Captor
	private ArgumentCaptor<CustomerContact> customerContactArgumentCaptor;
	
	@Test
	public void testAddContact() {
		//given
		CustomerContact newContact = new CustomerContact("Miku","Itou");
		
		//when
		when(customerContactRepo.save(any())).thenReturn(newContact);
		
		//then
		contactsManagementService.add(newContact);
		verify(customerContactRepo, times(1)).save(customerContactArgumentCaptor.capture());
		CustomerContact resultContact = customerContactArgumentCaptor.getValue();
		
		assertEquals(newContact,resultContact);
	}
}
