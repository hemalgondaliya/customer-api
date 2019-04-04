package com.example.demo.modal;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
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
    int billNumber;

    @Column(name = "first_name", columnDefinition = "VARCHAR(50)")
    String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR(50)")
    String lastName;

    @Column(name = "reference_name", columnDefinition = "VARCHAR(50)")
    String referenceName;

    @Column(name = "phone_no",columnDefinition = "BIGINT")
    Long phoneNumber;

    @Column(name = "date",columnDefinition = "DATE")
    Date date;

    @Column(name = "item",columnDefinition = "VARCHAR(50)")
    String item;

    @Column(name = "address",columnDefinition = "VARCHAR(50)")
    String address;

    @Column(name = "price",columnDefinition = "INTEGER")
    Integer price;

    @ManyToOne(optional = true)
    @JoinColumn(name = "delivery_person_id",nullable = true)
    DeliveryPerson deliveryPerson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(int billNumber) {
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }
}
