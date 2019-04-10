package com.example.demo.modal;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customer", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "bill_no", "date" }) })
public class Customer {
    @Id
    @GeneratedValue(generator = "cust_id_generator")
    @SequenceGenerator(
            name = "cust_id_generator",
            sequenceName = "number_sequence",
            initialValue = 1
    )
    private Long id;

    @Column(name = "bill_no", columnDefinition = "INTEGER")
    @NotNull(message = "Bill number can not be null")
    Integer billNumber;

    @Column(name = "first_name", columnDefinition = "VARCHAR(50)")
    @NotNull(message = "First name can not be null")
    String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR(50)")
    @NotNull(message = "Last name can not be null")
    String lastName;

    @Column(name = "reference_name", columnDefinition = "VARCHAR(50)")
    String referenceName;

    @Column(name = "phone_no",columnDefinition = "BIGINT")
    @NotNull(message = "Phone number can not be null")
    Long phoneNumber;

    @Column(name = "date",columnDefinition = "DATE")
    @NotNull(message = "Date can not be null")
    Date date;

    @Column(name = "address",columnDefinition = "VARCHAR(50)")
    @NotNull(message = "Address can not be null")
    String address;

    @Column(name = "email",columnDefinition = "VARCHAR(50)")
    String email;

    @ManyToOne(optional = true)
    @JoinColumn(name = "delivery_person_id",nullable = true)
    @NotNull(message = "Delivery person can not be null")
    DeliveryPerson deliveryPerson;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_id")
    List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
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

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }
}
