package com.example.demo.view;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.modal.Customer;
import com.example.demo.repository.DeliveryPersonRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomerVO {

    @Autowired
    private DeliveryPersonRepository deliveryPersonRepository;

    int billNumber;

    String firstName;

    String lastName;

    String referenceName;

    Long phoneNumber;

    Date date;

    String item;

    String address;

    Integer price;

    String deliveryPerson;

    @JsonIgnore
    Customer customer;

    public CustomerVO() {

    }
    public CustomerVO(Customer customer) {
        this.customer = customer;
        this.billNumber = customer.getBillNumber();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.referenceName = customer.getReferenceName();
        this.phoneNumber = customer.getPhoneNumber();
        this.date = customer.getDate();
        this.item = customer.getItem();
        this.address = customer.getAddress();
        this.price = customer.getPrice();
        this.deliveryPerson = customer.getDeliveryPerson().getName();
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

    public String getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(String deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public Customer getCustomer() {
        if(this.customer == null) {
            Customer cust = new Customer();
            cust.setItem(this.item);
            cust.setAddress(this.address);
            cust.setPrice(this.price);
            cust.setDate(this.date);
            cust.setPhoneNumber(this.phoneNumber);
            cust.setReferenceName(this.referenceName);
            cust.setLastName(this.lastName);
            cust.setFirstName(this.firstName);
            cust.setBillNumber(this.billNumber);
            return cust;
        }
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
