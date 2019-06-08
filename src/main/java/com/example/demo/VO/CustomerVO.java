package com.example.demo.vo;

import java.util.Date;
import java.util.List;
import com.example.demo.modal.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomerVO {

    private Integer billNumber;
    private String firstName;
    private String lastName;
    private String referenceName;
    private Long phoneNumber;
    private Long secondNumber;
    private Date date;
    private String address;
    private String email;
    private String deliveryPerson;
    private List<ProductVO> selectedProducts;

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
        this.secondNumber = customer.getSecondNumber();
        this.date = customer.getDate();
        this.address = customer.getAddress();
        this.email = customer.getEmail();
        this.deliveryPerson = customer.getDeliveryPerson().getName();
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

    public String getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(String deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public Customer getCustomer() {
        if(this.customer == null) {
            Customer cust = new Customer();
            cust.setAddress(this.address);
            cust.setDate(this.date);
            cust.setPhoneNumber(this.phoneNumber);
            cust.setSecondNumber(this.secondNumber);
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

    public List<ProductVO> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<ProductVO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }
}
