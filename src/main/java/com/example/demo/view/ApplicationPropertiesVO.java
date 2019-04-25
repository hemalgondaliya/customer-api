package com.example.demo.view;

import com.example.demo.modal.PaymentMethods;

public class ApplicationPropertiesVO {

    PaymentMethods paymentMethods;

    public PaymentMethods[] getPaymentMethods() {
        return PaymentMethods.values();
    }

}
