package com.example.JavaTdd.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
