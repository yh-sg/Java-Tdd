package com.example.JavaTdd.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
    private PaymentService paymentService;
	
	@PostMapping
	public void makePayment(@RequestBody PaymentRequest paymentRequest) {
		paymentService.chargeCard(paymentRequest.getPayment().getCustomerId(), paymentRequest);
	}
}
