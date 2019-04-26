package com.example.demo.repository;

import com.example.demo.modal.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByCustomer_Id(Long id);
    List<Payment> findByCustomer_IdAndPaymentMethodAndDate(Long id, String method, Date date);
}
