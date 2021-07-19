package com.example.JavaTdd.payment;

import java.math.BigDecimal;
import java.util.UUID;

public interface CardPaymentCharger {
	
	CardPaymentCharge chargeCard(UUID cardSource,
			BigDecimal amount,
			Currency currency,
			String description
	);
}
