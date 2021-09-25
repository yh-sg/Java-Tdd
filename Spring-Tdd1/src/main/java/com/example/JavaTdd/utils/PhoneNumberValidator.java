package com.example.JavaTdd.utils;

import org.springframework.stereotype.Service;

@Service
public class PhoneNumberValidator {
	public boolean validate(String phoneNumber) {
		return phoneNumber.startsWith("+65") && phoneNumber.length()==11;
	}
}
