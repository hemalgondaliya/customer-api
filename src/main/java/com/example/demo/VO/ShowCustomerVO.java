package com.example.demo.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.modal.Customer;
import com.example.demo.modal.Purchase;

public class ShowCustomerVO {

    Integer billNumber;
    String firstName;
    String lastName;
    String referenceName;
    Long phoneNumber;
    Long secondNumber;
    Date date;
    String address;
    String email;
    String deliveryPerson;
    List<Purchase> purchaseList;
    Integer debitAmount;
    List<String> purchase;


    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    public ShowCustomerVO() {

    }
    public ShowCustomerVO(Customer customer, Integer debtAmount) {
        this.billNumber = customer.getBillNumber();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.referenceName = customer.getReferenceName();
        this.phoneNumber = customer.getPhoneNumber();
        this.secondNumber = customer.getSecondNumber();
        this.date = customer.getDate();
        this.address = customer.getAddress();
        this.email = customer.getEmail();
        this.debitAmount = debtAmount;
        this.deliveryPerson = customer.getDeliveryPerson().getName();
        this.purchaseList = customer.getPurchaseList();
        this.initProducts();
    }

    private void initProducts() {
        this.purchase = this.purchaseList
                .stream().map(p -> p.getProductModel().getBrand().getName() + "-" +
                p.getProductModel().getBrand().getCategory() + "("+p.getQty()+")").collect(Collectors.toList());
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

    public String getDate() {
        return format.format(date);
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

    public Integer getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Integer debitAmount) {
        this.debitAmount = debitAmount;
    }

    public List<String> getPurchase() {
        return purchase;
    }

    public void setPurchase(List<String> purchase) {
        this.purchase = purchase;
    }
}
