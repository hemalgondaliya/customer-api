package com.example.demo.vo;

import com.example.demo.modal.PaymentMethods;

public class ApplicationPropertiesVO {

    PaymentMethods paymentMethods;

    public PaymentMethods[] getPaymentMethods() {
        return PaymentMethods.values();
    }

}
