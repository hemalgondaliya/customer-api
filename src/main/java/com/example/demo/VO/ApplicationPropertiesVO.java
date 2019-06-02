package com.example.demo.VO;

import com.example.demo.modal.PaymentMethods;

public class ApplicationPropertiesVO {

    PaymentMethods paymentMethods;

    public PaymentMethods[] getPaymentMethods() {
        return PaymentMethods.values();
    }

}
