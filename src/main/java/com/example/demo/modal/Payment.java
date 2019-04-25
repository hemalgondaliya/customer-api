package com.example.demo.modal;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(generator = "payment_id_generator")
    @SequenceGenerator(
            name = "payment_id_generator",
            sequenceName = "number_sequence",
            initialValue = 1
    )
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    @NotNull(message = "Customer can not be null")
    private Customer customer;

    @Column(name = "date",columnDefinition = "DATE")
    @NotNull(message = "Date can not be null")
    Date date;

    @Column(name = "amount", columnDefinition = "INTEGER")
    @NotNull(message = "Amount can not be null")
    private Integer amount;

    @Column(name = "payment_method", columnDefinition = "VARCHAR(20)")
    @NotBlank(message = "Payment method can not be blank")
    private String paymentMethod;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "cheque_payment_id")
    private ChequePayment chequePayment;

    @Column(name = "receiver",columnDefinition = "VARCHAR(50)")
    @NotBlank(message = "Receiver person can not be blank")
    private String receiver;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ChequePayment getChequePayment() {
        return chequePayment;
    }

    public void setChequePayment(ChequePayment chequePayment) {
        this.chequePayment = chequePayment;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
