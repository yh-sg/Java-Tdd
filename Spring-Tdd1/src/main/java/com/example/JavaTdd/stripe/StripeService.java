package com.example.JavaTdd.stripe;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.JavaTdd.payment.CardPaymentCharge;
import com.example.JavaTdd.payment.CardPaymentCharger;
import com.example.JavaTdd.payment.Currency;

@Service
@ConditionalOnProperty(
		value="stripe.enabled",
		havingValue="false"
)
public class StripeService implements CardPaymentCharger {
	@Override
	public CardPaymentCharge chargeCard(
			UUID cardSource,
			BigDecimal amount,
			Currency currency,
			String description) {
		return new CardPaymentCharge(true);
	}
}
