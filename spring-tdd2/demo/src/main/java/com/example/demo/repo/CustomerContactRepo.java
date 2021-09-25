package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.CustomerContact;

public interface CustomerContactRepo extends JpaRepository<CustomerContact, Long> {
	
	@Query(value="SELECT * FROM tbl_customer_contact u WHERE u.email = ?1", nativeQuery=true)
	public Optional<CustomerContact> findByEmail(String email);
}
