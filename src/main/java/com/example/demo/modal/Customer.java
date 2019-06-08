package com.example.demo.modal;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customer", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "bill_no", "phone_no" }) })
public class Customer {
    @Id
    @GeneratedValue(generator = "cust_id_generator")
    @SequenceGenerator(
            name = "cust_id_generator",
            sequenceName = "customer_id_number_sequence",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "bill_no", columnDefinition = "INTEGER")
    @NotNull(message = "Bill number can not be null")
    Integer billNumber;

    @Column(name = "first_name", columnDefinition = "VARCHAR(50)")
    @NotBlank(message = "First name can not be blank")
    String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR(50)")
    @NotBlank(message = "Last name can not be blank")
    String lastName;

    @Column(name = "reference_name", columnDefinition = "VARCHAR(50)")
    String referenceName;

    @Column(name = "phone_no",columnDefinition = "BIGINT")
    @NotNull(message = "Phone number can not be null")
    Long phoneNumber;

    @Column(name = "second_phone_no",columnDefinition = "BIGINT")
    Long secondNumber;

    @Column(name = "date",columnDefinition = "DATE")
    @NotNull(message = "Date can not be null")
    Date date;

    @Column(name = "address",columnDefinition = "VARCHAR(50)")
    @NotBlank(message = "Address can not be blank")
    String address;

    @Column(name = "email",columnDefinition = "VARCHAR(50)")
    String email;

    @ManyToOne
    @JoinColumn(name = "delivery_person_id")
    @NotNull(message = "Delivery person can not be null")
    DeliveryPerson deliveryPerson;

    @Column(name = "deprecated", columnDefinition = "BOOLEAN")
    @NotNull(message = "deprecated can not be null")
    private boolean deprecated;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_id")
    @NotNull(message = "Product list can not be null")
    List<Purchase> purchaseList;

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public Long getId() {
        return id;
    }

    public Integer getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(Integer billNumber) {
        this.billNumber = billNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(Long secondNumber) {
        this.secondNumber = secondNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDeprecated() {
        return deprecated;
    }

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }
}
