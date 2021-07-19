package com.example.JavaTdd.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.JavaTdd.utils.PhoneNumberValidator;

public class PhoneValidationTest {
	
	private PhoneNumberValidator phoneNumberValidation = new PhoneNumberValidator();
	
	@ParameterizedTest
	@CsvSource({
		"+8712345678,false",
		"65123456789,false",
		"+6512345678,true",
	})
	void phoneNumValidatorTest(String phoneNumber, boolean expected) {
		boolean result = phoneNumberValidation.validate(phoneNumber);
		assertEquals(result,expected);
	}
}
