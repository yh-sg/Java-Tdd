package com.example.JavaTdd.payment;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Payment {
	
	@Id
	@GeneratedValue
	private Long paymentId;

    private UUID customerId;

    private BigDecimal amount;

    private Currency currency;

    private String source;

    private String description;

    public Payment(Long paymentId, UUID customerId, BigDecimal amount, Currency currency, String source, String description) {
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.amount = amount;
        this.currency = currency;
        this.source = source;
        this.description = description;
    }

    public Payment() {
    }
}
