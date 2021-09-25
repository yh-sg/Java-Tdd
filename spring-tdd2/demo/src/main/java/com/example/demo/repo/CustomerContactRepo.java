package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CustomerContact;

public interface CustomerContactRepo extends JpaRepository<CustomerContact, Long> {
	public CustomerContact findByEmail(String email);
}
