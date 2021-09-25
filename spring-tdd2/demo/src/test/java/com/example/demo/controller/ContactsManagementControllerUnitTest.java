package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.entity.CustomerContact;
import com.example.demo.service.ContactsManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class ContactsManagementControllerUnitTest {

	@Mock
	private ContactsManagementService contactsManagementService;

	@InjectMocks
	private ContactsManagementController contactsManagementController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(contactsManagementController).build();
    }
	
	@Test
    public void failPost() throws Exception {
        mockMvc.perform(post("/")).andExpect(status().is4xxClientError());
    }
	
	@Test
	public void successPost() throws Exception {
		//given
		CustomerContact newContact = new CustomerContact("Inori","Minase");
		String asJson = new ObjectMapper().writeValueAsString(newContact);
		
		System.out.println("ASJSON-->"+asJson);
		
		//when and then
		when(contactsManagementService.add(any())).thenReturn(newContact);
		
        mockMvc.perform(post("/addContact")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(asJson)
     	        .accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		.andReturn();
    }
	
	@Test
	public void successPostButFailResult() throws Exception {
		//given
		CustomerContact newContact = new CustomerContact(null,"Minase");
		String asJson = new ObjectMapper().writeValueAsString(newContact);
		
		when(contactsManagementService.add(any())).thenReturn(null);
		
		//when and then
	       mockMvc.perform(post("/addContact")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(asJson)
	     	        .accept(MediaType.APPLICATION_JSON))
	    		   .andExpect(status().isMethodNotAllowed())
	    		   .andReturn();
	       
       
    }
	
}
