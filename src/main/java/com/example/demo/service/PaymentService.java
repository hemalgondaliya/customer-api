package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.modal.ChequePayment;
import com.example.demo.modal.Customer;
import com.example.demo.modal.Payment;
import com.example.demo.modal.Response;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.VO.PaymentVO;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CustomerService customerService;

    public ResponseEntity save(PaymentVO paymentVO, Customer customer) {
        Payment p = new Payment();
        p.setAmount(paymentVO.getAmount());
        p.setCustomer(customer);
        p.setDate(new Date());
        p.setReceiver(paymentVO.getReceiver());
        p.setPaymentMethod(paymentVO.getPaymentMethod());
        if (paymentVO.getChequeNumber() != null) {
            ChequePayment cq = new ChequePayment();
            cq.setChequeNumber(paymentVO.getChequeNumber());
            cq.setBankName(paymentVO.getBankName());
            p.setChequePayment(cq);
        }

        paymentRepository.save(p);
        return new ResponseEntity<>(new Response(
                "Payment" + p.getAmount() + " is added for" + p.getCustomer().getFirstName(), 200),
                HttpStatus.OK);
    }

    public boolean isDuplicate(PaymentVO paymentVO, Customer customer) {
        return !paymentRepository.findByCustomer_IdAndPaymentMethodAndDate(customer.getId(),
                paymentVO.getPaymentMethod(), new Date()).isEmpty();
    }

    public boolean isOverPaid(Customer customer, Integer currentAmount){
        return currentAmount > customerService.getDebtAmountByCustomer(customer);
    }
}

