package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tbl_customer_contact")
public class CustomerContact {
	
	//By default Spring uses org.springframework.boot.orm.jpa.SpringNamingStrategy to generate table names. 
	//The ImprovedNamingStrategy will convert CamelCase to SNAKE_CASE
	//Either add spring.jpa.hibernate.naming_strategy=org.hibernate.cfg.EJB3NamingStrategy it or do this.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="firstname")
	private String firstName;
	@Column(name="lastname")
	private String lastName;
	private String email;
	@Column(name="deliveryaddressline1") 
	private String deliveryAddressLine1;
	@Column(name="deliveryaddressline2") 
	private String deliveryAddressLine2;
	@Column(name="deliveryaddresscity")
	private String deliveryAddressCity;
	@Column(name="deliveryaddressstate")
	private String deliveryAddressState;
	@Column(name="deliveryaddresszipcode")
	private String deliveryAddressZipCode;
}
